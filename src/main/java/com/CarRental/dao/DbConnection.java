package com.CarRental.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {
    private static DbConnection ourInstance = new DbConnection();

    public static DbConnection getInstance() {
        return ourInstance;
    }

    private final static String DRIVER = "org.sqlite.JDBC";
    private final static String DATABASE = "jdbc:sqlite:CarRentalDB.db";

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }


    private DbConnection() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DATABASE);
            createTables();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTables()
    {
        try {
            if (connection != null)
            {
                String sql_car =
                        "CREATE TABLE IF NOT EXISTS Car(" +
                                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                "car_brand VARCHAR (50) NOT NULL ," +
                                "car_model VARCHAR (50) NOT NULL ," +
                                "production_year INTEGER NOT NULL ," +
                                "car_mileage INTEGER NOT NULL " +
                                ")";
                String sql_person =
                        "CREATE TABLE IF NOT EXISTS Person (" +
                                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                "name VARCHAR (50) NOT NULL ," +
                                "surname VARCHAR (50) NOT NULL ," +
                                "pesel VARCHAR (50) NOT NULL UNIQUE ," +
                                "phone_number VARCHAR (50) NOT NULL" +
                                ")";
                String sql_worker =
                        "CREATE TABLE IF NOT EXISTS Worker (" +
                                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                "person_id INTEGER NOT NULL ," +
                                "salary INTEGER," +
                                "FOREIGN KEY (person_id) REFERENCES Person(id) ON DELETE CASCADE " +
                                ")";
                String sql_client =
                        "CREATE TABLE IF NOT EXISTS Client (" +
                                "id INTEGER PRIMARY KEY AUTOINCREMENT ," +
                                "person_id INTEGER NOT NULL ," +
                                "driving_license INTEGER UNIQUE," +
                                "FOREIGN KEY (person_id) REFERENCES Person(id) ON DELETE CASCADE " +
                                ")";
                String sql_rent =
                        "CREATE TABLE IF NOT EXISTS Rent (" +
                                "id INTEGER PRIMARY KEY AUTOINCREMENT ," +
                                "person_id INTEGER NOT NULL ," +
                                "car_id INTEGER NOT NULL ," +
                                "current_mileage INTEGER NOT NULL ," +
                                "return_date DATE NOT NULL ," +
                                "FOREIGN KEY (person_id) REFERENCES Person(id) ON UPDATE CASCADE ," +
                                "FOREIGN KEY (person_id) REFERENCES PersonIdx(id) ON UPDATE CASCADE ," +
                                "FOREIGN KEY (car_id) REFERENCES Car(id) ON UPDATE CASCADE " +
                                ")";
                //String sql_idxOnPerson =
                        //("CREATE INDEX surname_idx ON Person(pesel)");
                String sql_viewWorker =
                        "CREATE VIEW IF NOT EXISTS workerView AS SELECT " +
                                "Worker.id AS id," +
                                "Person.name AS name," +
                                "Person.surname AS surname," +
                                "Person.phone_number AS phone_number," +
                                "Person.pesel AS pesel," +
                                "Worker.salary AS salary " +
                                "FROM Worker " +
                                "INNER JOIN Person ON Worker.person_id = Person.id ";
                String sql_viewClient =
                        "CREATE VIEW IF NOT EXISTS clientView AS SELECT " +
                                "Client.id AS id," +
                                "Person.name AS name," +
                                "Person.surname AS surname," +
                                "Person.phone_number AS phone_number," +
                                "Person.pesel AS pesel," +
                                "Client.driving_license AS driving_license " +
                                "FROM Client " +
                                "INNER JOIN Person ON Client.person_id = Person.id ";
                String sql_viewRent =
                        "CREATE VIEW IF NOT EXISTS RentView AS SELECT " +
                                "p.pesel as pesel," +
                                "c.car_brand as brand," +
                                "c.car_model as model," +
                                "r.current_mileage as mileage " +
                                "FROM Rent r " +
                                "INNER JOIN Person p ON r.person_id = p.id " +
                                "LEFT OUTER JOIN Car c ON r.car_id = c.id" +
                                "";

                Statement statement = connection.createStatement();
                statement.execute(sql_car);
                //statement.execute(sql_idxOnPerson);
                statement.execute(sql_person);
                statement.execute(sql_client);
                statement.execute(sql_worker);
                statement.execute(sql_rent);
                statement.execute(sql_viewWorker);
                statement.execute(sql_viewClient);
                statement.execute(sql_viewRent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
