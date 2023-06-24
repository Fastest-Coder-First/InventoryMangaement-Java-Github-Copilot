package com.ivs.cud.product.ms.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchIdentifiers {
	
	private String sellerId;
	private String variantId;
	private String productId;
	private int stocksize;	

}
