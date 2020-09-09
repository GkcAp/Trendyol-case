package com.shoppingcard.business;

import com.shoppingcard.ShoppingCart;
import com.shoppingcard.model.Coupon;
import com.shoppingcard.model.Discount;

public class CouponDiscount extends DiscountCalculator {

	
	@Override
	public double calculateFor(Discount discount, ShoppingCart cart) {
        Coupon coupon = (Coupon) discount;
		return  calculateFor(coupon,cart);
	}
	
    private double calculateFor(Coupon coupon, ShoppingCart cart) {
        double couponDiscount = 0;
        double cartRemainingAmount = cart.getRemainingAmountAfterApplyingCampaign();

        if (cartRemainingAmount >= coupon.getMinAmount()) {
            couponDiscount = getDiscountAmount(cartRemainingAmount, coupon);
        }

        return couponDiscount;
    }
	
}
