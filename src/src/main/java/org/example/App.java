package org.example;

import java.io.IOException;


import static org.example.database.InterviewDatabase.createNewDatabase;
import static org.example.csv.Parser.parseCSV;
import static org.example.database.InterviewTable.createNewTable;

public class App {
    public static void main(String[] args) {
        createNewDatabase();
        try {
            createNewTable();
            parseCSV();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}