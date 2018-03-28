package com.CarRental.gui;

import com.CarRental.Service.RentCar;
import com.CarRental.Service.RentCarImpl;
import com.CarRental.dao.personDao.PersonDao;
import com.CarRental.dao.personDao.PersonDaoImpl;
import com.CarRental.model.Car;
import com.CarRental.model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public class ClientFrame extends JFrame implements ActionListener {

    RentCar rentCar = new RentCarImpl();
    JLabel info;

    JButton rentButton, showAllCarsButton;
    ClientFrame(String clientPesel)
    {
        super("MENU KLIENT");
        setSize(300,300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        info = new JLabel("Użytkownik: "+ clientIdentity(clientPesel));
        info.setBounds(50,50,200,20);
        add(info);
        showAllCarsButton = new JButton("dostępne auta");
        showAllCarsButton.addActionListener(this::actionPerformed);
        showAllCarsButton.setBounds(50,100,200,20);
        add(showAllCarsButton);
        rentButton = new JButton("wypożycz");
        rentButton.addActionListener(this::actionPerformed);
        rentButton.setBounds(50,130,200,20);
        add(rentButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == rentButton)
        {
            newRent();
        }
        if(source == showAllCarsButton)
        {
            showCars();
        }
    }

    String userpesel;

    public String clientIdentity( String pesel)
    {
        PersonDao personDao = new PersonDaoImpl();
        Optional<Person> personOp = personDao.getByPesel(pesel);
        Person person = personOp.get();
        userpesel = pesel;
        return person.getName()+" "+person.getSurname();
    }

    void showCars()
    {
        ShowCarsFrame showCarsFrame = new ShowCarsFrame();
    }
    void newRent()
    {
        NewRentFrame newRentFrame = new NewRentFrame(userpesel);
    }
}

