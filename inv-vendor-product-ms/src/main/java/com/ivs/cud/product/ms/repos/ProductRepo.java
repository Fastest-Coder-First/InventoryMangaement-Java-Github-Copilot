package com.ivs.cud.product.ms.repos;

import java.util.Optional;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.ivs.cud.product.ms.models.ProductModel;

public interface ProductRepo extends ReactiveMongoRepository<ProductModel, String> {
	
	Optional<ProductModel> findByProductId(String productId);
	

		

}
