package com.example.food_basket_optimization.castommapping;

import com.example.food_basket_optimization.extractpojo.extractedentity.diksi.DiksiCityInfoExt;
import com.example.food_basket_optimization.extractpojo.extractedentity.diksi.DiksiShopExt;
import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.mapping.jsonmap.CustomJsonMapper;
import com.example.food_basket_optimization.extraction.properties.mapping.MapProperty;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DikcyCityInfoJsonMapper implements CustomJsonMapper {

    @Override
    public List<ExtractedEntity> map(List<? extends MapProperty> properties) {

        List<ExtractedEntity> entities = new ArrayList<>();

        for(MapProperty property: properties){
            if(property.getClassToMap() != DiksiCityInfoExt.class && property.getClassToMap() == null){
                throw new IllegalArgumentException();
            }
            String data = property.getData();

            JsonObject jsonObject = JsonParser.parseString(data).getAsJsonObject();

            Map<String, JsonElement> map = jsonObject.asMap();

            ArrayList<DiksiCityInfoExt> diksiCityInfoExts = new ArrayList<>();
            map.forEach((cityName, object) -> {

                JsonElement citiesElement = object.getAsJsonObject().get("cities");
                Map<String, JsonElement> citiesMap = citiesElement.getAsJsonObject().asMap();

                List<DiksiCityInfoExt> citiesCur = new ArrayList<>();
                citiesMap.forEach((cityName1, object1) -> {

                    List<DiksiShopExt> shops = new ArrayList<>();

                    JsonElement shopsElements = object1.getAsJsonObject().get("shops");
                    JsonArray shopsArray = shopsElements.getAsJsonArray();
                    shopsArray.forEach(shop -> {
                        Map<String, JsonElement> shopMap = shop.getAsJsonObject().asMap();
                        DiksiShopExt shopCur = DiksiShopExt.builder()
                                .adress(shopMap.get("adress").getAsString())
                                .latitude(shopMap.get("longitude").getAsDouble())
                                .longitude(shopMap.get("longitude").getAsDouble())
                                .openingHours(shopMap.get("opening_hours").getAsString())
                                .build();
                        shops.add(shopCur);
                    });


                    DiksiCityInfoExt res = DiksiCityInfoExt.builder()
                            .cityName(cityName1)
                            .longitude(object1.getAsJsonObject().get("longitude").getAsDouble())
                            .latitude(object1.getAsJsonObject().get("latitude").getAsDouble())
                            .shops(shops)
                            .build();


                    citiesCur.add(res);
                });

                DiksiCityInfoExt res = DiksiCityInfoExt.builder()
                        .cityName(cityName)
                        .longitude(object.getAsJsonObject().get("longitude").getAsDouble())
                        .latitude(object.getAsJsonObject().get("latitude").getAsDouble())
                        .cities(citiesCur)
                        .build();
                diksiCityInfoExts.add(res);

            });

            entities.addAll(diksiCityInfoExts);

        }
        return entities;
    }

}
