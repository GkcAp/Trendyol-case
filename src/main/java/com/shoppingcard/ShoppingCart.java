package com.shoppingcard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.shoppingcard.business.IntDeliveryCost;
import com.shoppingcard.model.Campaign;
import com.shoppingcard.model.Category;
import com.shoppingcard.model.Coupon;
import com.shoppingcard.model.Product;
import com.shoppingcard.model.ProductStock;

public class ShoppingCart {


	private List<ProductStock> productStockList = new ArrayList<>();

    private List<Campaign> campaigns = new ArrayList<>();

	private Coupon coupon;

    private IntDeliveryCost intDeliveryCost;

	public ShoppingCart(List<ProductStock> productStockList, List<Campaign> campaigns, Coupon coupon,
 			IntDeliveryCost deliveryCostCalculator) {
 		super();
 		this.productStockList = productStockList;
 		this.campaigns = campaigns;
 		this.coupon = coupon;
 		this.intDeliveryCost = deliveryCostCalculator;
 	}
     
     public ShoppingCart() {
 	
 	}
    
    public List<ProductStock> getProductStockList() {
		return productStockList;
	}

	public void setProductStockList(List<ProductStock> productStockList) {
		this.productStockList = productStockList;
	}

	public List<Campaign> getCampaigns() {
		return campaigns;
	}

	public void setCampaigns(List<Campaign> campaigns) {
		this.campaigns = campaigns;
	}

	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}
    
    public IntDeliveryCost getDeliveryCostCalculator() {
		return intDeliveryCost;
	}
    
	public void setDeliveryCostCalculator(IntDeliveryCost deliveryCostCalculator) {
		this.intDeliveryCost = deliveryCostCalculator;
	}
	


    public void addItem(Product product, int quantity) {
        if (Objects.nonNull(product) && quantity > 0) {
            this.productStockList.add(new ProductStock(product, quantity));
        }
    }
    
    public void addProductDetail(ProductStock productDetail) {
        if (Objects.nonNull(productDetail) && productDetail.getQuantity() > 0) {
            this.productStockList.add(productDetail);
        }
    }

    public void applyDiscounts(Campaign... campaigns) {
        if (Objects.nonNull(campaigns)) {
            Arrays.stream(campaigns)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toCollection(() -> this.campaigns));
        }
    }

    public void applyCoupon(Coupon coupon) {
        if (Objects.nonNull(coupon) ) { //TODO && coupon.isValid()
            this.coupon = coupon;
        }
    }

    public double getTotalAmountAfterDiscounts() {
        double amount = getRemainingAmountAfterApplyingCampaign() - getCouponDiscount();
        return amount < 0 ? 0 : amount;
    }

    public double getRemainingAmountAfterApplyingCampaign() {
        double amount = getTotalAmount() - getCampaignDiscount();
        return amount < 0 ? 0 : amount;
    }

    private double getTotalAmount() {
    	Product product = new Product();
    	double totalAmount = 0;
    	for (ProductStock productStock : productStockList) {
    		product = (Product) productStock.getProduct();
    		totalAmount = product.getPrice() * productStock.getQuantity();
		}
        return totalAmount;
    }

    public double getCampaignDiscount() {
    	double campaignDiscount = 0;
    	for (Campaign campaign : campaigns) {
    		double camp = campaign.calculateFor(this);
    		if (campaignDiscount < camp)
    			campaignDiscount = camp;
		}
        return campaignDiscount;
    }

    public double getCouponDiscount() {

        double couponDiscount = 0;
        if (Objects.nonNull(this.coupon)) {
            couponDiscount = this.coupon.calculateFor(this);
        }
        return couponDiscount;
    }

    public List<ProductStock> getProductsByCategory(Category category) {
    	List<ProductStock> productStockListResult = new ArrayList<>();
    	for (Category categry : category.getCategories()) {
    		for (ProductStock productStock : productStockList) {
				Product product = (Product) productStock.getProduct();
				if (categry.equals(product.getCategory())){
					productStockListResult.add(productStock);
				}
			}
		}
        return productStockListResult;
    }

    public int getTotalQuantities(List<ProductStock> ProductDetails) {
        return ProductDetails.stream().mapToInt(ProductStock::getQuantity).sum();
    }

    public double getDeliveryCost() {

        double deliveryCost = 0;
        if (!this.getproductStockList().isEmpty() && Objects.nonNull(this.intDeliveryCost)) {
            deliveryCost = this.intDeliveryCost.calculateFor(this);
        }
        return deliveryCost;
    }

    public List<ProductStock> getproductStockList() {
		return this.getProductStockList();
	}

	public int getNumberOfDelivers() {
        return this.productStockList.stream().map(ProductDetail -> ProductDetail.getProduct().getCategory()).collect(Collectors.toSet()).size();
    }

    public int getNumberOfProducts() {
        return this.productStockList.size();
    }

    public String print() {

        StringBuilder msg = new StringBuilder();

        if (!this.productStockList.isEmpty()) {
        	
        	for (ProductStock productStock : productStockList) {
        		msg.append("\n\nProducts ");
        		Product product = new Product();
        		product = productStock.getProduct();
        		String cate = productStock.getProduct().getCategory().getTitle();
              msg.append("----------Category").append(cate);
              msg.append("----------ProductDetails").append(productStock);
              msg.append("\n--> Product Name :\t").append(product.getTitle());
              msg.append("\n--- Quantity     :\t").append(productStock.getQuantity());
              msg.append("\n--- Price   :\t").append(product.getPrice());
              msg.append("\n--- Total Price  :\t").append(productStock.getQuantity() * product.getPrice());

			}
          msg.append("\n----------------------");
          msg.append("\nTOTAL DISCOUNT  : ").append((getCampaignDiscount() + getCouponDiscount()));
          msg.append("\nTOTAL AMOUNT    : ").append(getTotalAmount());
          msg.append("\nDELIVERY COST   : ").append(getDeliveryCost());

        }

        return msg.toString();
    }

}
