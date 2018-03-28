package com.CarRental.gui;

import com.CarRental.Service.RentCar;
import com.CarRental.Service.RentCarImpl;
import com.CarRental.dao.carDao.CarDao;
import com.CarRental.dao.carDao.CarDaoImpl;
import com.CarRental.model.Car;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;
import java.util.Random;

public class NewRentFrame extends JFrame implements ActionListener{
    JButton confirmButton;
    JLabel brandLabel, modelLabel, peselLabel, termLabel, peselText;
    JTextField brandText, modelText,  termText;
    Random rnd = new Random();

    String modelS,brandS,termS,peselS;
    int mileageRandom = 0;

    RentCar rentCar = new RentCarImpl();

    NewRentFrame(String clientPesel)
    {
        super("WYPOŻYCZENIE");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300,300);
        setLayout(null);

        brandLabel = new JLabel("marka: ");
        modelLabel = new JLabel("model: ");
        peselLabel = new JLabel("pesel: ");
        termLabel = new JLabel("ilość dni: ");
        brandText = new JTextField("");
        modelText = new JTextField("");
        peselText = new JLabel(clientPesel);
        termText = new JTextField("");
        confirmButton = new JButton("wypożycz");

        brandLabel.setBounds(50,50,100,20);
        modelLabel.setBounds(50,80,100,20);
        peselLabel.setBounds(50,110,100,20);
        termLabel.setBounds(50,140,100,20);
        brandText.setBounds(150,50,100,20);
        modelText.setBounds(150,80,100,20);
        peselText.setBounds(150,110,100,20);
        termText.setBounds(150,140,100,20);
        confirmButton.setBounds(50,170,200,20);
        confirmButton.addActionListener(this::actionPerformed);

        add(brandLabel);
        add(brandText);
        add(peselLabel);
        add(peselText);
        add(modelLabel);
        add(modelText);
        add(termLabel);
        add(termText);
        add(confirmButton);

        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source == confirmButton)
        {
            modelS = modelText.getText();
            brandS = brandText.getText();
            termS = termText.getText();
            peselS = peselText.getText();

            int tmprnd = Integer.parseInt(termS);
            mileageRandom = tmprnd * rnd.nextInt(300);

            CarDao carDao = new CarDaoImpl();
            Optional<Car> carOp = carDao.getByBrandAndModel(brandS,modelS);
            if(carOp.isPresent()) {
                rentCar.updateMileage(brandS, modelS, mileageRandom);
                rentCar.newRent(brandS, modelS, peselS, mileageRandom, termS);
                JOptionPane.showMessageDialog(null,
                        "wypożyczenie zatwierdzone",
                        "INFO",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            else
                JOptionPane.showMessageDialog(
                        null,
                        "Nieprawidłowa marka lub model auta",
                        "BŁĄD",
                        JOptionPane.ERROR_MESSAGE);


            modelText.setText("");
            brandText.setText("");
            termText.setText("");
        }
    }
}
