package com.creativosoft.examify.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ExcelFileInputStream {
    // Attributes.
    private String excelFileExtension;
    private FileInputStream fileInputStream;

    // Constructor.
    public ExcelFileInputStream(String pathToExcelFile) throws FileNotFoundException {
        if (pathToExcelFile.equals("")) {
            System.out.println("Error: path not provided.");
            return;
        }
        else {
            if (pathToExcelFile.endsWith(".xls"))
                this.excelFileExtension = ".xls";
            else if (pathToExcelFile.endsWith(".xlsx"))
                this.excelFileExtension = ".xlsx";
            else {
                System.out.println("Error: file type not supported.");
                return;
            }
        }
        this.fileInputStream = new FileInputStream(new File(pathToExcelFile));
    }

    // Methods.
    // Getter for pathToExcelFile.
    public String getExcelFileExtension() {
        return excelFileExtension;
    }

    // Getter for fileInputStream.
    public FileInputStream getFileInputStream() {
        return fileInputStream;
    }
}