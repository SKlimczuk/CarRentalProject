package com.CarRental.gui;

import com.CarRental.Service.RentCar;
import com.CarRental.Service.RentCarImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WorkerFrame extends JFrame implements ActionListener {

    JButton addClientButton, showClientsButton, addWorkerButton,
            showWorkersButton, newCarButton, showCarsButton,
            showRentsButton, showMileageButton;
    JTextField peselText;
    String peselS;

    RentCar rentCar = new RentCarImpl();

    WorkerFrame()
    {
        super("MENU PRACOWNIK");
        setSize(300,400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        addClientButton = new JButton("dodaj klienta");
        addClientButton.addActionListener(this::actionPerformed);
        addClientButton.setBounds(50,100,200,20);
        add(addClientButton);
        showClientsButton = new JButton("pokaż klientów");
        showClientsButton.addActionListener(this::actionPerformed);
        showClientsButton.setBounds(50,130,200,20);
        add(showClientsButton);
        addWorkerButton = new JButton("dodaj pracownika");
        addWorkerButton.addActionListener(this::actionPerformed);
        addWorkerButton.setBounds(50,160,200,20);
        add(addWorkerButton);
        showWorkersButton = new JButton("pokaż pracowników");
        showWorkersButton.addActionListener(this::actionPerformed);
        showWorkersButton.setBounds(50,190,200,20);
        add(showWorkersButton);
        newCarButton = new JButton("dodaj auto");
        newCarButton.addActionListener(this::actionPerformed);
        newCarButton.setBounds(50,220,200,20);
        add(newCarButton);
        showCarsButton = new JButton("pokaż auta");
        showCarsButton.addActionListener(this::actionPerformed);
        showCarsButton.setBounds(50,250,200,20);
        add(showCarsButton);
        showRentsButton = new JButton("pokaż wypożyczenia");
        showRentsButton.addActionListener(this::actionPerformed);
        showRentsButton.setBounds(50,280,200,20);
        add(showRentsButton);
        showMileageButton = new JButton("śr. przebieg klienta");
        showMileageButton.addActionListener(this::actionPerformed);
        showMileageButton.setBounds(50,310,100,20);
        add(showMileageButton);
        peselText = new JTextField("");
        peselText.setBounds(150,310,100,20);
        add(peselText);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == addClientButton)
        {
            newClient();
        }
        if(source == showClientsButton)
        {
            showClients();
        }
        if(source == addWorkerButton)
        {
            newWorker();
        }
        if(source == showWorkersButton)
        {
            showWorkers();
        }
        if(source == newCarButton)
        {
            newCar();
        }
        if(source == showCarsButton)
        {
            showCars();
        }
        if(source == showRentsButton)
        {
            showRents();
        }
        if(source == showMileageButton)
        {
            peselS = peselText.getText();

            JOptionPane.showMessageDialog(null,
                    rentCar.avgClientMileage(peselS),
                    "ŚREDNI PRZEBIEG",JOptionPane.INFORMATION_MESSAGE);

            peselText.setText("");
        }
    }

    void showCars()
    {
        ShowCarsFrame showCarsFrame = new ShowCarsFrame();
    }
    void showClients(){ShowClientsFrame showClientsFrame = new ShowClientsFrame();}
    void showWorkers(){ShowWorkersFrame showWorkersFrame = new ShowWorkersFrame();}
    void showRents(){ShowRentsFrame showRentsFrame = new ShowRentsFrame();}
    void newClient(){ NewClientFrame newClientFrame = new NewClientFrame();}
    void newWorker() {NewWorkerFrame newWorkerFrame = new NewWorkerFrame();}
    void newCar() {NewCarFrame newCarFrame = new NewCarFrame();}

}
