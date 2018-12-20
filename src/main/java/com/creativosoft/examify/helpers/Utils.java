// Package specification.
package com.creativosoft.examify.helpers;

// Importing libraries.
import com.creativosoft.examify.models.ArrangementRecord;
import org.apache.poi.ss.usermodel.Row;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.TimeZone;

// Utils class definition.
abstract class Utils {
    // Methods.
    // Method to arrangements object for given row.
    static ArrangementRecord getArrangementObject(Row row) {
        // Variables.
        int serialNumber;
        int studentRegistrationNumber;
        String studentName;
        String courseName;
        String batch;
        String courseCode;
        Date date = null;
        String day;
        LocalTime startTime;
        LocalTime endTime;
        String examLocation;

        // Getting data from hssfRow.
        // Getting serialNumber.
        serialNumber = row.getRowNum();

        // Getting registration number.
        studentRegistrationNumber = Integer.parseInt(row.getCell(0).toString());

        // Getting student name.
        studentName = row.getCell(1).toString();

        // Getting course name.
        courseName = row.getCell(2).toString();

        // Getting batch.
        batch = row.getCell(3).toString();

        // Getting course code.
        courseCode = row.getCell(4).toString();

        // Splitting date and day.
        String[] dateDay = row.getCell(5).toString().split(",");

        // Getting day.
        day = dateDay[0];

        // Getting date.
        // Formatting for date.
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        dateFormatter.setTimeZone(TimeZone.getTimeZone("Asia/Karachi"));
        try {
            date = dateFormatter.parse(dateDay[1]);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Splitting start and end times.
        String[] startEndTime = row.getCell(6).toString().split("-");

        // Getting start time.
        startTime = LocalTime.parse(startEndTime[0]);

        // Getting end time.
        endTime = LocalTime.parse(startEndTime[1]);

        // Getting exam location.
        examLocation = row.getCell(7).toString();

        // Creating and returning a arrangement record object.
        return new ArrangementRecord(serialNumber, studentRegistrationNumber, studentName, courseName, batch, courseCode, date, day, startTime, endTime, examLocation);
    }
}