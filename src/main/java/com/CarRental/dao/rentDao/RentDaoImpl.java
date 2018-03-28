package com.CarRental.dao.rentDao;

import com.CarRental.dao.DbConnection;
import com.CarRental.model.Rent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RentDaoImpl implements RentDao {
    Connection connection = DbConnection.getInstance().getConnection();

    @Override
    public void add(Rent rent) {
        try {
            PreparedStatement prep = connection.prepareStatement(
                    "INSERT INTO Rent (person_id, car_id, current_mileage, return_date) VALUES (?,?,?,?)");
            prep.setInt(1,rent.getPerson_id());
            prep.setInt(2,rent.getCar_id());
            prep.setInt(3,rent.getCurrent_mileage());
            prep.setString(4,rent.getReturn_date());
            prep.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Rent rent) {
        try {
            PreparedStatement prep = connection.prepareStatement(
                    "UPDATE Rent SET person_id=?,car_id=?,current_mileage=?,return_date=? WHERE id=?");
            prep.setInt(1,rent.getPerson_id());
            prep.setInt(2,rent.getCar_id());
            prep.setInt(3,rent.getCurrent_mileage());
            prep.setString(4,rent.getReturn_date());
            prep.setInt(5,rent.getId());
            prep.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement prep = connection.prepareStatement("DELETE FROM Rent WHERE id=?");
            prep.setInt(1,id);
            prep.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Rent> getAll() {
        try{
            PreparedStatement prep = connection.prepareStatement(
                    "SELECT person_id,car_id,current_mileage,return_date FROM Rent");
            ResultSet rs = prep.executeQuery();
            List<Rent> rentList = new ArrayList<>();
            while(rs.next()){
                rentList.add(Rent
                        .builder()
                        .id(rs.getInt(1))
                        .person_id(rs.getInt(2))
                        .car_id(rs.getInt(3))
                        .current_mileage(rs.getInt(4))
                        .return_date(rs.getString(5))
                        .build()
                );
            }
            return rentList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Rent> getOne(Rent rent) {
        try{
            PreparedStatement prep = connection.prepareStatement(
                    "SELECT person_id,car_id,current_mileage,return_date FROM Rent WHERE id=?");
            prep.setInt(1,rent.getId());
            ResultSet rs = prep.executeQuery();
            while(rs.next()){
                return Optional.of(Rent
                        .builder()
                        .id(rs.getInt(1))
                        .person_id(rs.getInt(2))
                        .car_id(rs.getInt(3))
                        .current_mileage(rs.getInt(4))
                        .return_date(rs.getString(5))
                        .build()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();

    }

    @Override
    public String avgClient(int id) {
        String avgTemp = "";
        try {
            PreparedStatement prep = connection.prepareStatement(
                    "SELECT avg(current_mileage) FROM Rent " +
                            "GROUP BY person_id " +
                            "HAVING person_id = ?"
            );
            prep.setInt(1,id);
            ResultSet rs = prep.executeQuery();
            while(rs.next())
            {
                avgTemp = rs.getString(1);
            }
            return avgTemp;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
