package com.ivs.cud.product.ms.models;

import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SellerModel {
	
	@Id
	private String sellerId;
	
	private List<VariantModel> variants;

}
