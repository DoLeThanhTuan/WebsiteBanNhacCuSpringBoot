package com.dolethanhtuan.dto;

import java.text.DecimalFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductDTO extends GenericDTO{
	private String productName;
	private Double price;
	private Integer quantity;
	private String image;
	private String description;
	private CategoryDTO category;
	public String viewPrice() {
		DecimalFormat df = new DecimalFormat("###,###.##");
		return df.format(price);
	}
}
