package com.CarRental.Service;

import com.CarRental.model.Worker;

import java.util.List;

public interface PersonWorker {
    boolean addWorker(String pesel, int salary);
    boolean addPerson(String name, String surname, String pesel, String phone_number);
    void deleteWorker(String pesel);
    void findWorker(String pesel);
    List<String> getAllWorkers();
}
