package com.patientdata.app.exception;


public class RequestNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4154576415649491359L;

	public RequestNotFoundException(String message) {
		super(message);
		
	}
	
}
