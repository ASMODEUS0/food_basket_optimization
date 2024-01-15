package com.example.food_basket_optimization.mapper;

import com.example.food_basket_optimization.dto.CityReadDto;
import com.example.food_basket_optimization.entity.City;

public class CityReadMapper implements Mapper<City, CityReadDto>{
    @Override
    public CityReadDto mapFrom(City object) {
        return  new CityReadDto(object.getId(),
                object.getRegion(),
                object.getRegionType(),
                object.getNameOfCity(),
                object.getLatitude(),
                object.getLongitude(),
                object.getTimeZone());

    }
}
