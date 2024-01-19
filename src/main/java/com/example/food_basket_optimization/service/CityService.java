package com.example.food_basket_optimization.service;

import com.example.food_basket_optimization.dao.CityRepository;
import com.example.food_basket_optimization.dto.CityReadDto;
import com.example.food_basket_optimization.mapper.CityReadMapper;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;
    private final CityReadMapper cityReadMapper;



    public Optional<CityReadDto> findById(Long id){
        return cityRepository.findById(id)
                .map(cityReadMapper::mapFrom);
    }

    public boolean delete(Long id){
        var mayBeCity = cityRepository.findById(id);
        mayBeCity.ifPresent(city -> cityRepository.delete(id));
        return mayBeCity.isPresent();
    }


}
