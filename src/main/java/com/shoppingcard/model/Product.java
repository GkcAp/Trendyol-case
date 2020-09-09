package com.shoppingcard.model;

public class Product extends BaseDto {
	
    private int price;

    private Category category;

	public Product(String string, int i, Category category) {
		this.title = string ;
		this.category = category;
		this.price = i;
	}
	
	public Product() {
	

	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
