package com.creativosoft.examify;

import com.creativosoft.examify.helpers.Database;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.text.ParseException;

@SpringBootApplication
public class ExamifyApplication {

    public static void main(String[] args) throws IOException, ParseException {

        SpringApplication.run(ExamifyApplication.class, args);
        Database.createDatabase();
        System.out.println("Database Created Successfully...");
        System.out.println("Please visit: <http://localhost/arrangements> to find your exam schedule.");
    }
}