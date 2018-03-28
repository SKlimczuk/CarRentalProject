package com.CarRental.dao.carDao;

import com.CarRental.dao.DbConnection;
import com.CarRental.model.Car;

import javax.swing.text.html.Option;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class CarDaoImpl implements CarDao{
    Connection connection = DbConnection.getInstance().getConnection();

    @Override
    public void add(Car car) {
        try {
            PreparedStatement prep = connection.prepareStatement(
                    "INSERT INTO Car (car_brand, car_model, production_year, car_mileage) VALUES (?,?,?,?)");
            prep.setString(1,car.getCar_brand());
            prep.setString(2,car.getCar_model());
            prep.setInt(3,car.getProduction_year());
            prep.setInt(4,car.getCar_mileage());
            prep.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Car car) {
        try {
            PreparedStatement prep = connection.prepareStatement(
                    "UPDATE Car SET car_brand=?,car_model=?,production_year=?,car_mileage=? WHERE id=?");
            prep.setString(1,car.getCar_brand());
            prep.setString(2,car.getCar_model());
            prep.setInt(3,car.getProduction_year());
            prep.setInt(4,car.getCar_mileage());
            prep.setInt(5,car.getId());
            prep.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement prep = connection.prepareStatement("DELETE FROM Car WHERE id=?");
            prep.setInt(1,id);
            prep.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getAll() {
        String tmp = "";
        List<String> tmpList = new LinkedList<>();
        try {
            PreparedStatement prep = connection.prepareStatement(
                    "SELECT id, car_brand,car_model,production_year,car_mileage FROM Car");
            ResultSet rs = prep.executeQuery();
            while(rs.next()){
                tmp =   "ID: " + rs.getInt(1) + ", " +
                        "AUTO: " + rs.getString(2) + " " +
                        rs.getString(3) + ", " +
                        "ROCZNIK: " + rs.getInt(4) + ", " +
                        "PRZEBIEG: " + rs.getInt(5);
                tmpList.add(tmp);
            }
            return tmpList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Car> getOne(int id) {
        try {
            PreparedStatement prep = connection.prepareStatement(
                    "SELECT car_brand,car_model,production_year,car_mileage FROM Car WHERE id = ?");
            prep.setInt(1,id);
            ResultSet rs = prep.executeQuery();
            while(rs.next()){
               return Optional.of(Car
                        .builder()
                        .id(id)
                        .car_brand(rs.getString(1))
                        .car_model(rs.getString(2))
                        .production_year(rs.getInt(3))
                        .car_mileage(rs.getInt(4))
                        .build()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Car> getByBrandAndModel(String brand, String model) {
        try {
            PreparedStatement prep = connection.prepareStatement(
                    "SELECT id, car_brand,car_model,production_year,car_mileage FROM Car " +
                            "WHERE car_brand = ? AND car_model = ?");
            prep.setString(1,brand);
            prep.setString(2,model);
            ResultSet rs = prep.executeQuery();
            while(rs.next()){
                return Optional.of(Car
                        .builder()
                        .id(rs.getInt(1))
                        .car_brand(rs.getString(2))
                        .car_model(rs.getString(3))
                        .production_year(rs.getInt(4))
                        .car_mileage(rs.getInt(5))
                        .build()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
