package com.ivs.cud.product.ms.dtos;

import java.time.LocalDateTime;
import java.util.List;

import com.ivs.cud.product.ms.models.Specifications;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VariantsDto {

	private String id;
	private String type;
	private String size;
	private String color;
	private List<Specifications> highlights;
	private int stock;
	private double cost;
	private LocalDateTime created;
	private LocalDateTime updated;

}
