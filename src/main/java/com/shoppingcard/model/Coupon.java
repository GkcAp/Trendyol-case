package com.shoppingcard.model;

import com.shoppingcard.business.CouponDiscount;

public class Coupon extends Discount {

	 private double minAmount;

	    public Coupon(double minAmount, double discount, DiscountType discountType) {
	        super(discount, discountType, new CouponDiscount());
	        this.setMinAmount(minAmount);
	    }

		public double getMinAmount() {
			return minAmount;
		}

		public void setMinAmount(double minAmount) {
			this.minAmount = minAmount;
		}

//	    @Override
//	    public boolean isValid() {
//	        return this.minAmount >= 0 && this.getDiscount() >= 0 && Objects.nonNull(this.getDiscountType());
//	    }
}
