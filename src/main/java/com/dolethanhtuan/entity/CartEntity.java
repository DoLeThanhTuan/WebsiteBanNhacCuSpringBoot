package com.dolethanhtuan.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="cart")
public class CartEntity extends GenericEntity{
	@Column(name = "sumPrice")
	private double sumPrice;
	@Column(name = "quantity")
	private int quantity;
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinTable(name = "cart_product",
			joinColumns = @JoinColumn(name="cart_id"),
			inverseJoinColumns = @JoinColumn(name="product_id")
			)
	private Set<ProductEntity> products = new HashSet<>();
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private UserEntity user;
	@OneToOne(fetch = FetchType.LAZY,mappedBy = "cart")
	private BillEntity bill;
	public double getSumPrice() {
		return sumPrice;
	}
	public void setSumPrice(double sumPrice) {
		this.sumPrice = sumPrice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public CartEntity(double sumPrice, int quantity) {
		super();
		this.sumPrice = sumPrice;
		this.quantity = quantity;
	}
	public CartEntity() {
		super();
	}
	
	
	
	
}
