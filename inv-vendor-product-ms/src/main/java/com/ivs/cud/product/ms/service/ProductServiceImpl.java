package com.ivs.cud.product.ms.service;

import java.util.Collections;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import com.ivs.cud.product.ms.dtos.ProductDto;
import com.ivs.cud.product.ms.dtos.VariantsDto;
import com.ivs.cud.product.ms.exceptions.CustomExceptionProduct;
import com.ivs.cud.product.ms.models.ProductModel;
import com.ivs.cud.product.ms.models.SellerModel;
import com.ivs.cud.product.ms.models.VariantModel;
import com.ivs.cud.product.ms.repos.ProductRepo;
import com.ivs.cud.product.ms.request.SearchIdentifiers;
import com.ivs.cud.product.ms.utils.ProductUtils;

import com.mongodb.client.result.UpdateResult;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ProductServiceImpl implements IProductService {

	@Autowired
	private ProductRepo productrepo;

	@Autowired
	private ReactiveMongoTemplate reactiveMongoTemplate;

	@Override
	public Mono<Void> createProduct(List<ProductDto> products) throws JsonMappingException, JsonProcessingException {

		return Flux.fromIterable(products).flatMap(product -> {
			Mono<Boolean> productExists = this.productrepo.existsById(product.getProductId());
			return productExists.flatMap(exists -> {
				if (!exists) {
					try {
						this.saveProduct(product);
						return Mono.empty();
					} catch (JsonProcessingException e) {
						return Mono.error(new CustomExceptionProduct("Failed..."));
					}
				} else {
					return this.productrepo.findById(product.getProductId())
							.flatMapMany(model -> Flux.fromIterable(model.getSellers()))
							.filter(seller -> seller.getSellerId().equals(product.getSellerid()))
							.map(SellerModel::getSellerId).collectList().flatMap(sellerIds -> {
								if (sellerIds.isEmpty()) {
									Query query = new Query(Criteria.where("productId").is(product.getProductId()));
									SellerModel populateSeller = ProductUtils.populateSeller(product);

									Update update = new Update().push("sellers")
											.each(Collections.singleton(populateSeller));

									return reactiveMongoTemplate.updateFirst(query, update, ProductModel.class)
											.flatMap(updateResult -> {
												if (updateResult.getMatchedCount() <= 0) {
													return Mono.error(
															new CustomExceptionProduct("No Product Found..........."));
												}
												log.info("New seller added to product: " + product.getProductId());
												return Mono.empty();
											}).onErrorResume(throwable -> {
												if (throwable instanceof CustomExceptionProduct) {
													log.info("Error :" + throwable.getMessage());
													return Mono.error(throwable);
												} else {
													log.info("Error :" + throwable.getMessage());
													return Mono
															.error(new CustomExceptionProduct(throwable.getMessage()));
												}
											}).then();
								} else {
									Query query = new Query(Criteria.where("productId").is(product.getProductId())
											.and("sellers._id").is(product.getSellerid()));

									Update update = new Update().addToSet("sellers.$.variants")
											.each(product.getVariants().toArray());

									return reactiveMongoTemplate.updateFirst(query, update, ProductModel.class)
											.flatMap(updateResult -> {
												if (updateResult.getMatchedCount() <= 0) {
													return Mono.error(new CustomExceptionProduct(
															"No Product or Seller Found..........."));
												}
												log.info("New variants added to seller: " + product.getSellerid()
														+ " in product: " + product.getProductId());
												return Mono.empty();
											}).onErrorResume(throwable -> {
												log.error("Error: " + throwable.getMessage());
												if (throwable instanceof CustomExceptionProduct) {
													return Mono.error(throwable);
												} else {
													return Mono
															.error(new CustomExceptionProduct(throwable.getMessage()));
												}
											}).then();

								}
							});
				}
			});
		}).then();
	}

	@Override
	public void saveProduct(ProductDto product) throws JsonMappingException, JsonProcessingException {
		ProductModel dtoToEntity = ProductUtils.DTOToEntity(product);
		Mono<ProductModel> save = productrepo.save(dtoToEntity);
		save.doOnSuccess((s) -> {

			log.info("Product is inserted successfully with product id : " + s.getProductId());

		}).doOnError((thr) -> {

			new CustomExceptionProduct(thr.getMessage());
		}).subscribe();

	}

	@Override

	public Mono<Void> deleteProductVariant(SearchIdentifiers searchIdentifier) {
		if (ProductUtils.checkValues(searchIdentifier.getProductId())
				&& ProductUtils.checkValues(searchIdentifier.getVariantId())
				&& ProductUtils.checkValues(searchIdentifier.getSellerId())) {
			Query query = new Query(Criteria.where("productId").is(searchIdentifier.getProductId())
					.and("sellers.sellerId").is(searchIdentifier.getSellerId()));

			Update update = new Update().pull("sellers.$.variants",
					new Document("id", searchIdentifier.getVariantId()));

			Mono<UpdateResult> resultMono = reactiveMongoTemplate.updateFirst(query, update, ProductModel.class);

			return resultMono.flatMap(updateResult -> {
				if (updateResult.getMatchedCount() <= 0) {
					return Mono.error(new CustomExceptionProduct("No Product Found..........."));
				}
				log.info(searchIdentifier.getVariantId() + " is deleted: " + updateResult.getModifiedCount());
				return Mono.empty();
			}).onErrorResume(throwable -> {
				if (throwable instanceof CustomExceptionProduct) {
					return Mono.error(throwable);
				} else {
					return Mono.error(new CustomExceptionProduct(throwable.getMessage()));
				}
			}).then(); // Convert Mono<Object> to Mono<Void>
		} else {
			return Mono.error(new CustomExceptionProduct("Invalid Search Identifiers"));
		}
	}

	@Override
	public Flux<ProductDto> getAllVendorProducts(SearchIdentifiers searchIdentifier) throws CustomExceptionProduct {

		if (ProductUtils.checkValues(searchIdentifier.getProductId())
				&& ProductUtils.checkValues(searchIdentifier.getVariantId())
				&& ProductUtils.checkValues(searchIdentifier.getSellerId())) {
			Query query = Query.query(Criteria.where("sellers._id").is(searchIdentifier.getSellerId()));
			Flux<ProductModel> productFlux = reactiveMongoTemplate.find(query, ProductModel.class);

			Flux<ProductDto> productDtoFlux = productFlux.flatMap(p -> {
				ProductDto pdto = new ProductDto();
				pdto.setProductId(p.getProductId());
				pdto.setProductName(p.getProductName());
				pdto.setCategory(p.getCategory());
				pdto.setDescription(p.getDescription());
				pdto.setManufacture(p.getManufacture());
				pdto.setManufactureDate(p.getManufactureDate());
				pdto.setSellerid(searchIdentifier.getSellerId());
				return Flux.fromIterable(p.getSellers())
						.filter(s -> s.getSellerId().equalsIgnoreCase(searchIdentifier.getSellerId()))
						.flatMap(v -> Mono.just(ProductUtils.populateVariantModels(v.getVariants()))).collectList()
						.map(variants -> {
							pdto.setVariants(variants.stream().flatMap(List::stream).collect(Collectors.toList()));
							return pdto;
						});
			});

			return productDtoFlux;

		}

		else {
			throw new CustomExceptionProduct("Invalid Search Identifiers");
		}

	}

	@Override
	public Flux<VariantsDto> getProductAndVariants(SearchIdentifiers searchIdentifier) throws CustomExceptionProduct {
		if (ProductUtils.checkValues(searchIdentifier.getProductId())
				&& ProductUtils.checkValues(searchIdentifier.getVariantId())
				&& ProductUtils.checkValues(searchIdentifier.getSellerId())) {
			Flux<VariantsDto> variantsFlux = this.getAllVendorProducts(searchIdentifier)
					.filter(p -> p.getProductId().equalsIgnoreCase(searchIdentifier.getProductId()))
					.flatMap(p -> Flux.fromIterable(p.getVariants()));

			return variantsFlux;

		} else {
			throw new CustomExceptionProduct("Invalid Search Identifiers");
		}
	}

	@Override
	public Mono<Void> UpdateStockProduct(SearchIdentifiers searchIdentifier) throws CustomExceptionProduct {
		if (ProductUtils.checkValues(searchIdentifier.getProductId())
				&& ProductUtils.checkValues(searchIdentifier.getVariantId())
				&& ProductUtils.checkValues(searchIdentifier.getSellerId()) && searchIdentifier.getStocksize() != 0) {

			Query query = new Query(Criteria.where("productId").is(searchIdentifier.getProductId()).and("sellers._id")
					.is(searchIdentifier.getSellerId()));
			Flux<ProductModel> productFlux = reactiveMongoTemplate.find(query, ProductModel.class);

			return productFlux.flatMap(product -> {

				Optional<VariantModel> optionalVariant = product.getSellers().stream()
						.filter(seller -> seller.getSellerId().equalsIgnoreCase(searchIdentifier.getSellerId()))
						.flatMap(seller -> seller.getVariants().stream())
						.filter(variant -> variant.getId().equalsIgnoreCase(searchIdentifier.getVariantId()))
						.findFirst();

				if (optionalVariant.isPresent()) {
					VariantModel variantToUpdate = optionalVariant.get();
					variantToUpdate.setStock(variantToUpdate.getStock() + searchIdentifier.getStocksize()); // Update
																											// the stock
																											// to the
																											// desired
																											// value

					// Save/update the entire record
					return reactiveMongoTemplate.save(product).then();
				} else {
					// Variant not found, return the original product without modifications
					return Mono.just(product);
				}
			}).

					then();

		}

		else {
			return Mono.error(new CustomExceptionProduct("Invalid Search Identifiers"));
		}

	}
}
