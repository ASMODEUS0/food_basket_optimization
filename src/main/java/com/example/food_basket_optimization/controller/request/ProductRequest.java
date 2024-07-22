package com.example.food_basket_optimization.controller.request;

import com.example.food_basket_optimization.entity.ShopType;

import java.util.Set;

public class ProductRequest {

   public String productName;
   public Set<ShopType> shops;

   public ProductRequest (String productName,
                          Set<ShopType> shops){
      this.productName = productName;
      this.shops = shops;
   }


}
