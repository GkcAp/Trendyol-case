package com.shoppingcard;

import com.shoppingcard.business.DeliveryCost;
import com.shoppingcard.business.IntDeliveryCost;
import com.shoppingcard.model.Campaign;
import com.shoppingcard.model.Category;
import com.shoppingcard.model.Coupon;
import com.shoppingcard.model.DiscountType;
import com.shoppingcard.model.Product;

public class Prototype {

    public static void main(String args[]) {

        ShoppingCart shoppingCart = new ShoppingCart();

        //ana kategori
        Category kozmetik = new Category("Kozmetik");

        //child kategori
        Category maskara = new Category("Maskara");
        Category sudaAkmayan = new Category("Suda Akmayan");
        Category maviRenkli = new Category("Mavi Renkli");
        Category hacimliKirpikler = new Category("Hacimli Kirpkiler");

        maskara.add(sudaAkmayan);
        maskara.add(maviRenkli);
        maskara.add(hacimliKirpikler);

        //child kategori
        Category ruj = new Category("Ruj");
        Category likitRuj = new Category("Likit Ruj");
        Category matRuj = new Category("Mat Ruj");
        Category kaliciRuj = new Category("Kalici Ruj");

        ruj.add(likitRuj);
        ruj.add(matRuj);
        ruj.add(kaliciRuj);

        kozmetik.add(maskara);
        kozmetik.add(ruj);

        //Products
        Product maybellineMaskara = new Product("Maybelline Maskara", 135, maskara);
        Product macMaskara = new Product("Mac Maskara", 135, maskara);
        
        Product macRuj = new Product("Mac Ruj", 100, ruj);
        Product lorealMatRuj = new Product("Loreal Mat Ruj", 200, matRuj);


        //kuponlar
        Coupon kupon = new Coupon(1000, 100, DiscountType.PRICE);
        Coupon kozmetikFýrsatý = new Coupon(100, 30, DiscountType.PERCENT);


        //kampanyalar
        Campaign macSeninMarkan = new Campaign(ruj, 15, 2, DiscountType.PERCENT);
        Campaign maskaradaKacmazFýrsat = new Campaign(maskara, 20, 2, DiscountType.PERCENT);
        Campaign güzelleþtirKendini = new Campaign(matRuj, 20, 1, DiscountType.PRICE);


        shoppingCart.addItem(maybellineMaskara, 5);
        shoppingCart.addItem(macMaskara, 2);
        shoppingCart.applyDiscounts(macSeninMarkan, maskaradaKacmazFýrsat);
        shoppingCart.applyCoupon(kupon);

        System.out.println(shoppingCart.print());

        IntDeliveryCost intdeliveryCost = new DeliveryCost(2,1, Constants.FIXED_COST);
        shoppingCart.setDeliveryCostCalculator(intdeliveryCost);
        
//        System.out.println(shoppingCart.print());

        //******************************//

//        shoppingCart.addItem(lorealMatRuj, 1);
//        shoppingCart.applyCoupon(kozmetikFýrsatý);
//        System.out.println(shoppingCart.print());
//        //******************************//
//        shoppingCart.setIntDeliveryCost(new new DeliveryCostCalculatorImpl(2,1, DeliveryConstants.FIXED_COST)(2,1, Constants.FIXED_COST)); //TODO DeliveryConstants.FIXED_COST
//        System.out.println(shoppingCart.print());


    }
}
