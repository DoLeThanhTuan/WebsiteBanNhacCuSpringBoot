package com.dolethanhtuan.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dolethanhtuan.dto.PageRS;
import com.dolethanhtuan.dto.ProductDTO;

@Service
public interface IProductService {
	PageRS getListProductByIndex(Pageable page);
	List<ProductDTO> getAll();
	ProductDTO save(ProductDTO proD);
	ProductDTO update(ProductDTO proD);
	void deleteByIds(long[] id);
	PageRS searchProductByKeywordAndIndex(String keyword, int index);
	List<ProductDTO> getAllByCateId(int cateId);
	PageRS getAllByCategoryAndIndex(long cateId,int index);
	ProductDTO findOneById(long id);
	List<ProductDTO> getAllRelative(int count,long idCate);
	PageRS getAllByCategory_idAndtxtSearch(int index, long category_id,String txtSearch);
}	
