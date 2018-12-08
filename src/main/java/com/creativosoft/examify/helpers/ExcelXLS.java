// Package specification.
package com.creativosoft.examify.helpers;

// Importing libraries.
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

// ExcelXLS class definition.
class ExcelXLS {
    // Attributes.
    private FileInputStream fileInputStream;
    private Set<Row> rows;

    // Constructor.
    ExcelXLS(FileInputStream fileInputStream) throws IOException {
        // If fileInputStream is not null then initialize attributes.
        if (fileInputStream != null) {
            this.fileInputStream = fileInputStream;
            this.rows = new HashSet<>();
        }
        // Else return.
        else {
            return;
        }

        // Read XLS file.
        readXLS();
    }

    // Methods.
    // Getters.
    // Getter for rows.
    Set<Row> getRows() {
        return rows;
    }

    // Method to read xls file.
    private void readXLS() throws IOException {
        // Variables to get workbook and spreadsheet.
        HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
        HSSFSheet spreadsheet = workbook.getSheetAt(0);

        // Iterating rows using foreach loop.
        for (Row aSpreadsheet : spreadsheet) {

            // Skipping header line.
            if (aSpreadsheet.getRowNum() == 0)
                continue;

            // Reading row and adding it to rows set.
            HSSFRow row = (HSSFRow) aSpreadsheet;
            rows.add(row);

            // Breaking loop and last row num i.e. that contains no data.
            if (aSpreadsheet.getRowNum() == spreadsheet.getLastRowNum())
                break;
        }
    }
}