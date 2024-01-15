package com.example.food_basket_optimization.refresh.deserialization;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Map;
@Setter
@Getter
@Component
public class DeserializationObject {
   private String className;
   private String deserializationClassName;
   private Map<String, String> fieldProperties;
}
