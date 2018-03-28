package com.CarRental.dao.carDao;

import com.CarRental.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarDao {
    void add(Car car);
    void update(Car car);
    void delete(int id);
    List<String> getAll();
    Optional<Car> getOne(int id);
    Optional<Car> getByBrandAndModel(String brand, String model);
}
