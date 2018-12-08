// Package specification.
package com.creativosoft.examify.controllers;

// Importing libraries.
import com.creativosoft.examify.models.ArrangementRecord;
import com.creativosoft.examify.repositories.ArrangementRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// Annotation for rest controller.
@RestController
// Annotation for requesting url mapping.
@RequestMapping(path = "arrangements")
// ArrangementRecordController class definition.
public class ArrangementRecordController {

    // Attributes.
    private final ArrangementRecordRepository recordRepository;

    // Constructor injection for recordRepository.
    @Autowired
    public ArrangementRecordController(ArrangementRecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    // Enabling cross origin.
    @CrossOrigin
    // Get mapping annotation to get JSON response.
    @GetMapping(path = "/all")
    // Method to get JSON response at specified url.
    public @ResponseBody
    Iterable<ArrangementRecord> getAllUsers() {
        return recordRepository.findAll();
    }
}