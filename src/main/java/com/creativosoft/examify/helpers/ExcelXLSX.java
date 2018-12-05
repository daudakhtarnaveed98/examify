package com.creativosoft.examify.helpers;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ExcelXLSX {
    // Attributes.
    private FileInputStream fileInputStream;
    private Set<Row> rows;

    // Constructor.
    public ExcelXLSX(FileInputStream fileInputStream) throws IOException {
        if (fileInputStream != null) {
            this.fileInputStream = fileInputStream;
            this.rows = new HashSet<>();
        }
        else {
            return;
        }
        readXLSX();
    }

    // Methods.
    // Getters.
    // Getter for rows.
    public Set<Row> getRows() {
        return rows;
    }

    // Method to read xls file.
    private void readXLSX() throws IOException {
        // Variables to get workbook and spreadsheet.
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet spreadsheet = workbook.getSheetAt(0);

        // Iterating rows using foreach loop.
        for (Row aSpreadsheet : spreadsheet) {
            if (aSpreadsheet.getRowNum() == 0)
                continue;

            XSSFRow row = (XSSFRow) aSpreadsheet;
            rows.add(row);

            if (aSpreadsheet.getRowNum() == spreadsheet.getLastRowNum())
                break;
        }
    }
}