package com.dolethanhtuan.utils.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.dolethanhtuan.dto.CategoryDTO;
import com.dolethanhtuan.entity.CategoryEntity;

@Component
public class CategoryConverter {
	public CategoryEntity toEntity(CategoryDTO cateD) {
		CategoryEntity cateE = new CategoryEntity();
		if(cateD.getId() != null)
			cateE.setId(cateD.getId());
		if(cateD.getCategoryName() != null)
			cateE.setCateName(cateD.getCategoryName());
		if(cateD.getCategoryDes() != null)
			cateE.setDescription(cateD.getCategoryDes());
		if(cateD.getThumbnail() != null)
			cateE.setThumbnail(cateD.getThumbnail());
		return cateE;
	}
	public CategoryDTO toDTO(CategoryEntity cateE) {
		return new CategoryDTO(cateE.getId(), cateE.getCateName(), cateE.getDescription(), cateE.getThumbnail());
	}
	public List<CategoryDTO> toListDTO(List<CategoryEntity> listCateE){
		List<CategoryDTO> listD = new ArrayList<>();
		for (CategoryEntity cateE : listCateE) {
			listD.add(new CategoryDTO(cateE.getId(), cateE.getCateName(), cateE.getDescription(), cateE.getThumbnail()));
		}
		return listD;
	}
	public List<CategoryEntity> toListE(List<CategoryDTO> listD){
		List<CategoryEntity> listE = new ArrayList<>();
		for (CategoryDTO cateD : listD) {
			listE.add(toEntity(cateD));
		}
		return listE;
	}
}
