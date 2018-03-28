package com.CarRental.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class Person {
    private int id;
    private String name;
    private String surname;
    private String pesel;
    private String phone_number;
    //private int worker_id;
    //private int client_id;
}
