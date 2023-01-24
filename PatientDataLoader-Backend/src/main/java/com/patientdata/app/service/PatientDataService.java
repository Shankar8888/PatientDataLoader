package com.patientdata.app.service;

import javax.validation.Valid;

import org.springframework.web.multipart.MultipartFile;

import com.patientdata.app.dto.PatientDTO;
import com.patientdata.app.payload.response.MasterResponseObject;

public interface PatientDataService {

	MasterResponseObject readExcelFile(MultipartFile file);

	MasterResponseObject getPatient(Long patientId);

	MasterResponseObject updatePatient(long patientId, @Valid PatientDTO patientDTO);

	MasterResponseObject getPatients();

}
