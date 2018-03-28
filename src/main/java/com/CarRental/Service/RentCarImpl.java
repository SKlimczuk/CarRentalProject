package com.CarRental.Service;

import com.CarRental.dao.carDao.CarDao;
import com.CarRental.dao.carDao.CarDaoImpl;
import com.CarRental.dao.personDao.PersonDao;
import com.CarRental.dao.personDao.PersonDaoImpl;
import com.CarRental.dao.rentDao.RentDao;
import com.CarRental.dao.rentDao.RentDaoImpl;
import com.CarRental.dao.viewRentDao.ViewRentDao;
import com.CarRental.dao.viewRentDao.viewRentDaoImpl;
import com.CarRental.model.Car;
import com.CarRental.model.Person;
import com.CarRental.model.Rent;

import javax.swing.*;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public class RentCarImpl extends JFrame implements RentCar {
    CarDao carDao = new CarDaoImpl();
    PersonDao personDao = new PersonDaoImpl();
    RentDao rentDao = new RentDaoImpl();
    ViewRentDao viewRentDao = new viewRentDaoImpl();

    @Override
    public void newRent(String brand, String model, String pesel, int mileage, String term) {
        Optional<Car> carOp = carDao.getByBrandAndModel(brand,model);
        Optional<Person> personOp = personDao.getByPesel(pesel);

        if (!carOp.isPresent()) {
            JOptionPane.showMessageDialog(null,"Nie ma takiego samochodu w bazie !","BŁĄD",JOptionPane.ERROR_MESSAGE);
        }
        if (!personOp.isPresent())
        {
            JOptionPane.showMessageDialog(null,"Nie ma takiej osoby w bazie !","BŁĄD",JOptionPane.ERROR_MESSAGE);
        }

        Person person = personOp.get();
        Car car = carOp.get();

        rentDao.add(Rent
                .builder()
                .person_id(person.getId())
                .car_id(car.getId())
                .current_mileage(mileage)
                .return_date(term)
                .build());
    }

    @Override
    public void addCar(String car_brand, String car_model, int production_year, int car_mileage) {
        carDao.add(Car
                .builder()
                .car_brand(car_brand)
                .car_model(car_model)
                .production_year(production_year)
                .car_mileage(car_mileage)
                .build());
    }

    @Override
    public void getCar(String brand, String model) {
        Optional<Car> carOp = carDao.getByBrandAndModel(brand,model);
        System.out.println(carOp.toString());
        if(!carOp.isPresent())
        {
            JOptionPane.showMessageDialog(null,"Podany samochód nie istnieje","BŁĄD",JOptionPane.ERROR_MESSAGE);
        }

        Car car = carOp.get();
        carDao.getOne(car.getId());
    }

    @Override
    public List<String>  getAllCars()
    {
        return carDao.getAll();
    }

    @Override
    public List<String> getAllRents() {
        return viewRentDao.getAll();
    }

    @Override
    public String avgClientMileage(String pesel) {
        Optional<Person> personOp = personDao.getByPesel(pesel);
        if(!personOp.isPresent())
        {
            JOptionPane.showMessageDialog(null,"nieprawidlowy pesel","BŁĄD",JOptionPane.ERROR_MESSAGE);
        }
        Person person = personOp.get();
        return rentDao.avgClient(person.getId());
    }

    @Override
    public void updateMileage(String brand, String model, int mileage) {
        Optional<Car> carOp = carDao.getByBrandAndModel(brand,model);
        Car car = carOp.get();
        int tmp = car.getCar_mileage()+mileage;
        carDao.update(Car.builder()
                .id(car.getId())
                .car_brand(car.getCar_brand())
                .car_model(car.getCar_model())
                .car_mileage(tmp)
                .production_year(car.getProduction_year())
                .build());
    }
}
