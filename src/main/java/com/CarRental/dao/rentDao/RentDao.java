package com.CarRental.dao.rentDao;

import com.CarRental.model.Rent;

import java.util.List;
import java.util.Optional;

public interface RentDao {
    void add(Rent rent);
    void update(Rent rent);
    void delete(int id);
    List<Rent> getAll();
    Optional<Rent> getOne(Rent rent);
    String avgClient(int id);
}
