package com.ivs.cud.product.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;

import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
@EnableReactiveMongoAuditing
public class InvVendorProductMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvVendorProductMsApplication.class, args);
	}

}
