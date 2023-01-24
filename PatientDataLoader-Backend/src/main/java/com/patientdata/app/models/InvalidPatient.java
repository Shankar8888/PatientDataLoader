package com.patientdata.app.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "invalid_patient")
public class InvalidPatient {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id")
		private Integer id;
		
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
		
		@Column(name = "error_msg")
		private String errorMsg;
		

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getErrorMsg() {
			return errorMsg;
		}

		public void setErrorMsg(String errorMsg) {
			this.errorMsg = errorMsg;
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

		@Override
		public String toString() {
			return "InvalidPatient [id=" + id + ", patientName=" + patientName + ", patientAddress=" + patientAddress
					+ ", patientDOB=" + patientDOB + ", patientEmail=" + patientEmail + ", patientPhone=" + patientPhone
					+ ", drugId=" + drugId + ", drugName=" + drugName + ", status=" + status + ", errorMsg=" + errorMsg
					+ "]";
		}
		
}
