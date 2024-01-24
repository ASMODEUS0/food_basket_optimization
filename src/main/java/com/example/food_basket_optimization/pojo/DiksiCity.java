package com.example.food_basket_optimization.pojo;


import com.example.food_basket_optimization.importer.parser.mapping.Attribute;
import com.example.food_basket_optimization.importer.parser.mapping.Select;
import com.example.food_basket_optimization.importer.parser.mapping.TextRootSelect;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Select(value = "a.region-link")
@NoArgsConstructor
public class DiksiCity {
    @Attribute(value = "data-region-id")
    private String id;

    @Select(value = "a.region-link")
    private String name;


}
