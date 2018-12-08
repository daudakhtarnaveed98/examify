// Package specification.
package com.creativosoft.examify.repositories;

// Importing libraries.
import com.creativosoft.examify.models.ArrangementRecord;
import org.springframework.data.repository.CrudRepository;

// ArrangementRecordRepository interface definition, it extends CrudRepository.
public interface ArrangementRecordRepository extends CrudRepository<ArrangementRecord, Integer> {

}