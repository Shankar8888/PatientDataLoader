package com.patientdata.app.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PatientDTO {

	private Long patientId;
	
	@NotBlank(message = "Patient Name Should Not be blank")
	@Size(min = 5,max = 30)
	private String patientName;
	
	@NotBlank(message = "Patient Address Should Not be blank")
	private String patientAddress;
	
	@NotNull(message = "Patient DOB Should Not be blank")
	private LocalDate patientDOB;
	
	@NotBlank(message = "Patient Email Should Not be blank")
	@Email
	private String patientEmail;
	
	@Size(min = 10,max = 10)
	@NotBlank(message = "Patient Contact Number Should Not be blank")
	private String patientPhone;
	
//	@NotBlank(message = "Drug Id Should Not be blank")
	private String drugId;
	
//	@NotBlank(message = "Drug Name Should Not be blank")
	private String drugName;
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
//	public String getDrugId() {
//		return drugId;
//	}
//	public void setDrugId(String drugId) {
//		this.drugId = drugId;
//	}
//	public String getDrugName() {
//		return drugName;
//	}
//	public void setDrugName(String drugName) {
//		this.drugName = drugName;
//	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	private String status;
}
