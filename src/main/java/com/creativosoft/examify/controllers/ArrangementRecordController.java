// Package specification.
package com.creativosoft.examify.controllers;

// Importing libraries.
import com.creativosoft.examify.helpers.DBHandler;
import com.creativosoft.examify.helpers.PDF;
import com.creativosoft.examify.models.ArrangementRecord;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Annotation for rest controller.
@RestController
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
    List getArrangements(@PathVariable(value="studentRegistration") int studentRegistration) {
        Session session = handler.openSession();
        String hql = String.format("FROM ArrangementRecord R WHERE R.studentRegistrationNumber = %d", studentRegistration);
        return session.createQuery(hql).list();
    }

    // PDF Generation.
    // Enabling cross origin.
    @CrossOrigin
    // Get mapping annotation to get JSON response.
    @GetMapping("/{studentRegistration}/pdf")
    // Method to generate PDF.
    public @ResponseBody
    String getPDF(@PathVariable(value="studentRegistration") int studentRegistration) throws IOException {
        Session session = handler.openSession();
        String hql = String.format("FROM ArrangementRecord R WHERE R.studentRegistrationNumber = %d", studentRegistration);
        List arrangementListGeneric = session.createQuery(hql).list();
        List<ArrangementRecord> arrangementRecords = new ArrayList<>();
        for (Object object: arrangementListGeneric) {
            arrangementRecords.add((ArrangementRecord) object);
        }
        PDF.createPDF(arrangementRecords, studentRegistration);
        return "{" + "\"link\"" + ":" + "\"pdf/" + studentRegistration + "_arrangements" + ".pdf\"" + "}";
    }
}