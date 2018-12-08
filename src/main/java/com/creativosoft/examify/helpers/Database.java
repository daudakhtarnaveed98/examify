// Package specification.
package com.creativosoft.examify.helpers;

// Importing libraries.
import com.creativosoft.examify.models.ArrangementRecord;
import org.apache.poi.ss.usermodel.Row;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.jetbrains.annotations.NotNull;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

// Database class definition.
public class Database {

    // Constructor with one parameter.
    private Database(String pathToExcelFile) throws IOException {
        Set<Row> rows;

        // Creating an excel file input stream.
        ExcelFileInputStream inputStream = new ExcelFileInputStream(pathToExcelFile);
        // If the file is .xls.
        if (inputStream.getExcelFileExtension().equals(".xls")) {
            // Opening excel file and storing it into excelXLS.
            ExcelXLS excelXLS = new ExcelXLS(inputStream.getFileInputStream());
            rows = excelXLS.getRows();
            // If excel file has no rows, returning.
            if (rows.size() == 0) {
                System.out.println("\033[0;31m" + "Error: Sheet is empty, terminating...");
                System.exit(0);
            }
            // Creating table.
            createTable(rows);
        }
        // Else the file will be .xlsx.
        else {
            // Opening excel file and storing it into excelXLSX.
            ExcelXLSX excelXLSX = new ExcelXLSX(inputStream.getFileInputStream());
            rows = excelXLSX.getRows();
            // If excel file has no rows, returning.
            if (rows.size() == 0) {
                System.out.println("\033[0;31m" + "Error: Sheet is empty, terminating...");
                System.exit(0);
            }
            // Creating table.
            createTable(rows);
        }
    }

    // Method to create xls table.
    private static void createTable(@NotNull Set<Row> rows) {
        // Creating configuration based on ArrangementRecord class.
        Configuration configuration = new Configuration().configure().addAnnotatedClass(ArrangementRecord.class);
        // Creating session factory based on above configuration.
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        // Creating session from above session factory.
        Session session = sessionFactory.openSession();

        // Iterating the rows set using for each loop.
        for (Row row : rows) {
            if (row.getPhysicalNumberOfCells() != 0) {
                // For each row, getting arrangement record object using Utils class.
                ArrangementRecord arrangementRecord = Utils.getArrangementObject(row);

                // Creating transaction to transact data.
                Transaction transaction = session.beginTransaction();

                // Saving arrangement record.
                session.save(arrangementRecord);

                // Committing transaction.
                transaction.commit();
            }
        }

        // Closing session.
        session.close();
        // Closing session factory.
        sessionFactory.close();
    }

    // Method to create database.
    public static void createDatabase() throws IOException {
        // Variables.
        String path = "excel\\";
        File folder = new File(path);
        File[] files = folder.listFiles();
        int fileSelected;

        // Checking if the folder exists and files is not null.
        if (files != null) {
            // Checking if it contains files.
            if (files.length > 0) {
                // Variable to printing file position. It is set to one to avoid confusion due to zero indexing of arrays.
                int filePosition = 1;
                // Printing each file in the folder along with a filePosition number to select it.
                for (File file : files) {
                    System.out.println(filePosition + ". " + file.getPath());
                    filePosition++;
                }
            }

            // Getting input for file number from user for which database needs to be created.
            System.out.print("\nPlease enter the file number to create database: ");
            fileSelected = new Scanner(System.in).nextInt();

            // If user selects a file that does not exist then print error and exit.
            if (fileSelected <= 0 || fileSelected > files.length) {
                System.out.println("\033[0;31m" + "Error: You selected wrong file, terminating...");
                System.exit(0);
            }

            // Create a new database based on selection by user.
            new Database(files[fileSelected-1].getPath());
            System.out.println("\033[0;32m" + "Database Created Successfully...");
            System.out.println("\033[0;32m" + "Please visit: <http://localhost:8081> to find your exam schedule.");
        }

        // Else printing error (Folder does not exist).
        else {
            System.out.println("\033[0;31m" + "Error: Folder " + path + " does not exist or does not have any files, terminating...\"");
            System.exit(0);
        }
    }
}