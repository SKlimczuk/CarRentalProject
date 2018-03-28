package com.CarRental.dao.viewWorkerDao;

import com.CarRental.dao.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewWorkerDaoImpl implements ViewWorkerDao {
    Connection connection = DbConnection.getInstance().getConnection();

    @Override
    public void getOne(int id) {
        try {
            PreparedStatement prep = connection.prepareStatement(
                    "SELECT id,name,surname,phone_number,pesel,salary FROM workerView WHERE id = ?");
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
    public List<String> getAll() {
        List<String> wokersTemp = new ArrayList<>();
        String temp;
        try {
            PreparedStatement prep = connection.prepareStatement(
                    "SELECT id,name,surname,phone_number,pesel,salary FROM workerView");
            ResultSet rs = prep.executeQuery();
            while(rs.next())
            {
                temp =  "ID: " + rs.getInt(1) + ", " +
                        rs.getString(2) + " " +
                        rs.getString(3) + ", " +
                        "NR. KONTAKTOWY: " + rs.getString(4) + ", " +
                        "PESEL: " + rs.getString(5) + ", " +
                        "PENSJA: " + rs.getString(6);
                wokersTemp.add(temp);
            }
            return wokersTemp;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
