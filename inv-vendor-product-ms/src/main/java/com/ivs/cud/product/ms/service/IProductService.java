package com.ivs.cud.product.ms.service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ivs.cud.product.ms.dtos.ProductDto;
import com.ivs.cud.product.ms.dtos.VariantsDto;

import com.ivs.cud.product.ms.exceptions.CustomExceptionProduct;

import com.ivs.cud.product.ms.request.SearchIdentifiers;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProductService {

	public Mono<Void> createProduct(List<ProductDto> products) throws JsonMappingException, JsonProcessingException;

	public Mono<Void> deleteProductVariant(SearchIdentifiers searchIdentifier) throws CustomExceptionProduct;

	public void saveProduct(ProductDto product) throws JsonMappingException, JsonProcessingException;

	public Mono<Void> UpdateStockProduct(SearchIdentifiers searchIdentifier) throws CustomExceptionProduct;

	public Flux<ProductDto> getAllVendorProducts(SearchIdentifiers searchIdentifiers) throws CustomExceptionProduct;

	public Flux<VariantsDto> getProductAndVariants(SearchIdentifiers searchIdentifiers) throws CustomExceptionProduct;
}
