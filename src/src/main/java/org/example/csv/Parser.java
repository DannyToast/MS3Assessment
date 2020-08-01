package org.example.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.example.database.InterviewTable;
import org.example.database.Records;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class Parser {

    private static final String SAMPLE_CSV_FILE = "./ms3Interview-bad.csv";

    /**
     * Function to parse input CSV into an ArrayList of Records objects. These Records are separated into failed or
     * successful record inputs, depending on if the record matches the correct number of columns. Failed records are 
     * excluded from being entered into the database and logged in the ms3Interview-bad.csv file. 
     * @throws IOException 
     * @throws InterruptedException
     */
    public static void parseCSV() throws IOException, InterruptedException {
        CSVFormat format = CSVFormat.EXCEL.withHeader("A", "B", "C", "D", "E", "F", "G", "H", "I", "J");
        CSVParser parser = new CSVParser(new FileReader("ms3Interview.csv"), format);

        List < Records > recs = new ArrayList < Records > ();
        List < CSVRecord > failedRecs = new ArrayList < CSVRecord > ();
        for (CSVRecord record: parser) {
            sortRecords(recs, failedRecs, record);
        }
        parser.close();
        writeFailedRecords(failedRecs);
        InterviewTable.insertRecords(recs);
        logResults(recs, failedRecs);
    }

    /**
     * Function for outputting results of parseCSV, including the total amount of successfully processed records and records
     * that failed to process due to incorrect format.
     * @param recs ArrayList of successful records
     * @param failedRecs ArrayList of failed records
     */
    private static void logResults(List<Records> recs, List<CSVRecord> failedRecs) {
        Logger logger = Logger.getLogger("ms3InterviewLog");
        try {
            FileHandler fh = new FileHandler("ms3Interview.log");
            logger.addHandler(fh);
            fh.setFormatter(new SimpleFormatter());
            logger.info("Records Received: " + (recs.size() + failedRecs.size()) + "\nRecords Successful: " + recs.size() + "\nRecords Failed: " + failedRecs.size());
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Function containing the logic to determine which records are to be marked as successful and which ones are to
     * be marked as failed and excluded from the database commit.
     * @param recs ArrayList of successful records
     * @param failedRecs ArrayList of failed records
     * @param record parsed CSV records object
     */
    private static void sortRecords(List < Records > recs, List < CSVRecord > failedRecs, CSVRecord record) {
        if (record.isConsistent()) {
            Records rec = new Records();
            rec.setFirstName(record.get("A"));
            rec.setLastName(record.get("B"));
            rec.setEmail(record.get("C"));
            rec.setGender(record.get("D"));
            rec.setImageData(record.get("E"));
            rec.setCard(record.get("F"));
            rec.setCharge(record.get("G"));
            rec.setFirstBoolValue(record.get("H"));
            rec.setSecondBoolValue(record.get("I"));
            rec.setCity(record.get("J"));
            recs.add(rec);
        }
        else{
            failedRecs.add(record);
        }
        }
    

    /**
     * Function to write complete failed records to new CSV file marked ms3Interview-bad.csv
     * @param records ArrayList of failedRecords as Apache Commons CSVRecord list, so as to output all extra columns
     * @throws IOException
     */
    private static void writeFailedRecords(List < CSVRecord > records) throws IOException {
        BufferedWriter writer = Files.newBufferedWriter(Paths.get(SAMPLE_CSV_FILE));
        CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT);
        for (CSVRecord record: records) {
            printer.printRecord(record);
        }
        printer.close();
    }

}