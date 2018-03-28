package com.CarRental.dao.clientDao;

import com.CarRental.dao.DbConnection;
import com.CarRental.model.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientDaoImpl implements ClientDao {
    Connection connection = DbConnection.getInstance().getConnection();

    @Override
    public void add(Client client) {
        try {
            PreparedStatement prep = connection.prepareStatement(
                    "INSERT INTO Client (person_id, driving_license) VALUES (?,?)");
            prep.setInt(1, client.getPerson_id());
            prep.setInt(2, client.getDriving_license());
            prep.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Client client) {
        try {
            PreparedStatement prep = connection.prepareStatement(
                    "UPDATE Client SET person_id=?, driving_license=? WHERE id=?");
            prep.setInt(1, client.getPerson_id());
            prep.setInt(2, client.getDriving_license());
            prep.setInt(3, client.getId());
            prep.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement prep = connection.prepareStatement(
                    "DELETE FROM Client WHERE person_id=?");
            prep.setInt(1,id);
            prep.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Client> getAll() {
        try {
            PreparedStatement prep = connection.prepareStatement(
                    "SELECT id, person_id,driving_license FROM Client");
            ResultSet rs = prep.executeQuery();
            List<Client> clientList = new ArrayList<>();
            while (rs.next())
            {
                clientList.add(Client
                        .builder()
                        .id(rs.getInt(1))
                        .person_id(rs.getInt(2))
                        .driving_license(rs.getInt(3))
                        .build());
            }
            return clientList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Client> getOne(int id) {
        try {
            PreparedStatement prep = connection.prepareStatement(
                    "SELECT id, person_id,driving_license FROM Client WHERE id=?");
            prep.setInt(1,id);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                return Optional.of(Client
                        .builder()
                        .id(rs.getInt(1))
                        .person_id(rs.getInt(2))
                        .driving_license(rs.getInt(3))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
