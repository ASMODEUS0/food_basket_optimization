package com.example.food_basket_optimization.extractproperties.extractedentity.lenta;

import com.example.food_basket_optimization.entity.Product;
import com.example.food_basket_optimization.extraction.ExtractedEntityMappedObject;
import com.fasterxml.jackson.annotation.JsonRootName;
import java.util.ArrayList;
import java.util.Date;
@JsonRootName(value = "skus")
public class LentaProductExt implements ExtractedEntityMappedObject<Product> {

    public LentaProductExt(){

    }
    public int webControlsShowType;
    public Object description;
    public String quantity;
    public Date promoStart;
    public Date promoEnd;
    public ArrayList<Double> weightOptionsMax;
    public int defaultSelectedWeightOptionIndex;
    public Object subCategoryUrl;
    public int stock;
    public String stockValue;
    public boolean preventIndexing;
    public int position;
    public String proProductType;
    public boolean isProCardSelected;
    public String brand;
    public String code;
    public String title;
    public String subTitle;
    public String imageUrl;
    public String skuUrl;
    public String gaCategory;
    public boolean isWeightProduct;
    public boolean isPromoForRegularPrice;
    public boolean hasDiscount;
    public boolean isEcomEnabled;
    public boolean isInFavorites;
    public boolean isAvailableForOrder;
    public boolean isDeliveryEnabled;
    public boolean isShowEcomCartControl;
    public String kppPageUrl;
    public double averageRating;
    public int commentsCount;
    public boolean showUnavailableToOrderForProError;
    public String unavailableToOrderForProErrorMessage;
    public Object unavailableToOrderForProErrorDescription;
    public boolean isBlur;
    public String placeOutput;
    public boolean isShowOnePrice;
    public int promotionType;
    public int promoPercent;
    public boolean hasAdultContent;
    public boolean hasPrices;
    @Override
    public Product map(Object... args) {
        return null;
    }
}
