package com.CarRental.dao.clientDao;

import com.CarRental.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientDao {
    void add(Client client);
    void update(Client client);
    void delete(int id);
    List<Client> getAll();
    Optional<Client> getOne(int id);
}
