package com.CarRental.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class Client {
    private int id;
    private int person_id;
    private int driving_license;
}