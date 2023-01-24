package com.patientdata.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patientdata.app.models.Patient;

@Repository
public interface PatientDataRepository extends JpaRepository<Patient, Long>{

	Patient findByPatientName(String patientName);

	Patient findByPatientId(Long patientId);

	
}
