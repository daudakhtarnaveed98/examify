// Package specification.
package com.creativosoft.examify;

// Importing libraries.
import com.creativosoft.examify.helpers.Database;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;

// Annotation for spring boot.
@SpringBootApplication
// ExamifyApplication class definition.
public class ExamifyApplication {
    // Main method to start program execution.
    public static void main(String[] args) throws IOException {
        // Running spring application.
        SpringApplication.run(ExamifyApplication.class, args);
        // Creating database.
        Database.createDatabase();
    }
}