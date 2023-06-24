package com.ivs.cud.product.ms.dtos;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ProductDto {

	@NotEmpty(message = "Product Id is mandatory")
	private String productId;
	@NotEmpty(message = "Product Name is mandatory")
	private String productName;
	@NotEmpty(message = "Product Description is mandatory")
	private String description;
	@NotEmpty(message = "Product Manufacture is mandatory")
	private String manufacture;
	@NotEmpty(message = "Product Category is mandatory")
	private String category;
	@NotEmpty(message = "Product Manufacture Date is mandatory")
	private String manufactureDate;

	private String sellerid;

	private List<VariantsDto> variants;

}
