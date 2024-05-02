package com.example.food_basket_optimization.extractpojo.extractedentity.diksi;

import com.example.food_basket_optimization.entity.City;
import com.example.food_basket_optimization.extraction.ExtractedEntityMappedObject;
import com.example.food_basket_optimization.extraction.mapper.MapByEquals;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Builder
@AllArgsConstructor
@Getter
public class DiksiCityInfoExt implements ExtractedEntityMappedObject<List<City>> {
    private String cityName;
    private double longitude;
    private double latitude;
    private List<DiksiShopExt> shops;
    private List<DiksiCityInfoExt> cities;

    @MapByEquals(unionClazz = DiksiCityExtr.class, ownField = "cityName", unionField = "name")
    @Override
    public List<City> map(Object... args) {
        Object[] array = Arrays.stream(args).toArray();
        if(array[0] != null && array[0] instanceof DiksiCityExtr diksiCityExtr){

            ArrayList<City> result = new ArrayList<>();
//            cities.stream().map()


        }

        return null;
    }
}
