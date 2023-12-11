package com.dolethanhtuan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dolethanhtuan.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>{
	
	@Query("FROM ProductEntity c WHERE c.productName LIKE %?1%")
	List<ProductEntity> searchProduct(String name);
	List<ProductEntity> findAllByCategory_id(long id);
	ProductEntity findOneById(long id);
	List<ProductEntity> findAllByCategory_idAndProductNameLike(long category_id,String txtSearch);
}
