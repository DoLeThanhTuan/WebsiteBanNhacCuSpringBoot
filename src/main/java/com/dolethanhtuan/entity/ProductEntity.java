package com.dolethanhtuan.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@Entity
@Table(name="product")
public class ProductEntity extends GenericEntity{
	@Column(name="productName",length = 400)
	private String productName;
	@Column(name ="price")
	private double price;
	@Column(name = "quantity")
	private int quantity;
	@Column(name="image")
	private String image;
	@Column(name="description",columnDefinition = "text")
	private String description;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private CategoryEntity category;
	@ManyToMany(fetch = FetchType.LAZY,mappedBy ="products")
	private Set<CartEntity> carts = new HashSet<>();
	
	
	
	public ProductEntity(String productName, double price, int quantity) {
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
	}
	public ProductEntity() {
		super();
	}
	
	public ProductEntity(String productName, double price, int quantity, CategoryEntity category) {
		super();
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
		this.category = category;
	}

	
	
	
	
}
