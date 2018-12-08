package com.creativosoft.examify.helpers;
import com.creativosoft.examify.models.ArrangementRecord;
import org.apache.poi.ss.usermodel.Row;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
import java.util.Set;

public class Database {

    // Constructor.
    private Database(String pathToExcelFile) throws IOException, ParseException {
        Set<Row> rows;

        // Creating an excel file input stream.
        ExcelFileInputStream inputStream = new ExcelFileInputStream(pathToExcelFile);
        if (inputStream.getExcelFileExtension().equals(".xls")) {
            // Opening excel file and storing it into excelXLS.
            ExcelXLS excelXLS = new ExcelXLS(inputStream.getFileInputStream());
            rows = excelXLS.getRows();
            // If excel file has no rows, returning.
            if (excelXLS.getRows() == null)
                return;
            createTable(rows);
        }
        else {
            ExcelXLSX excelXLSX = new ExcelXLSX(inputStream.getFileInputStream());
            if (excelXLSX.getRows() == null)
                return;
            rows = excelXLSX.getRows();
            createTable(rows);
        }
    }

    // Method to create xls table.
    private static void createTable(@NotNull Set<Row> rows) throws ParseException {
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

    public static void createDatabase() throws IOException, ParseException {
        String path = "excel\\";
        File folder = new File(path);
        File[] files = folder.listFiles();
        int fileSelected;

        if (files != null) {
            if (files.length > 0) {
                int filePosition = 1;
                for (File file : files) {
                    System.out.println(filePosition + ". " + file.getPath());
                    filePosition++;
                }
            }
        }

        System.out.print("\nPlease enter the file number to create database: ");
        fileSelected = new Scanner(System.in).nextInt();

        if (files != null) {
            if (fileSelected <= 0 || fileSelected > files.length) {
                System.out.println("You selected wrong file.");
                return;
            }
            new Database(files[fileSelected-1].getPath());
        }
    }
}