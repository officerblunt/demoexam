package ru.officerblunt;

import ru.officerblunt.ui.AgentTableForm;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        new AgentTableForm();
    }
    public static Connection getConnection() throws SQLException {
       return DriverManager.getConnection("jdbc:mysql://localhost:3306/demoexam_2", "root", "110302");
    }
}
