package com.shoppingcard.model;

import com.shoppingcard.ShoppingCart;
import com.shoppingcard.business.DiscountCalculator;


public abstract class Discount {

	private double discount;
	
    private DiscountType discountType;

    private DiscountCalculator calculator;


	public DiscountCalculator getCalculator() {
		return calculator;
	}

	public void setCalculator(DiscountCalculator calculator) {
		this.calculator = calculator;
	}

	public Discount(double discount, DiscountType discountType, DiscountCalculator calculator) {
		this.discount = discount;
		this.discountType = discountType;
		this.calculator = calculator;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public DiscountType getDiscountType() {
		return discountType;
	}

	public void setDiscountType(DiscountType discountType) {
		this.discountType = discountType;
	}
	
    public double calculateFor(ShoppingCart cart) {
        return this.calculator.calculate(this, cart);
    }


}
