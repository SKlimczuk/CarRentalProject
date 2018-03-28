package com.CarRental.dao.viewRentDao;

import com.CarRental.dao.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class viewRentDaoImpl implements ViewRentDao {
    Connection connection = DbConnection.getInstance().getConnection();

    @Override
    public List<String> getAll() {
        String temp;
        List<String> tempList = new ArrayList<>();
        try {
            PreparedStatement prep = connection.prepareStatement(
                    "SELECT pesel, brand, model, mileage FROM RentView");
            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                temp =  "PESEL KLIENTA: " + rs.getString(1) + ", " +
                        "AUTO: " + rs.getString(2) + " " +
                        "" + rs.getString(3) + ", " +
                        "KLIENT PRZEJECHAŁ: " + rs.getString(4);
                tempList.add(temp);
            }
            return tempList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
