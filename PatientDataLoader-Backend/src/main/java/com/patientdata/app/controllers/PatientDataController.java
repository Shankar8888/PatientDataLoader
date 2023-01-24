package com.patientdata.app.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.patientdata.app.dto.PatientDTO;
import com.patientdata.app.exception.handleMethodArgumentNotValidException;
import com.patientdata.app.payload.response.MasterResponseObject;
import com.patientdata.app.service.PatientDataService;


@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PatientDataController {

	private final Logger logger = LoggerFactory.getLogger(PatientDataController.class);
	
	@Autowired
	private PatientDataService patientService;

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "load/patientdata", method = RequestMethod.POST)
	public ResponseEntity<MasterResponseObject> uploadExcelFile(@RequestParam("file") MultipartFile file){

		logger.info("Entering into uploadExcelFile method in PatientDataController");
		String message = "";
		MasterResponseObject response =null;

		try {
			 response =patientService.readExcelFile(file);
			// return ResponseEntity.status(HttpStatus.OK).body(message);
			 logger.info("Existing from uploadExcelFile method in PatientDataController");
		} catch (Exception e) {
			e.printStackTrace();
			message = "Could not upload the requested file: " + file.getOriginalFilename() + ". Please try to upload with different excel file..!";
			return ResponseEntity.status(500).body(new MasterResponseObject(message));
		}
		// }
		
		return ResponseEntity.status(response.getStatus()).body(response);
	
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value="/retrieve/{patient-id}")
	public ResponseEntity<Object> getPatient(@PathVariable("patient-id") Long patientId) {
		MasterResponseObject response=null;
		try {
		logger.info("Entering getPatient method in PatientDataController");
		response = patientService.getPatient(patientId);
		logger.info("Exiting getPatient method in PatientDataController");
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(500).body("Error Occured while fetching Patient");
		}
		return ResponseEntity.status(response.getStatus()).body(response);	
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/updatepatient/{patient-id}")
	public ResponseEntity<MasterResponseObject> updatePatient(@PathVariable("patient-id") long patientId,@Valid @RequestBody PatientDTO patientDTO) throws handleMethodArgumentNotValidException{
		
		MasterResponseObject response=null;
		try {
		logger.info("Entering updatePatient method in PatientDataController");
		response = patientService.updatePatient(patientId,patientDTO);
		logger.info("Exiting updatePatient method in PatientDataController");
		}catch(Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(500).body(new MasterResponseObject("Error Occured while updating patient details"));
		}
		return ResponseEntity.status(response.getStatus()).body(response);

	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value="/patients")
	public ResponseEntity<Object> getPatients() {
		MasterResponseObject response=null;
		try {
		logger.info("Entering getPatients method in PatientDataController");
		response = patientService.getPatients();
		logger.info("Exiting getPatients method in PatientDataController");
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(500).body("Error Occured while fetching Patient");
		}
		return ResponseEntity.status(response.getStatus()).body(response);	
	}
	
}
