package com.patientdata.app.payload.response;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageResponse {
	
	private String message;
	private HttpStatus status;

	public MessageResponse(String message) {
	    this.message = message;
	  }


	public MessageResponse(String message, HttpStatus status) {
		super();
		this.message = message;
		this.status = status;
	}


	public MessageResponse() {
		super();
		// TODO Auto-generated constructor stub
	}


	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
