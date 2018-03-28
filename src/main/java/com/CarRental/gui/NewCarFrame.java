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

public class NewCarFrame extends JFrame implements ActionListener {
    JButton addCarButton;
    JLabel brandLabel, modelLabel, yearLabel, mileageLabel;
    JTextField brandText, modelText, yearText, mileageText;
    String brandS, modelS;
    int years, mileageS;

    RentCar rentCar = new RentCarImpl();

    NewCarFrame()
    {
        super("DODAWANIE AUTA");
        setSize(300,300);
        setLayout(null);

        brandLabel = new JLabel("marka :");
        brandLabel.setBounds(50,50,100,20);
        add(brandLabel);
        modelLabel = new JLabel("model :");
        modelLabel.setBounds(50,80,100,20);
        add(modelLabel);
        yearLabel = new JLabel("rok produkcji :");
        yearLabel.setBounds(50,110,100,20);
        add(yearLabel);
        mileageLabel = new JLabel("przebieg :");
        mileageLabel.setBounds(50,140,100,20);
        add(mileageLabel);

        brandText = new JTextField("");
        brandText.setBounds(150,50,100,20);
        add(brandText);
        modelText = new JTextField("");
        modelText.setBounds(150,80,100,20);
        add(modelText);
        yearText = new JTextField("");
        yearText.setBounds(150,110,100,20);
        add(yearText);
        mileageText = new JTextField("");
        mileageText.setBounds(150,140,100,20);
        add(mileageText);

        addCarButton = new JButton("dodaj");
        addCarButton.setBounds(50,200,200,20);
        addCarButton.addActionListener(this::actionPerformed);
        add(addCarButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == addCarButton)
        {
            brandS = brandText.getText();
            modelS = modelText.getText();
            mileageS = Integer.parseInt(mileageText.getText());
            years = Integer.parseInt(yearText.getText());

            rentCar.addCar(brandS,modelS,years,mileageS);

            brandText.setText("");
            mileageText.setText("");
            modelText.setText("");
            yearText.setText("");
        }
    }
}
