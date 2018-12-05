package com.creativosoft.examify.controllers;

import com.creativosoft.examify.models.ArrangementRecord;
import com.creativosoft.examify.repositories.ArrangementRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "arrangements")
public class ArrangementRecordController {
    private final ArrangementRecordRepository recordRepository;

    @Autowired
    public ArrangementRecordController(ArrangementRecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<ArrangementRecord> getAllUsers() {
        return recordRepository.findAll();
    }
}