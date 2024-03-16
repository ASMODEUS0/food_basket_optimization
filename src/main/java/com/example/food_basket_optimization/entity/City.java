package com.example.food_basket_optimization.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class City implements  BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String region;
    @Column(name = "reg_type")
    private String regionType;
    @Column(name = "name_of_city")
    private String nameOfCity;
    private Double latitude;
    private Double longitude;
    @Column(name = "time_zone")
    private String timeZone;


}
