package org.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.sqlite.JDBC;

public class InterviewDatabase {

    /**
     * Function to create a connection to a new SQLite database marked ms3Interview.db to be generated in the base project directory
     */
    public static void createNewDatabase() {
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:ms3Interview.db")) {
            if (conn != null) {
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
