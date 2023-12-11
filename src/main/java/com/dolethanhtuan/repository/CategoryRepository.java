package com.dolethanhtuan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dolethanhtuan.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>{
	CategoryEntity findOneById(long id);
}
