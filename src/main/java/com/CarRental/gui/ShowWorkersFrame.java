package com.CarRental.gui;

import com.CarRental.Service.PersonWorker;
import com.CarRental.Service.PersonWorkerImpl;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ShowWorkersFrame extends JFrame {
    PersonWorker personWorker = new PersonWorkerImpl();
    List<String> workers = personWorker.getAllWorkers();
    JList list;
    JScrollPane scroll;

    ShowWorkersFrame()
    {
        super("LISTA PRACOWNIKÓW");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600,300);
        JPanel panel = new JPanel(new BorderLayout());

        list = new JList(workers.toArray());
        scroll = new JScrollPane();
        scroll.setViewportView(list);
        list.setLayoutOrientation(JList.VERTICAL);
        add(scroll);

        setVisible(true);
    }
}
