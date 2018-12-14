// Package specification.
package com.creativosoft.examify.controllers;

// Importing libraries.
import com.creativosoft.examify.helpers.DBHandler;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// Annotation for rest controller.
@RestController
// Annotation for requesting url mapping.
@RequestMapping("/arrangements")
// ArrangementRecordController class definition.
public class ArrangementRecordController {

    // Attributes.
    private static DBHandler handler;

    // Constructor injection for recordRepository.
    @Autowired
    public ArrangementRecordController() {
        handler = new DBHandler();
    }

    // Enabling cross origin.
    @CrossOrigin
    // Get mapping annotation to get JSON response.
    @GetMapping("/{studentRegistration}")
    // Method to get JSON response at specified url.
    public @ResponseBody
    List getAllUsers(@PathVariable(value="studentRegistration") int studentRegistration) {
        Session session = handler.openSession();
        return session.createQuery("FROM ArrangementRecord R WHERE R.studentRegistrationNumber = " + studentRegistration).list();
    }
}