// Package specification.
package com.creativosoft.examify.helpers;

// Importing libraries.
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

// ExcelFileInputStream class definition.
class ExcelFileInputStream {
    // Attributes.
    private String excelFileExtension;
    private FileInputStream fileInputStream;

    // Constructor.
    ExcelFileInputStream(String pathToExcelFile) throws FileNotFoundException {
        // Print error if user provides no path.
        if (pathToExcelFile.equals("")) {
            System.out.println("\033[0;31m" + "Error: path not provided.");
            return;
        }
        // Else check for file extension and set the attribute excelFileExtension.
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
        // Initialize fileInputStream attribute based on pathToExcelFile.
        this.fileInputStream = new FileInputStream(new File(pathToExcelFile));
    }

    // Methods.
    // Getter for pathToExcelFile.
    String getExcelFileExtension() {
        return excelFileExtension;
    }

    // Getter for fileInputStream.
    FileInputStream getFileInputStream() {
        return fileInputStream;
    }
}