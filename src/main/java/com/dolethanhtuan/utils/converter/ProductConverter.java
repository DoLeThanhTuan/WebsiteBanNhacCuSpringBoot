package com.dolethanhtuan.utils.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dolethanhtuan.dto.ProductDTO;
import com.dolethanhtuan.entity.ProductEntity;
import com.dolethanhtuan.repository.CategoryRepository;

@Component
public class ProductConverter {
	@Autowired
	private CategoryConverter cateConverter;
	@Autowired
	private CategoryRepository cateRepository;
	public ProductEntity toEntity(ProductDTO productD) {
		ProductEntity productE = new ProductEntity();
		if(productD.getId() != null)
			productE.setId(productD.getId());
		if(productD.getProductName() != null)
			productE.setProductName(productD.getProductName());
		if(productD.getPrice() != null)
			productE.setPrice(productD.getPrice());
		if(productD.getQuantity() != null) 
			productE.setQuantity(productD.getQuantity());
		if(productD.getImage() != null)
			productE.setImage(productD.getImage());
		if(productD.getDescription() != null)
			productE.setDescription(productD.getDescription());
		if(productD.getCategory() != null && productD.getCategory().getId() != null)
			productE.setCategory(cateRepository.findOneById(productD.getCategory().getId()));
		return productE;
	}
	public ProductDTO toDTO(ProductEntity productE) {
		ProductDTO productD = new ProductDTO(productE.getProductName(), productE.getPrice(), productE.getQuantity(), productE.getImage(),productE.getDescription(),cateConverter.toDTO(productE.getCategory()));
		productD.setId(productE.getId());
		return productD;
	}
	public List<ProductEntity> toListEntity(List<ProductDTO> listD){
		List<ProductEntity> listE = new ArrayList<>();
		for (ProductDTO pd : listD) 
			listE.add(this.toEntity(pd));
		return listE;
	}
	public List<ProductDTO> toListDTO(List<ProductEntity> listE){
		List<ProductDTO> listD = new ArrayList<>();
		for (ProductEntity productE : listE) 
			listD.add(this.toDTO(productE));
		return listD;
	}
}
