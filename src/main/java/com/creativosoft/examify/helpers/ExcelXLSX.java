// Package specification.
package com.creativosoft.examify.helpers;

// Importing libraries.
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

// ExcelXLSX class definition.
class ExcelXLSX {
    // Attributes.
    private FileInputStream fileInputStream;
    private Set<Row> rows;

    // Constructor.
    ExcelXLSX(FileInputStream fileInputStream) throws IOException {
        // If fileInputStream is not null then initialize attributes.
        if (fileInputStream != null) {
            this.fileInputStream = fileInputStream;
            this.rows = new HashSet<>();
        }
        // Else return.
        else {
            return;
        }
        // Read XLSX file.
        readXLSX();
    }

    // Methods.
    // Getters.
    // Getter for rows.
    Set<Row> getRows() {
        return rows;
    }

    // Method to read xls file.
    private void readXLSX() throws IOException {
        // Variables to get workbook and spreadsheet.
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet spreadsheet = workbook.getSheetAt(0);

        // Iterating rows using foreach loop.
        for (Row aSpreadsheet : spreadsheet) {

            // Skipping header line.
            if (aSpreadsheet.getRowNum() == 0)
                continue;

            // Reading row and adding it to rows set.
            XSSFRow row = (XSSFRow) aSpreadsheet;
            rows.add(row);

            // Breaking loop and last row num i.e. that contains no data.
            if (aSpreadsheet.getRowNum() == spreadsheet.getLastRowNum())
                break;
        }
    }
}