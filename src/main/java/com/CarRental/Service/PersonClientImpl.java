package com.CarRental.Service;

import com.CarRental.dao.clientDao.ClientDao;
import com.CarRental.dao.clientDao.ClientDaoImpl;
import com.CarRental.dao.personDao.PersonDao;
import com.CarRental.dao.personDao.PersonDaoImpl;
import com.CarRental.dao.viewClientDao.ViewClienDao;
import com.CarRental.dao.viewClientDao.ViewClientDaoImpl;
import com.CarRental.model.Client;
import com.CarRental.model.Person;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Optional;

public class PersonClientImpl extends JFrame implements PersonClient{
    PersonDao personDao = new PersonDaoImpl();
    ClientDao clientDao = new ClientDaoImpl();
    ViewClienDao viewClienDao = new ViewClientDaoImpl();

    @Override
    public boolean addClient(String pesel, int driving_license) {
        Optional<Person> personOp = personDao.getByPesel(pesel);

        if(!personOp.isPresent())
        {
            JOptionPane.showMessageDialog(null,"Dodawanie nie powiodlo sie","BŁĄD",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        Person person = personOp.get();

        clientDao.add(Client
                .builder()
                .person_id(person.getId())
                .driving_license(driving_license)
                .build());
        return true;
    }

    @Override
    public boolean addPerson(String name, String surname, String pesel, String phone_number) {
        Optional<Person> personOp = personDao.getByPesel(pesel);
        if(personOp.isPresent())
        {
            JOptionPane.showMessageDialog(null,"Osoba o takim numerze PESEL już istnieje","BŁĄD",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        personDao.add(Person
                .builder()
                .name(name)
                .surname(surname)
                .pesel(pesel)
                .phone_number(phone_number)
                .build());

        return true;
    }

    @Override
    public void deleteClient(String pesel) {
        Optional<Person> personOp = personDao.getByPesel(pesel);

        if(!personOp.isPresent())
        {
            JOptionPane.showMessageDialog(null,"Klient o takim numerze PESEL już istnieje","BŁĄD",JOptionPane.ERROR_MESSAGE);
        }

        Person person = personOp.get();
        personDao.delete(person.getId());
        clientDao.delete(person.getId());
    }

    @Override
    public void getClient(String pesel) {
        Optional<Person> personOp = personDao.getByPesel(pesel);

        if(!personOp.isPresent())
        {
            JOptionPane.showMessageDialog(null,"Klient o takim numerze PESEL już istnieje","BŁĄD",JOptionPane.ERROR_MESSAGE);
        }

        Person person = personOp.get();
        viewClienDao.getOne(person.getId());
    }

    @Override
    public ArrayList<String> getAllClients() {
        return viewClienDao.getAll();
    }

}
