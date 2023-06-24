package com.ivs.cud.product.ms.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ivs.cud.product.ms.dtos.ProductDto;
import com.ivs.cud.product.ms.dtos.VariantsDto;
import com.ivs.cud.product.ms.exceptions.CustomExceptionProduct;
import com.ivs.cud.product.ms.request.SearchIdentifiers;
import com.ivs.cud.product.ms.service.ProductServiceImpl;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("product/api/v1")
@Validated
@Slf4j
public class ProductController {

	@Autowired
	private ProductServiceImpl productService;

	@PostMapping
	public Mono<ResponseEntity<String>> createProduct(@RequestBody @Valid List<ProductDto> productdto)
			throws JsonMappingException, JsonProcessingException {
		return this.productService.createProduct(productdto)
				.thenReturn(new ResponseEntity<>("Product Created successfully", HttpStatus.OK)).onErrorResume(e -> {
					log.info("Error occurred while Creating the product variant: " + e.getMessage());
					return Mono
							.just(new ResponseEntity<>("Error occurred while Creating the product : " + e.getMessage(),
									HttpStatus.INTERNAL_SERVER_ERROR));
				});
	}

	@PutMapping("/updateStock")
	public Mono<ResponseEntity<String>> updateStock(@RequestBody SearchIdentifiers searchIdentifiers)
			throws CustomExceptionProduct {

		return this.productService.UpdateStockProduct(searchIdentifiers)
				.thenReturn(new ResponseEntity<>("Product Updated successfully", HttpStatus.OK)).onErrorResume(e -> {
					log.info("Error occurred while Updating the product variant: " + e.getMessage());
					return Mono
							.just(new ResponseEntity<>("Error occurred while Updating the product : " + e.getMessage(),
									HttpStatus.INTERNAL_SERVER_ERROR));
				});
	}

	@DeleteMapping("/deleteVariant")
	public Mono<ResponseEntity<String>> deleteVariant(@RequestBody SearchIdentifiers searchIdentifiers) {
		return this.productService.deleteProductVariant(searchIdentifiers)
				.thenReturn(new ResponseEntity<>("Product deleted successfully", HttpStatus.OK)).onErrorResume(e -> {
					log.info("Error occurred while deleting the product variant: " + e.getMessage());
					return Mono
							.just(new ResponseEntity<>("Error occurred while deleting the product : " + e.getMessage(),
									HttpStatus.INTERNAL_SERVER_ERROR));
				});
	}

	@GetMapping
	public ResponseEntity<Flux<ProductDto>> getAllSellerProducts(@RequestBody SearchIdentifiers searchIdentifiers)
			throws CustomExceptionProduct {
		return new ResponseEntity<Flux<ProductDto>>(this.productService.getAllVendorProducts(searchIdentifiers),
				HttpStatus.OK);
	}

	@GetMapping("/product/variants")
	public ResponseEntity<Flux<VariantsDto>> getAllAndProductsandVariants(
			@RequestBody SearchIdentifiers searchIdentifiers) throws CustomExceptionProduct {
		return new ResponseEntity<Flux<VariantsDto>>(this.productService.getProductAndVariants(searchIdentifiers),
				HttpStatus.OK);
	}

}
