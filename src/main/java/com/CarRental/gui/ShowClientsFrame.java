package com.CarRental.gui;

import com.CarRental.Service.PersonClient;
import com.CarRental.Service.PersonClientImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ShowClientsFrame extends JFrame {
    PersonClient personClient = new PersonClientImpl();
    ArrayList<String> clients = personClient.getAllClients();
    JList list;
    JScrollPane scroll;

    ShowClientsFrame()
    {
        super("LISTA KLIENTÓW");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600,300);
        JPanel panel = new JPanel(new BorderLayout());

        list = new JList(clients.toArray());
        scroll = new JScrollPane();
        scroll.setViewportView(list);
        list.setLayoutOrientation(JList.VERTICAL);
        add(scroll);

        setVisible(true);
    }
}
