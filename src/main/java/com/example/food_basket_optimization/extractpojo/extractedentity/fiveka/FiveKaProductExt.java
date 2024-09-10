package com.example.food_basket_optimization.extractpojo.extractedentity.fiveka;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.mapping.jsonmap.annotation.JsonCollection;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.IOException;

@JsonRootName("products")
@JsonCollection
@JsonDeserialize(using = FiveKaProductExt.FiveKaProductDeserializer.class)
public class FiveKaProductExt implements ExtractedEntity {

    public String name;
    public Double price;

    public static class FiveKaProductDeserializer extends JsonDeserializer<FiveKaProductExt> {
        @Override
        public FiveKaProductExt deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {

            TreeNode treeNode = p.getCodec().readTree(p);
            TreeNode pricesNode = treeNode.get("prices");
            FiveKaProductExt productExt = new FiveKaProductExt();
            String regular = pricesNode.get("regular").toString().replace("\"", "");
            productExt.price = Double.parseDouble(regular);
            productExt.name = treeNode.get("name").toString();

            return productExt;
        }
    }
}
