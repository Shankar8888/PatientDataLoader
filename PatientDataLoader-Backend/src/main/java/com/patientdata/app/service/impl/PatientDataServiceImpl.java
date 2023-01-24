package com.patientdata.app.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.patientdata.app.Util.Util;
import com.patientdata.app.dto.PatientDTO;
import com.patientdata.app.models.InvalidPatient;
import com.patientdata.app.models.Patient;
import com.patientdata.app.payload.response.MasterResponseObject;
import com.patientdata.app.payload.response.MessageResponse;
import com.patientdata.app.repository.InvalidPatientRepository;
import com.patientdata.app.repository.PatientDataRepository;
import com.patientdata.app.service.PatientDataService;

@Service
public class PatientDataServiceImpl implements PatientDataService {

	static final Logger logger = LoggerFactory.getLogger(PatientDataServiceImpl.class);

	@Autowired
	PatientDataRepository patientRepo;

	@Autowired
	InvalidPatientRepository invalidPatientRepo;

	public MasterResponseObject readExcelFile(MultipartFile file) {

		logger.info("Entering into readExcelFile method in PatientDataServiceImpl");
		MasterResponseObject messageResponse = validateExcelData(file);
		logger.info("Exiting from readExcelFile method in PatientDataServiceImpl");
		return messageResponse;
	}

