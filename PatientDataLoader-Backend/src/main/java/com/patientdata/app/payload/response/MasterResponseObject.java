package com.patientdata.app.payload.response;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.patientdata.app.dto.PatientDTO;
import com.patientdata.app.models.InvalidPatient;
import com.patientdata.app.models.Patient;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MasterResponseObject {

	private String message;
	private HttpStatus status;
	private List<Patient> patientList;
	private List<InvalidPatient> invalidPatientList;
	private PatientDTO patientDTO;
	
	public PatientDTO getPatientDTO() {
		return patientDTO;
	}

	public void setPatientDTO(PatientDTO patientDTO) {
		this.patientDTO = patientDTO;
	}

	public MasterResponseObject(HttpStatus status, List<Patient> patientList,
			List<InvalidPatient> invalidPatientList) {
		super();
		this.status = status;
		this.patientList = patientList;
		this.invalidPatientList = invalidPatientList;
	}

	public MasterResponseObject(String message,HttpStatus status) {
		super();
		this.message=message;
		this.status = status;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public List<Patient> getPatientList() {
		return patientList;
	}

	public void setPatientList(List<Patient> patientList) {
		this.patientList = patientList;
	}

	public List<InvalidPatient> getInvalidPatientList() {
		return invalidPatientList;
	}

	public void setInvalidPatientList(List<InvalidPatient> invalidPatientList) {
		this.invalidPatientList = invalidPatientList;
	}

	public MasterResponseObject() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MasterResponseObject(String message) {
		// TODO Auto-generated constructor stub
		this.message=message;
	}

	public MasterResponseObject(HttpStatus status, PatientDTO patientDTO) {
		// TODO Auto-generated constructor stub
		super();
		this.status=status;
		this.patientDTO=patientDTO;
	}

	public MasterResponseObject(HttpStatus status, List<Patient> patientList) {
		this.status=status;
		this.patientList=patientList;
	}
	

}
