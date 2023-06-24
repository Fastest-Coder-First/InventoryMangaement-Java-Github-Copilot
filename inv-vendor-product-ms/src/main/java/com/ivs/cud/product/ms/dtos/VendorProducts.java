package com.ivs.cud.product.ms.dtos;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendorProducts {
	
	private Map<String, List<CategoryDto>> vendorProducts ;

}
