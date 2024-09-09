package com.example.food_basket_optimization.extractpojo.extractedentity.lenta;

import com.example.food_basket_optimization.entity.Product;
import com.example.food_basket_optimization.extraction.ExtractedEntityMappedObject;
import com.example.food_basket_optimization.extraction.properties.mapping.jsonmap.annotation.JsonCollection;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.IOException;

@JsonCollection
@JsonRootName(value = "items")

@JsonDeserialize(using = LentaProductExt.LentaProductDeserializer.class)
public class LentaProductExt implements ExtractedEntityMappedObject<Product> {

    public double price;
    public double priceRegular;
    public String name;

    public LentaProductExt() {
    }

    public static class LentaProductDeserializer extends JsonDeserializer<LentaProductExt> {
        @Override
        public LentaProductExt deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            TreeNode treeNode = p.getCodec().readTree(p);
            TreeNode pricesNode = treeNode.get("prices");

            LentaProductExt productExt = new LentaProductExt();
            productExt.price = Double.parseDouble(pricesNode.get("price").toString());
            productExt.priceRegular = Double.parseDouble(pricesNode.get("priceRegular").toString());
            productExt.name = treeNode.get("name").toString();

            return productExt;
        }
    }


    public Product map(Object... args) {
        return null;
    }
}
