package com.CarRental.dao.workerDao;

import com.CarRental.model.Worker;
import java.util.List;
import java.util.Optional;

public interface WorkerDao {
    void add(Worker worker);
    void update(Worker worker);
    void delete(int id);
    List<Worker> getAll();
    Optional<Worker> getOne(int id);
}
