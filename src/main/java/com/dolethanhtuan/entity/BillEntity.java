package com.dolethanhtuan.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bill")

public class BillEntity extends GenericEntity{
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cart_id")
	private CartEntity cart;
	@Column(name="status")
	private int status;
	public CartEntity getCart() {
		return cart;
	}
	public void setCart(CartEntity cart) {
		this.cart = cart;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
