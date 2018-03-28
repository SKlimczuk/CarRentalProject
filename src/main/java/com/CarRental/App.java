package com.CarRental;

import com.CarRental.gui.*;
import com.CarRental.dao.DbConnection;
import java.util.*;
import java.awt.*;

public class App
{
    public static void main( String[] args )
    {
        Scanner sc = new Scanner(System.in);
        Random rnd = new Random();

        //tworzenie obiektu singleton DbConnection w celu polaczenia sie z baza danych
        DbConnection db_connection = DbConnection.getInstance();
        db_connection.getConnection();

        //------------------------------------OKIENKA
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame();
            }
        });
    }
}

