package com.mobileapplication.exposys.translate.response.model;

import java.util.Date;

public class ExceptionMessageResponseModel {
	
	private Date timestamp;
	private String message;
	
	
	public ExceptionMessageResponseModel() {
		
	}


	public ExceptionMessageResponseModel(Date timestamp, String message) {
		super();
		this.timestamp = timestamp;
		this.message = message;
	}





	public Date getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	

}
