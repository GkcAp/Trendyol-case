package com.shoppingcard.business;

import java.util.List;

import com.shoppingcard.ShoppingCart;
import com.shoppingcard.model.Campaign;
import com.shoppingcard.model.Discount;
import com.shoppingcard.model.Product;
import com.shoppingcard.model.ProductStock;

public class CampaignDiscount extends DiscountCalculator {

	@Override
	public double calculateFor(Discount discount, ShoppingCart cart) {
		Campaign campaign = (Campaign) discount; 
		return  calculateFor(campaign,cart);
	}
	
	private double calculateFor(Campaign campaign, ShoppingCart cart) {

	double campaignAmount = 0;
    double amount = 0;
    List<ProductStock> productItems = cart.getProductsByCategory(campaign.getCategory());

    if (cart.getTotalQuantities(productItems) >= campaign.getProductsNumber()) {
    	
    	for (ProductStock productStock : productItems) {
    		Product product = (Product) productStock.getProduct();
    		amount = product.getPrice() * productStock.getQuantity();
		}


        campaignAmount = getDiscountAmount(amount, campaign);
    }
    return campaignAmount;
	    }
	
}
