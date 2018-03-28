package com.CarRental.Service;

import com.CarRental.dao.personDao.PersonDao;
import com.CarRental.dao.personDao.PersonDaoImpl;
import com.CarRental.dao.viewWorkerDao.ViewWorkerDao;
import com.CarRental.dao.viewWorkerDao.ViewWorkerDaoImpl;
import com.CarRental.dao.workerDao.WorkerDao;
import com.CarRental.dao.workerDao.WorkerDaoImpl;
import com.CarRental.model.Person;
import com.CarRental.model.Worker;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

public class PersonWorkerImpl implements PersonWorker{
    PersonDao personDao = new PersonDaoImpl();
    WorkerDao workerDao = new WorkerDaoImpl();
    ViewWorkerDao viewWorkerDao = new ViewWorkerDaoImpl();

    @Override
    public boolean addWorker(String pesel, int salary) {
        Optional<Person> personOp = personDao.getByPesel(pesel);
        if(!personOp.isPresent())
        {
            JOptionPane.showMessageDialog(null,"Dodawanie nie powiodło się","BŁĄD",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        Person person = personOp.get();

        workerDao.add(Worker
                .builder()
                .person_id(person.getId())
                .salary(salary)
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
    public void deleteWorker(String pesel) {
        Optional<Person> personOp = personDao.getByPesel(pesel);

        if(!personOp.isPresent())
        {
            System.out.println("Taka osoba nie istnieje");
        }

        Person person = personOp.get();
        personDao.delete(person.getId());
        workerDao.delete(person.getId());
    }

    @Override
    public void findWorker(String pesel) {
       Optional<Person> personOp = personDao.getByPesel(pesel);

        if(!personOp.isPresent())
        {
            System.out.println("Taka osoba nie istnieje");
        }

        Person person = personOp.get();
        viewWorkerDao.getOne(person.getId());
    }

    @Override
    public List<String> getAllWorkers() {
        return viewWorkerDao.getAll();
    }
}
