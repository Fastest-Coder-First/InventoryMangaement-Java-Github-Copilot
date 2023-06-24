package com.ivs.cud.product.ms.utils;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivs.cud.product.ms.dtos.ProductDto;
import com.ivs.cud.product.ms.dtos.VariantsDto;
import com.ivs.cud.product.ms.models.ProductModel;
import com.ivs.cud.product.ms.models.SellerModel;
import com.ivs.cud.product.ms.models.VariantModel;

public class ProductUtils {

	public static ObjectMapper objectMapper = new ObjectMapper();

	public static ProductDto entityToDto(ProductModel productModel)
			throws JsonMappingException, JsonProcessingException {
		ProductDto productdto = objectMapper.readValue(objectMapper.writeValueAsString(productModel), ProductDto.class);
		return productdto;
	}

	public static ProductModel DTOToEntity(ProductDto productdto) throws JsonMappingException, JsonProcessingException {
		ProductModel product = objectMapper.readValue(objectMapper.writeValueAsString(productdto), ProductModel.class);
		List<SellerModel> sellers = new ArrayList<>();
		sellers.add(populateSeller(productdto));
		product.setSellers(sellers);
		return product;
	}

	public static SellerModel populateSeller(ProductDto productdto) {

		List<VariantModel> variants = new ArrayList<>();
		productdto.getVariants().stream().forEach((variant) -> {

			VariantModel s;
			try {
				s = objectMapper.readValue(objectMapper.writeValueAsString(variant), VariantModel.class);
				variants.add(s);
			} catch (JsonMappingException e) {

				e.printStackTrace();
			} catch (JsonProcessingException e) {

				e.printStackTrace();
			}

		});
		SellerModel s = new SellerModel();
		s.setSellerId(productdto.getSellerid());
		s.setVariants(variants);
		return s;
	}

	public static List<VariantsDto> populateVariantModels(List<VariantModel> models) {
		List<VariantsDto> variants = new ArrayList<>();
		for (VariantModel variant : models) {
			VariantsDto vdto;

			try {
				vdto = objectMapper.readValue(objectMapper.writeValueAsString(variant), VariantsDto.class);
				variants.add(vdto);
			} catch (JsonMappingException e) {

				e.printStackTrace();
			} catch (JsonProcessingException e) {

				e.printStackTrace();
			}
		}

		return variants;
	}

	public static boolean checkValues(String msg) {
		if (msg == null || msg.isEmpty()) {
			return false;
		}

		else

		{
			return true;
		}
	}

}
