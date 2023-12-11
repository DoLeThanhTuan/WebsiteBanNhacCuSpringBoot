package com.dolethanhtuan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dolethanhtuan.dto.CategoryDTO;
import com.dolethanhtuan.repository.CategoryRepository;
import com.dolethanhtuan.service.ICategoryService;
import com.dolethanhtuan.utils.converter.CategoryConverter;

@Component
public class CategoryServiceImpl implements ICategoryService{
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private CategoryConverter cateConverter;
	@Override
	public List<CategoryDTO> findAll() {
		return cateConverter.toListDTO(categoryRepository.findAll());
	}
	@Override
	public CategoryDTO findOneById(int id) {
		return cateConverter.toDTO(categoryRepository.findOneById(id));
	}
}
