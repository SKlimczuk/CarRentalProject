package com.CarRental.gui;

import com.CarRental.Service.RentCar;
import com.CarRental.Service.RentCarImpl;
import com.CarRental.dao.carDao.CarDao;
import com.CarRental.dao.carDao.CarDaoImpl;
import com.CarRental.model.Car;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ShowCarsFrame extends JFrame{

    RentCar rentCar = new RentCarImpl();
    List<String> carList = rentCar.getAllCars();
    JList list;
    JScrollPane scroll;

    ShowCarsFrame()
    {
        super("LISTA SAMOCHODÓW");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600,300);
        JPanel panel = new JPanel(new BorderLayout());

        list = new JList(carList.toArray());
        scroll = new JScrollPane();
        scroll.setViewportView(list);
        list.setLayoutOrientation(JList.VERTICAL);
        add(scroll);

        setVisible(true);
    }
}