	@Transactional
	private MasterResponseObject validateExcelData(MultipartFile file) {
		logger.info("Inside validateExcelData method in PatientDataServiceImpl");
		MessageResponse response = new MessageResponse();
		List<Patient> patientDetailList = new ArrayList<Patient>();
		List<InvalidPatient> invalidPatientList = new ArrayList<>();
		String responseMsg = "";

		try {
//			Workbook workbook = new XSSFWorkbook(file.getInputStream());
			Workbook workbook = WorkbookFactory.create(file.getInputStream());

			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = sheet.iterator();

			while (iterator.hasNext()) {

				XSSFRow currentRow = (XSSFRow) iterator.next();
				if (currentRow.getRowNum() < 1) {
					//// System.out.println("Skipped Header" + currentRow.getRowNum());
				} else {

					Patient patientDetail = new Patient();

					Iterator<Cell> cellsInRow = currentRow.cellIterator();

					while (cellsInRow.hasNext()) {
						XSSFCell currentCell = (XSSFCell) cellsInRow.next();

						System.out.println(currentCell.getColumnIndex());//cell.getStringCellValue().length());
						switch (currentCell.getColumnIndex()) {

						case 0:

							String value0 = compareTypeAndReturnValue(currentCell);
							 if (!(Util.VALID_NAME_REGEX.matcher(value0).matches())) {
								responseMsg += "The Patient Name should contain only alphabets and length should be min 5 and max 30 characters; ";
							}
							patientDetail.setPatientName(value0);

							 System.out.print("PatientName:" + value0 + "\t");
							break;

						case 1:

							String value1 = compareTypeAndReturnValue(currentCell);
							patientDetail.setPatientAddress(value1);

							 System.out.print("PatientAddress:" + value1 + "\t");
							break;

						case 2:

							String value2 = compareTypeAndReturnValue(currentCell);

							try {
								LocalDate date = Util.convertStringToLocalDate(value2, Util.DATE_FORMAT_ddmmyyyy);
								patientDetail.setPatientDOB(date);
								if (LocalDate.now().isBefore(date)) {
									responseMsg += "DateOfBirth should be less than system date; ";
								}
							} catch (Exception e) {
								responseMsg += "DateOfBirth should be in MM-DD-YYYY format; ";
							}

							 System.out.print("PatientDOB:" + value2 + "\t");
							break;

						case 3:

							String value3 = compareTypeAndReturnValue(currentCell);
							
							if (!(Util.VALID_EMAIL_REGEX.matcher(value3).matches())) {
								responseMsg += "Invalid Email address; ";
							}
							patientDetail.setPatientEmail(value3);

							 System.out.print("PatientEmail:" + value3 + "\t");
							break;

						case 4:

							String value4 = compareTypeAndReturnValue(currentCell);
							
							if (!(Util.VALID_PHONE_REGEX.matcher(value4).matches())) {
								responseMsg += "Invalid Patient Contact Number; ";
							}
							patientDetail.setPatientPhone(value4);

							 System.out.print("PatientPhone:" + value4 + "\t");
							break;

						case 5:

							String value5 = compareTypeAndReturnValue(currentCell);
							patientDetail.setDrugId(value5);
							
							if (!(Util.VALID_DRUG_ID_REGEX.matcher(value5).matches())) {
								responseMsg += "Drug Id should be in the numbers format of ‘XXXXX-XXXX-XX’ ; ";
							}

							 System.out.print("DrugId:" + value5 + "\t");
							break;

						case 6:

							String value6 = compareTypeAndReturnValue(currentCell);
							patientDetail.setDrugName(value6);
							
							 System.out.print("DrugName:" + value6 + "\t");
							break;

						}
					}

					if (!Util.isObjectEmpty(patientDetail)) {
						// set excel row number
						responseMsg+= Util.isNull(patientDetail.getPatientName())? "Patient Name should not be blank; ":"";
						responseMsg+= Util.isNull(patientDetail.getPatientAddress())? "Patient Address should not be blank; ":"";
						responseMsg+= Util.isNull(patientDetail.getPatientDOB())? "Date Of Birth should not be blank; ":"";
						responseMsg+= Util.isNull(patientDetail.getPatientEmail())? "Patient Email should not be blank; ":"";
						responseMsg+= Util.isNull(patientDetail.getPatientPhone())? "Patient Contact number should not be Empty; ":"";
						responseMsg+= Util.isNull(patientDetail.getDrugId())? "Drug Id should not be blank; ":"";
						responseMsg+= Util.isNull(patientDetail.getDrugName())? "Drug Name should not be blank; ":"";
						
//							patientDetail.setExcelRowNo(currentRow.getRowNum() + 1);
						if ("".equals(responseMsg)) {
							patientDetail.setStatus("Inducted");
							Patient savedPatient = patientRepo.save(patientDetail);
							patientDetailList.add(savedPatient);
						} else {
							ModelMapper modelMapper=new ModelMapper();
							modelMapper.getConfiguration().setAmbiguityIgnored(true);
							InvalidPatient invalidRecord = modelMapper.map(patientDetail, InvalidPatient.class);
							invalidRecord.setStatus("Validation Failed");
							invalidRecord.setErrorMsg(responseMsg);
							InvalidPatient invalidPatient = invalidPatientRepo.save(invalidRecord);
							invalidPatientList.add(invalidPatient);
						}
						responseMsg = "";
					}

				}
			}

			return new MasterResponseObject(HttpStatus.OK, patientDetailList, invalidPatientList);

		} catch (IOException e) {
			return new MasterResponseObject("didn't processed your request. due to : " + e.getCause(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@SuppressWarnings("deprecation")
	public static String compareTypeAndReturnValue(XSSFCell currentCell) {

		String value = null;
		if (!(currentCell.getCellType().toString().equalsIgnoreCase("BLANK"))) {

			if (currentCell.getCellType().toString().equalsIgnoreCase("NUMERIC")) {

				// check for celltype is date
				if (HSSFDateUtil.isCellDateFormatted(currentCell)) {
					value = Util.convertDateToStr(currentCell.getDateCellValue(), Util.DATE_FORMAT_ddmmyyyy);
				} else {
					value = String.valueOf(currentCell.getNumericCellValue());

					value = value.endsWith(".0") ? value.replace(".0", "") : value;
					// to handle exponetial value
					if (value.contains("E")) {
						BigDecimal bd = new BigDecimal(value);
						value = String.valueOf(bd.longValue());
					}
				}
				value = value.trim();
			} else if (currentCell.getCellType().toString().equalsIgnoreCase("STRING")) {
				value = String.valueOf(currentCell.getStringCellValue());
				value = value.trim();
			}

			if (!(Util.isNull(value)) && "".equalsIgnoreCase(value.trim())) {
				value = null;
			}
		}

		return value;
	}

	public MasterResponseObject getPatient(Long patientId) {
		  logger.info("Inside getPatient method in PatientDataServiceImpl");
			Optional<Patient> patient= patientRepo.findById(patientId);
			 if(patient.isEmpty()) {
				 return new MasterResponseObject("Patient Details not found in record..!",HttpStatus.NOT_FOUND);
			 }else {
//			if(!patient.get().getStatus().equalsIgnoreCase("Inducted"))
				 PatientDTO patientDTO=new ModelMapper().map(patient.get(),PatientDTO.class);
			        logger.info("Exiting getPatient method in PatientDataServiceImpl");
			        return new MasterResponseObject(HttpStatus.OK,patientDTO);
	}
	}

	@Transactional
	@Modifying
	public MasterResponseObject updatePatient(long id, PatientDTO patientDTO) {
		Optional<Patient> patient=patientRepo.findById(id);
		 
		 if(patient.isEmpty()) {
			 return new MasterResponseObject("Patient not found in records with patientId: "+patientDTO.getPatientId(),HttpStatus.NOT_FOUND);
		 }else {
			 patientDTO= Patient.setExistingPatientDetails(patientDTO,patient.get());
			 Patient updatedPatientDetails=new ModelMapper().map(patientDTO, Patient.class);
			 updatedPatientDetails.setStatus("Processed");
			 patientRepo.save(updatedPatientDetails);
		 return new MasterResponseObject("Requested patient Updated successfully..!",HttpStatus.OK);
		 }
	}

	@Override
	public MasterResponseObject getPatients() {
		logger.info("Inside getPatients method in PatientService");
		List<Patient> patients= patientRepo.findAll();
//		List<BookWithoutContent> bookResponse=new ArrayList<BookWithoutContent>();
		
		 if(patients.size()==0) {
			 return new MasterResponseObject("Patients not found in database..!",HttpStatus.NOT_FOUND);
		 }
//		 else { 
//				if(patients.stream().filter(p->p.getStatus().equalsIgnoreCase("Inducted")).count() == 0) {
//					 return new MasterResponseObject("Inducted Patients not found in database..!",HttpStatus.NOT_FOUND);	 
//				 }else if(patients.stream().filter(p->p.getStatus().equalsIgnoreCase("Processed")).count() == 0) {
//					 return new MasterResponseObject("Processed Patients not found in database..!",HttpStatus.NOT_FOUND);	 
//				 }
//		 }

		  return new MasterResponseObject(HttpStatus.OK,patients);
	}

}
