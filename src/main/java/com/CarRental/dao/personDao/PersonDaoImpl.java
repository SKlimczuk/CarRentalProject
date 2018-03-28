package com.CarRental.dao.personDao;

import com.CarRental.dao.DbConnection;
import com.CarRental.model.Car;
import com.CarRental.model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonDaoImpl implements PersonDao {
    Connection connection = DbConnection.getInstance().getConnection();

    @Override
    public void add(Person person) {
        try {
            PreparedStatement prep = connection.prepareStatement(
                    "INSERT INTO Person (name, surname, pesel, phone_number) VALUES (?,?,?,?)");
            prep.setString(1,person.getName());
            prep.setString(2,person.getSurname());
            prep.setString(3,person.getPesel());
            prep.setString(4,person.getPhone_number());
           // prep.setInt(5,person.getWorker_id());
           // prep.setInt(6,person.getClient_id());
            prep.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Person person) {
        try {
            PreparedStatement prep = connection.prepareStatement(
                    "UPDATE Person SET name=?,surname=?,pesel=?,phone_number=? WHERE id=?");
            prep.setString(1,person.getName());
            prep.setString(2,person.getSurname());
            prep.setString(3,person.getPesel());
            prep.setString(4,person.getPhone_number());
            //prep.setInt(5,person.getWorker_id());
            //prep.setInt(6,person.getClient_id());
            prep.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement prep = connection.prepareStatement("DELETE FROM Person WHERE id=?");
            prep.setInt(1,id);
            prep.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Person> getAll() {
        try{
        PreparedStatement prep = connection.prepareStatement(
                "SELECT name,surname,pesel,phone_number FROM Person");
        ResultSet rs = prep.executeQuery();
        List<Person> personList = new ArrayList<>();
        while(rs.next()){
            personList.add(Person
                    .builder()
                    .name(rs.getString(1))
                    .surname(rs.getString(2))
                    .pesel(rs.getString(3))
                    .phone_number(rs.getString(4))
                    //.worker_id(rs.getInt(5))
                    //.client_id(rs.getInt(6))
                    .build()
            );
        }
        return personList;
    } catch (SQLException e) {
        e.printStackTrace();
    }
        return null;
    }

    @Override
    public Optional<Person> getOne(int id) {
        try{
            PreparedStatement prep = connection.prepareStatement(
                    "SELECT name,surname,pesel,phone_number FROM Person WHERE  id=?");
            prep.setInt(1,id);
            ResultSet rs = prep.executeQuery();
            while(rs.next()){
                return Optional.of(Person
                        .builder()
                        .name(rs.getString(1))
                        .surname(rs.getString(2))
                        .pesel(rs.getString(3))
                        .phone_number(rs.getString(4))
                        //.worker_id(rs.getInt(5))
                        //.client_id(rs.getInt(6))
                        .build()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Person> getByPesel(String pesel) {
        try {
            PreparedStatement prep = connection.prepareStatement("SELECT id,name,surname,pesel,phone_number FROM Person WHERE pesel=?");
            prep.setString(1,pesel);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                return Optional.of(Person
                        .builder()
                        .id(rs.getInt(1))
                        .name(rs.getString(2))
                        .surname(rs.getString(3))
                        .pesel(rs.getString(4))
                        .phone_number(rs.getString(5))
                        //.worker_id(rs.getInt(5))
                        //.client_id(rs.getInt(6))
                        .build()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
