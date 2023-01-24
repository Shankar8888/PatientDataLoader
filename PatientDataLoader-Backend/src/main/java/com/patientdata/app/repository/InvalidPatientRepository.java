package com.patientdata.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patientdata.app.models.InvalidPatient;

@Repository
public interface InvalidPatientRepository extends JpaRepository<InvalidPatient, Long> {

}
