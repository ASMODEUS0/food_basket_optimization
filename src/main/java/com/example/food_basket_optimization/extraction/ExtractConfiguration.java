package com.example.food_basket_optimization.extraction;

import com.example.food_basket_optimization.extraction.properties.root.ExtractionProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Setter
@Getter

@Component
public class ExtractConfiguration {

    private final List<ExtractionProperties<?>> propertiesHttp;

    public ExtractConfiguration(List<ExtractionProperties<?>> propertiesHttp) {
        this.propertiesHttp = propertiesHttp;
    }
}
