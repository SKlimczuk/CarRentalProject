package com.CarRental.dao.workerDao;

import com.CarRental.dao.DbConnection;
import com.CarRental.model.Worker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WorkerDaoImpl implements WorkerDao {
    Connection connection = DbConnection.getInstance().getConnection();

    @Override
    public void add(Worker worker) {
        try {
            PreparedStatement prep = connection.prepareStatement(
                    "INSERT INTO Worker (person_id, salary) VALUES (?,?)");
            prep.setInt(1,worker.getPerson_id());
            prep.setInt(2,worker.getSalary());
            prep.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Worker worker) {
            try {
                PreparedStatement prep = connection.prepareStatement(
                        "UPDATE Worker SET person_id = ?, salary=? WHERE id=?");
                prep.setInt(1,worker.getPerson_id());
                prep.setInt(2,worker.getSalary());
                prep.setInt(3,worker.getId());
                prep.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement prep = connection.prepareStatement(
                    "DELETE FROM Worker WHERE id=?");
            prep.setInt(1,id);
            prep.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Worker> getAll() {
        try {
            PreparedStatement prep = connection.prepareStatement(
                    "SELECT id, person_id,salary FROM Worker");
            ResultSet rs = prep.executeQuery();
            List<Worker> workerList = new ArrayList<>();
            while (rs.next())
            {
                workerList.add(Worker
                        .builder()
                        .id(rs.getInt(1))
                        .person_id(rs.getInt(2))
                        .salary(rs.getInt(3))
                        .build());
            }
            return workerList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Worker> getOne(int id) {
        try {
            PreparedStatement prep = connection.prepareStatement(
                    "SELECT id, person_id,salary FROM Worker WHERE id=?");
            prep.setInt(1,id);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                return Optional.of(Worker
                        .builder()
                        .id(rs.getInt(1))
                        .person_id(rs.getInt(2))
                        .salary(rs.getInt(3))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
