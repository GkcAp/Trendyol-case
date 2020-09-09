package com.shoppingcard.business;

import java.util.Objects;

import com.shoppingcard.ShoppingCart;
import com.shoppingcard.model.Coupon;
import com.shoppingcard.model.Discount;

public class DeliveryCost implements IntDeliveryCost {

	private double deliveryCost;

	private double costPerProduct;
	
    private double fixedCost;
    
    public double getDeliveryCost() {
		return deliveryCost;
	}

	public void setDeliveryCost(double deliveryCost) {
		this.deliveryCost = deliveryCost;
	}

	public double getCostPerProduct() {
		return costPerProduct;
	}

	public void setCostPerProduct(double costPerProduct) {
		this.costPerProduct = costPerProduct;
	}

	public double getFixedCost() {
		return fixedCost;
	}

	public void setFixedCost(double fixedCost) {
		this.fixedCost = fixedCost;
	}


    public DeliveryCost(double deliveryCost, double costPerProduct, double fixedCost) {
        this.deliveryCost = Math.max(deliveryCost, 0);
        this.costPerProduct = Math.max(costPerProduct, 0);
        this.fixedCost = Math.max(fixedCost, 0);
    }

    public double calculateFor(ShoppingCart shoppingCart) {

        double deliveryCost = 0;

        if (Objects.nonNull(shoppingCart) && !shoppingCart.getproductStockList().isEmpty()) {

            deliveryCost = (shoppingCart.getNumberOfDelivers() * deliveryCost)
                    + (shoppingCart.getNumberOfProducts() * costPerProduct)
                    + fixedCost;
        }

        return deliveryCost;
    }
}
