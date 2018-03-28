package com.CarRental.gui;

import com.CarRental.Service.PersonClient;
import com.CarRental.Service.PersonClientImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewClientFrame extends JFrame implements ActionListener {

    JButton addClientButton;
    JLabel nameLabel, surnameLabel, peselLabel, phoneLabel, licenseLabel;
    JTextField nameText, surnameText, peselText, phoneText, licenseText;
    String nameS,surnameS, phoneS, peselS;
    int licenseS;

    PersonClient personClient = new PersonClientImpl();

    NewClientFrame()
    {
        super("DODAWANIE KLIENTA");
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
        licenseLabel = new JLabel("prawo jazdy :");
        licenseLabel.setBounds(50,170,100,20);
        add(licenseLabel);

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
        licenseText = new JTextField("");
        licenseText.setBounds(150,170,100,20);
        add(licenseText);

        addClientButton = new JButton("dodaj");
        addClientButton.setBounds(50,200,200,20);
        addClientButton.addActionListener(this::actionPerformed);
        add(addClientButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == addClientButton)
        {
            nameS = nameText.getText();
            surnameS = surnameText.getText();
            peselS = peselText.getText();
            phoneS = phoneText.getText();
            licenseS = Integer.parseInt(licenseText.getText());

            boolean isCorrect1 = personClient.addPerson(nameS,surnameS,peselS,phoneS);
            boolean isCorrect2 = personClient.addClient(peselS,licenseS);

            if(isCorrect1 == true && isCorrect2 == true)
                JOptionPane.showMessageDialog(null,"Klient dodany prawidłowo","INFO",JOptionPane.INFORMATION_MESSAGE);

            nameText.setText("");
            surnameText.setText("");
            peselText.setText("");
            phoneText.setText("");
            licenseText.setText("");
        }
    }
}
