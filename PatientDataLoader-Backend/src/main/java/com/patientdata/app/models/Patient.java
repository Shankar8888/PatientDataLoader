package com.patientdata.app.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.patientdata.app.dto.PatientDTO;

@Entity
@Table(name = "patient")
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long patientId;
	
	@Column(name = "patient_name")
	private String patientName;

	@Column(name = "patient_address")
	private String patientAddress;
	
	@Column(name = "patient_dob")
	private LocalDate patientDOB;
	
	@Column(name = "patient_email")
	private String patientEmail;
	
	@Column(name = "patient_phone")
	private String patientPhone;
	
	@Column(name = "drug_id")
	private String drugId;
	
	@Column(name = "drug_name")
	private String drugName;
	
	@Column(name = "patient_status")
	private String status;
	
	public Patient() {
	}

	public Patient(Long patientId, String patientName, String patientAddress, LocalDate patientDOB, String patientEmail,
			String patientPhone, String drugId, String drugName,String status) {
		super();
		this.patientId = patientId;
		this.patientName = patientName;
		this.patientAddress = patientAddress;
		this.patientDOB = patientDOB;
		this.patientEmail = patientEmail;
		this.patientPhone = patientPhone;
		this.drugId = drugId;
		this.drugName = drugName;
		this.status=status;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientAddress() {
		return patientAddress;
	}

	public void setPatientAddress(String patientAddress) {
		this.patientAddress = patientAddress;
	}

	public LocalDate getPatientDOB() {
		return patientDOB;
	}

	public void setPatientDOB(LocalDate patientDOB) {
		this.patientDOB = patientDOB;
	}

	public String getPatientEmail() {
		return patientEmail;
	}

	public void setPatientEmail(String patientEmail) {
		this.patientEmail = patientEmail;
	}

	public String getPatientPhone() {
		return patientPhone;
	}

	public void setPatientPhone(String patientPhone) {
		this.patientPhone = patientPhone;
	}

	public String getDrugId() {
		return drugId;
	}

	public void setDrugId(String drugId) {
		this.drugId = drugId;
	}

	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static PatientDTO setExistingPatientDetails(PatientDTO patientDTO, Patient patient) {
		if(patientDTO.getPatientId()==null)
			patientDTO.setPatientId(patient.getPatientId());
		if(patientDTO.getPatientName()==null)
			patientDTO.setPatientName(patient.getPatientName());
		if(patientDTO.getPatientAddress()==null)
			patientDTO.setPatientAddress(patient.getPatientAddress());
		if(patientDTO.getPatientDOB()==null)
			patientDTO.setPatientDOB(patient.getPatientDOB());
		if(patientDTO.getPatientEmail()==null)
			patientDTO.setPatientEmail(patient.getPatientEmail());
		if(patientDTO.getPatientPhone()==null)
			patientDTO.setPatientPhone(patient.getPatientPhone());
		if(patientDTO.getDrugId()==null)
			patientDTO.setDrugId(patient.getDrugId());
		if(patientDTO.getDrugName()==null)
			patientDTO.setDrugName(patient.getDrugName());
		
		return patientDTO;
	}
	
	
}
