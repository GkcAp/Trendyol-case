package com.shoppingcard.model;

import com.shoppingcard.business.CampaignDiscount;

import lombok.NonNull;

public class Campaign extends Discount {

	@NonNull
	private Category category;
	
	private int productsNumber;
		
	public Campaign(Category category, double amount, int numberOfProducts, DiscountType discountType) {
        super(amount, discountType, new CampaignDiscount());
        this.category = category;
        this.productsNumber = numberOfProducts;
    }

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getProductsNumber() {
		return productsNumber;
	}

	public void setProductsNumber(int productsNumber) {
		this.productsNumber = productsNumber;
	}
    
}
