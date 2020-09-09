package com.shoppingcard.business;

import java.util.Objects;

import com.shoppingcard.ShoppingCart;
import com.shoppingcard.model.Discount;
import com.shoppingcard.model.DiscountType;

public abstract class DiscountCalculator {

    public double calculate(Discount discount, ShoppingCart cart){
        double discountAmount = 0;

        if(Objects.nonNull(discount) && Objects.nonNull(cart)){
            discountAmount = calculateFor(discount,cart);
        }
        return discountAmount;
    }

    public double calculateFor(Discount discount, ShoppingCart cart) {
		return 0;
	}

    public double getDiscountAmount(double amount, Discount discount) {

        double discountAmount = 0;

        if (discount.getDiscountType() == DiscountType.PERCENT) {
            discountAmount = amount * (discount.getDiscount() / 100);
        } else if (discount.getDiscountType() == DiscountType.PRICE) {
            discountAmount = discount.getDiscount();
        }

        return Math.min(discountAmount, amount);
    }

}
