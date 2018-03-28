package com.CarRental.Service;

import com.CarRental.model.Car;

import java.util.List;

public interface RentCar {
    void newRent(String brand, String model, String pesel, int mileage, String term);
    void addCar(String car_brand, String car_model, int production_year, int car_mileage);
    void getCar(String brand, String model);
    List<String> getAllCars();
    List<String> getAllRents();
    String avgClientMileage(String pesel);
    void updateMileage(String brand, String model, int mileage);
}
