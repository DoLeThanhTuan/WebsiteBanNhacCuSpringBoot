package com.dolethanhtuan.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "category")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CategoryEntity extends GenericEntity{
	@Column(name = "cateName", nullable = false,length = 500)
	private String cateName;
	@Column(name = "cateDes", columnDefinition = "TEXT")
	private String description;
	@Column(name = "thumbnail", columnDefinition = "TEXT")
	private String thumbnail;
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "category")
	private Set<ProductEntity> products = new HashSet<>();
	
	
}
