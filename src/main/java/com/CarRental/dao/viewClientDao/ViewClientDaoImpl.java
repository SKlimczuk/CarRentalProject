package com.CarRental.dao.viewClientDao;

import com.CarRental.dao.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewClientDaoImpl implements ViewClienDao {
    Connection connection = DbConnection.getInstance().getConnection();

    @Override
    public void getOne(int id) {
        try {
            PreparedStatement prep = connection.prepareStatement(
                    "SELECT id,name,surname,phone_number,pesel,driving_license FROM clientView WHERE id = ?");
            prep.setInt(1,id);
            ResultSet rs = prep.executeQuery();
            while(rs.next())
            {
                System.out.println(
                        rs.getInt(1)+ ", " +
                                rs.getString(2) + ", " +
                                rs.getString(3) + ", " +
                                rs.getString(4) + ", " +
                                rs.getString(5) + ", " +
                                rs.getString(6)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<String> getAll() {
        ArrayList<String> clientsTemp = new ArrayList<>();
        String temp="";
        try {
            PreparedStatement prep = connection.prepareStatement(
                    "SELECT id,name,surname,phone_number,pesel,driving_license FROM clientView");
            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                temp =  "ID: " + rs.getInt(1) + ", " +
                        rs.getString(2) + " " +
                        rs.getString(3) + ", " +
                        "NR. KONTAKTOWY: " + rs.getString(4) + ", " +
                        "PESEL: " + rs.getString(5) + ", " +
                        "PRAWO JAZDY: " + rs.getString(6);
                 clientsTemp.add(temp);
            }
            return clientsTemp;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
