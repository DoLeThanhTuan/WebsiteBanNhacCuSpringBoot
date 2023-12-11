package com.dolethanhtuan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CategoryDTO extends GenericDTO{
	private String categoryName;
	private String categoryDes;
	private String thumbnail;
	public CategoryDTO(Long id, String categoryName, String categoryDes, String thumbnail) {
		super(id);
		this.categoryName = categoryName;
		this.categoryDes = categoryDes;
		this.thumbnail = thumbnail;
	}
	
	
}
