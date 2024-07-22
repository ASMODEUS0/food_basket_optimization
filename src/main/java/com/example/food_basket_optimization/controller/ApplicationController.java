package com.example.food_basket_optimization.controller;

import com.example.food_basket_optimization.controller.request.ProductRequest;
import com.example.food_basket_optimization.controller.request.ShopRequest;
import com.example.food_basket_optimization.dto.NearStoreDto;
import com.example.food_basket_optimization.dto.ProductDto;
import com.example.food_basket_optimization.dto.StoreDto;
import com.example.food_basket_optimization.geolocation.GeolocationUtil;
import com.example.food_basket_optimization.repository.ProductRepository;
import com.example.food_basket_optimization.repository.StoreRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@RestController()
public class ApplicationController {

    private final StoreRepository storeRepository;
    private final ProductRepository productRepository;

    public ApplicationController(StoreRepository storeRepository, ProductRepository productRepository){
        this.storeRepository = storeRepository;
        this.productRepository = productRepository;
    }

    @PostMapping("/shops")
    List<NearStoreDto> get(@RequestBody ShopRequest request){

        List<StoreDto> stores = storeRepository.getAll();
        List<NearStoreDto> nearStores = new java.util.ArrayList<>(stores.stream().map(store -> new NearStoreDto(store, GeolocationUtil.calculateDistanceBetweenPoints(store.latitude(),
                        store.longitude(),
                        request.latitude,
                        request.longitude)))
                .toList());

        nearStores.sort(Comparator.comparing(NearStoreDto::distance));
        return nearStores.subList(0, request.limit);
    }



    @PostMapping("/products")
    List<ProductDto> getProducts(@RequestBody ProductRequest request){
        return productRepository.byRequest(request);
    }

}
