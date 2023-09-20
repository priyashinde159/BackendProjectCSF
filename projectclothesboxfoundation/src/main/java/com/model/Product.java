package com.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Product")
public class Product {
	@Id
	private int productId;
	private String productName;
	private int productPrice;
	@ManyToOne
	@JoinColumn(name = "userId", nullable = false)
	private User user;

	public Product() {
		super();
	}

	public Product(int productId, String productName, int productPrice, User user) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.user = user;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productPrice=" + productPrice
				+ "]";
	}

}
