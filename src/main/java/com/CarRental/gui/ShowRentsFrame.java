package com.CarRental.gui;

import com.CarRental.Service.RentCar;
import com.CarRental.Service.RentCarImpl;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ShowRentsFrame extends JFrame {
    RentCar rentCar = new RentCarImpl();
    List<String> rentsList = rentCar.getAllRents();
    JList list;
    JScrollPane scroll;

    ShowRentsFrame()
    {
        super("LISTA WYPOŻYCZEŃ");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600,300);
        JPanel panel = new JPanel(new BorderLayout());

        list = new JList(rentsList.toArray());
        scroll = new JScrollPane();
        scroll.setViewportView(list);
        list.setLayoutOrientation(JList.VERTICAL);
        add(scroll);

        setVisible(true);
    }
}
