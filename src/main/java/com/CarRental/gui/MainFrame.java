package com.CarRental.gui;

import com.CarRental.dao.personDao.PersonDao;
import com.CarRental.dao.personDao.PersonDaoImpl;
import com.CarRental.model.Person;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public class MainFrame extends JFrame implements ActionListener {
    JButton clientButton, workerButton;
    JTextField peselField;
    JLabel peselPanel;

    public MainFrame()
    {
        super("MENU GLÓWNE");
        setSize(300,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        peselPanel = new JLabel("PSESEL : ");
        peselPanel.setBounds(50,70,100,20);
        add(peselPanel);
        peselField = new JTextField("");
        peselField.setBounds(150,70,100,20);
        peselField.addActionListener(this::actionPerformed);
        add(peselField);
        clientButton = new JButton("KLIENT");
        clientButton.setBounds(50,100,200,20);
        clientButton.addActionListener(this::actionPerformed);
        add(clientButton);
        workerButton = new JButton("PRACOWNIK");
        add(workerButton);
        workerButton.setBounds(50,130,200,20);
        workerButton.addActionListener(this::actionPerformed);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == clientButton)
        {
            String tmp = peselField.getText();
            PersonDao personDao = new PersonDaoImpl();
            Optional<Person> personOp = personDao.getByPesel(tmp);
            if(personOp.isPresent()) {
                openClientWindow(tmp);
                peselField.setText("");
            }
            else
                JOptionPane.showMessageDialog(
                        null,
                        "Nieprawidłowy pesel",
                        "BŁĄD",
                        JOptionPane.ERROR_MESSAGE);
        }
        if(source == workerButton)
        {
            openWorkerWindow();
        }
    }

    void openClientWindow(String tmp)
    {
        ClientFrame clientFrame =  new ClientFrame(tmp);
    }
    void openWorkerWindow()
    {
        WorkerFrame workerFrame = new WorkerFrame();
    }
}
