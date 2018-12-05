package com.creativosoft.examify.helpers;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ExcelXLS {
    // Attributes.
    private FileInputStream fileInputStream;
    private Set<Row> rows;

    // Constructor.
    public ExcelXLS(FileInputStream fileInputStream) throws IOException {
        if (fileInputStream != null) {
            this.fileInputStream = fileInputStream;
            this.rows = new HashSet<>();
        }
        else {
            return;
        }
        readXLS();
    }

    // Methods.
    // Getters.
    // Getter for rows.
    public Set<Row> getRows() {
        return rows;
    }

    // Method to read xls file.
    private void readXLS() throws IOException {
        // Variables to get workbook and spreadsheet.
        HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
        HSSFSheet spreadsheet = workbook.getSheetAt(0);

        // Iterating rows using foreach loop.
        for (Row aSpreadsheet : spreadsheet) {
            if (aSpreadsheet.getRowNum() == 0)
                continue;

            HSSFRow row = (HSSFRow) aSpreadsheet;
            rows.add(row);

            if (aSpreadsheet.getRowNum() == spreadsheet.getLastRowNum())
                break;
        }
    }
}