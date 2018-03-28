package com.CarRental.dao.personDao;

import com.CarRental.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonDao {
    void add(Person person);
    void update(Person person);
    void delete(int id);
    List<Person> getAll();
    Optional<Person> getOne(int id);
    Optional<Person> getByPesel(String pesel);
}
