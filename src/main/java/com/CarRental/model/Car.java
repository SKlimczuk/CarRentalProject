package com.CarRental.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class Car {
    private int id;
    private String car_brand;
    private String car_model;
    private int production_year;
    private int car_mileage;
}
