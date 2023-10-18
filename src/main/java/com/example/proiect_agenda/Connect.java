package com.example.proiect_agenda;
import java.sql.*;
public class Connect {
    private Connection connection;


    public Connect() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiect_java", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ResultSet executeQuery(String query) {
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }


    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void deletePerson(Person person) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM persoane WHERE nr_telefon = ?");
            statement.setString(1, person.getNr_telefon());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertPerson(Person person) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO persoane (nume, prenume, adresa, nr_telefon) VALUES (?, ?, ?, ?)");
            statement.setString(1, person.getNume());
            statement.setString(2, person.getPrenume());
            statement.setString(3, person.getAdresa());
            statement.setString(4, person.getNr_telefon());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updatePerson(Person person) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE persoane SET prenume = ?, adresa = ?, nr_telefon = ? WHERE nume = ?");
            statement.setString(1, person.getPrenume());
            statement.setString(2, person.getAdresa());
            statement.setString(3, person.getNr_telefon());
            statement.setString(4, person.getNume());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}