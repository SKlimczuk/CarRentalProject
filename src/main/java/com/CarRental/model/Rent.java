package com.CarRental.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class Rent {
    private int id;
    private int person_id;
    private int car_id;
    private int current_mileage;
    private String return_date;
}
