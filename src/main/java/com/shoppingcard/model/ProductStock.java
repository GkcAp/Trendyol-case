package com.shoppingcard.model;

public class ProductStock{

	Product product;
	
	private int quantity;
	
	public ProductStock(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		// TODO Auto-generated method stub
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}

	
	
}
