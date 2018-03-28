package com.CarRental.gui;

import com.CarRental.Service.PersonWorker;
import com.CarRental.Service.PersonWorkerImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewWorkerFrame extends JFrame implements ActionListener {

    JButton addWorkerButton;
    JLabel nameLabel, surnameLabel, peselLabel, phoneLabel, salaryLabel;
    JTextField nameText, surnameText, peselText, phoneText, salaryText;
    String nameS,surnameS, phoneS, peselS;
    int salaryS;

    PersonWorker personWorker = new PersonWorkerImpl();

    NewWorkerFrame()
    {
        super("DODAWANIE PRACOWNIKA");
        setSize(300,300);
        setLayout(null);

        nameLabel = new JLabel("imię :");
        nameLabel.setBounds(50,50,100,20);
        add(nameLabel);
        surnameLabel = new JLabel("nazwisko :");
        surnameLabel.setBounds(50,80,100,20);
        add(surnameLabel);
        peselLabel = new JLabel("pesel :");
        peselLabel.setBounds(50,110,100,20);
        add(peselLabel);
        phoneLabel = new JLabel("nr telefonu :");
        phoneLabel.setBounds(50,140,100,20);
        add(phoneLabel);
        salaryLabel = new JLabel("pensja :");
        salaryLabel.setBounds(50,170,100,20);
        add(salaryLabel);

        nameText = new JTextField("");
        nameText.setBounds(150,50,100,20);
        add(nameText);
        surnameText = new JTextField("");
        surnameText.setBounds(150,80,100,20);
        add(surnameText);
        peselText = new JTextField("");
        peselText.setBounds(150,110,100,20);
        add(peselText);
        phoneText = new JTextField("");
        phoneText.setBounds(150,140,100,20);
        add(phoneText);
        salaryText = new JTextField("");
        salaryText.setBounds(150,170,100,20);
        add(salaryText);

        addWorkerButton = new JButton("dodaj");
        addWorkerButton.setBounds(50,200,200,20);
        addWorkerButton.addActionListener(this::actionPerformed);
        add(addWorkerButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == addWorkerButton)
        {
            nameS = nameText.getText();
            surnameS = surnameText.getText();
            peselS = peselText.getText();
            phoneS = phoneText.getText();
            salaryS = Integer.parseInt(salaryText.getText());

            boolean isCorrectWorker1 = personWorker.addPerson(nameS,surnameS,peselS,phoneS);
            boolean isCorrectWorker2 = personWorker.addWorker(peselS,salaryS);

            if(isCorrectWorker1 == true && isCorrectWorker2 == true)
                JOptionPane.showMessageDialog(null,"Pracownik dodany prawidłowo","INFO", JOptionPane.INFORMATION_MESSAGE);

            nameText.setText("");
            surnameText.setText("");
            peselText.setText("");
            phoneText.setText("");
            salaryText.setText("");
        }
    }
}
