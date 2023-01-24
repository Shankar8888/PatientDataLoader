package com.patientdata.app.Util;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class Util {

	
	public static final String DATE_FORMAT_ddmmyyyy = "dd-mm-yyyy";

	public static LocalDate convertStringToLocalDate(String dateVal, String dateFormat) {

		Date convertedDate = null;
		LocalDate convertedLocalDate = null;

		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);		
		try {
			
			if (dateVal != null) {
				convertedDate = sdf.parse(dateVal);
			
			//Getting the default zone id
			ZoneId defaultZoneId = ZoneId.systemDefault();
			
			//Converting the date to Instant
			Instant instant = convertedDate.toInstant();
			
			//Converting the Date to LocalDate
			convertedLocalDate = instant.atZone(defaultZoneId).toLocalDate();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return convertedLocalDate;
	}
	
public static boolean isStringEmpty(String inputStr) {
		
		if(!Objects.isNull(inputStr))
			return  inputStr.length()==0;	
		else
			return true;
	}
	
	public static boolean isStringEmptyWithTrim(String inputStr) {
		
		if(!Objects.isNull(inputStr))
			return  inputStr.trim().length()==0;	
		else
			return true;
	}

	public static boolean isObjectEmpty(Object obj) {

		for (Field field : obj.getClass().getDeclaredFields()) {
//			System.out.println("------------"+field);
			try {
				field.setAccessible(true);
				if (field.get(obj) != null && !(field.get(obj).toString().trim().equalsIgnoreCase(""))) {
//					System.out.println("------------"+field.get(obj));
					return false;
				}
			} catch (Exception e) {
				System.out.println("Exception occured in processing");
			}
		}
		return true;
	}
	
	public static boolean isNull(Object obj) {
		return Objects.isNull(obj);
	}
	
	public static boolean isEmptyArray(List<?> array){	
		if ( !Objects.isNull(array))
		  return array.isEmpty();
		else
		  return	true;
	}
	
	public static String convertDateToStr(Date dateVal, String dateFormat) {

		String strDate = null;
		SimpleDateFormat dateFormatObj = new SimpleDateFormat(dateFormat);
       
		try {
			if (dateVal != null) {
				strDate = dateFormatObj.format(dateVal);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return strDate;
	}
	
	public static final Pattern VALID_NAME_REGEX = 
		    Pattern.compile("^[ a-zA-Z]{5,30}+$");    //min 5 max 30
	
	public static final Pattern VALID_PHONE_REGEX = 
		    Pattern.compile("^[0-9]{10}$");
	
	public static final Pattern VALID_EMAIL_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	
	public static final Pattern VALID_DRUG_ID_REGEX = 
		    Pattern.compile("\\d{4}-\\d{4}-\\d{2}");
}
