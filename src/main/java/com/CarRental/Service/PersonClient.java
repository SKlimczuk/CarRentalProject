package com.CarRental.Service;

import java.util.ArrayList;

public interface PersonClient {
    boolean addClient(String pesel, int driving_license);
    boolean addPerson(String name, String surname, String pesel, String phone_number);
    void deleteClient(String pesel);

    void getClient(String pesel);
    ArrayList<String> getAllClients();
}
