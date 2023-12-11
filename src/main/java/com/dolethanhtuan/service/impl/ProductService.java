package com.dolethanhtuan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.dolethanhtuan.dto.PageRS;
import com.dolethanhtuan.dto.ProductDTO;
import com.dolethanhtuan.entity.ProductEntity;
import com.dolethanhtuan.repository.ProductRepository;
import com.dolethanhtuan.service.IProductService;
import com.dolethanhtuan.utils.converter.ProductConverter;

@Component
public class ProductService implements IProductService{
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ProductConverter productConverter;
	@Override
	public PageRS getListProductByIndex(Pageable page) {
		Page<ProductEntity> pageProE = productRepository.findAll(page);
		List<ProductEntity> listProE = pageProE.getContent();
		return new PageRS(productConverter.toListDTO(listProE), pageProE.getTotalElements(), pageProE.getTotalPages(), pageProE.getSize(), page.getPageNumber());
	}
	
	@Override
	public List<ProductDTO> getAll() {
		return productConverter.toListDTO(productRepository.findAll());
	}
	@Override
	public ProductDTO save(ProductDTO proD) {
		ProductEntity proE = productRepository.save(productConverter.toEntity(proD));
		return productConverter.toDTO(proE);
	}
	@Override
	public ProductDTO update(ProductDTO proD) {
		ProductEntity proE = productRepository.save(productConverter.toEntity(proD));
		return productConverter.toDTO(proE);
	}
	@Override
	public void deleteByIds(long[] ids) {
		for (long id : ids) {
			productRepository.deleteById(id);
		}
	}
	@Override
	public PageRS searchProductByKeywordAndIndex(String keyword, int index) {
		Pageable pageable = PageRequest.of(index-1, 2);
		List<ProductEntity> listAll = productRepository.searchProduct(keyword);
		int begin = (int) pageable.getOffset();
		int end = (int) ((begin + pageable.getPageNumber())> listAll.size() ? listAll.size() : begin+pageable.getPageSize());
		List<ProductEntity> listRS = listAll.subList(begin, end);
		Page<ProductDTO> page = new PageImpl<>(productConverter.toListDTO(listRS), pageable, listAll.size());
		PageRS pageRS = new PageRS(page.getContent(), page.getTotalElements(), page.getTotalPages(), page.getSize(), page.getPageable().getPageNumber());
		return pageRS;
	}
	@Override
	public PageRS getAllByCategoryAndIndex(long cateId, int index) {
		Pageable pageable = PageRequest.of(index-1, 8);
		List<ProductEntity> listAll = productRepository.findAllByCategory_id(cateId);
		int n = listAll.size();
		int begin = (int) pageable.getOffset();
		int end = (int) ((begin + pageable.getPageSize())> n ? n : begin+pageable.getPageSize());
		if(begin >= n)
			return null;
		listAll = listAll.subList(begin, end);
		Page<ProductDTO> page = new PageImpl<>(productConverter.toListDTO(listAll), pageable, n);
		return new PageRS(page.getContent(), page.getTotalElements(),page.getTotalPages(),page.getSize(), page.getPageable().getPageNumber());
	}
	@Override
	public List<ProductDTO> getAllByCateId(int cateId) {
		return productConverter.toListDTO(productRepository.findAllByCategory_id(cateId));
	}
	@Override
	public ProductDTO findOneById(long id) {
		return productConverter.toDTO(productRepository.findOneById(id));
	}
	@Override
	public List<ProductDTO> getAllRelative(int count, long idCate) {
		List<ProductDTO> listAll = productConverter.toListDTO(productRepository.findAllByCategory_id(idCate));
		int begin = 0;
		int end = listAll.size() > count ? count : listAll.size();
		listAll = listAll.subList(begin, end);
		return listAll;
	}
	@Override
	public PageRS getAllByCategory_idAndtxtSearch(int index,long category_id, String txtSearch) {
		Pageable pageable = PageRequest.of(index-1, 8);
		List<ProductEntity> listAll = productRepository.findAllByCategory_idAndProductNameLike(category_id, txtSearch) ;
		int begin = (int) pageable.getOffset();
		int end = (int)(listAll.size() > begin+pageable.getPageSize() ? begin+pageable.getPageSize() : listAll.size());
		if(begin > listAll.size())
			return null;
		List<ProductEntity> listERS = listAll.subList(begin, end);
		Page<ProductDTO> page = new PageImpl<>(productConverter.toListDTO(listERS), pageable, listAll.size());
		PageRS pageRS = new PageRS(page.getContent(), page.getTotalElements(), page.getTotalPages(), page.getSize(),page.getPageable().getPageNumber());
		return pageRS;
	}
}
