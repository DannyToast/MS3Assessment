package org.example.database;

import java.sql.*;
import java.util.List;

public class InterviewTable {

    /**
     * Function to create a new table marked records in the ms3Interview.db SQLite database.
     */
    public static void createNewTable() {
        String sql = "CREATE TABLE records(\n" +
                " A text NOT NULL,\n " +
                " B text NOT NULL,\n " +
                " C text NOT NULL,\n " +
                " D text NOT NULL,\n " +
                " E text NOT NULL,\n " +
                " F text NOT NULL,\n " +
                " G text NOT NULL,\n " +
                " H text NOT NULL,\n " +
                " I text NOT NULL,\n " +
                " J text NOT NULL \n " +
                ");";

        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Function to form connection to ms3Interview.db SQLite database in the InterviewTable class.
     * @return Connection object/session with database
     */
    private static Connection connect() {
        String url = "jdbc:sqlite:ms3Interview.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    /**
     * Function to batch all successful records and commit them to the ms3Interview SQLite database using a Prepared
     * Statement. Also includes console benchmarks for time taken to execute the batch and commit the changes.
     * @param records ArrayList of successful records
     */
    public static void insertRecords(List < Records > records) {
        String sql = "INSERT INTO records (A,B,C,D,E,F,G,H,I,J) VALUES (?,?,?,?,?,?,?,?,?,?)";        

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (Records record: records) {
                System.out.println(record);
                conn.setAutoCommit(false);
                pstmt.setString(1, record.getFirstName());
                pstmt.setString(2, record.getLastName());
                pstmt.setString(3, record.getEmail());
                pstmt.setString(4, record.getGender());
                pstmt.setString(5, record.getImageData());
                pstmt.setString(6, record.getCard());
                pstmt.setString(7, record.getCharge());
                pstmt.setString(8, record.getFirstBoolValue());
                pstmt.setString(9, record.getSecondBoolValue());
                pstmt.setString(10, record.getCity());
                pstmt.addBatch();
                pstmt.clearParameters();
            }
            long start = System.currentTimeMillis();
            pstmt.executeBatch();
            conn.commit();
            long end = System.currentTimeMillis();
            System.out.println("total time taken to insert and commit the batch = " + (end - start) + " ms");
            System.out.println("\nBatch Execution Complete");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }


}