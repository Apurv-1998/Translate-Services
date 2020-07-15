package com.mobileapplication.exposys.translate.utils;

public enum ErrorMessages {

	ADMIN_NOT_VERIFIED("Wrong Admin Credentials"),
	NOT_A_SUPPORTED_LANGUAGE("This Code Does Not Match With Any Supported Language"),
	NULL_REQUEST_BODY_FIELDS("Request Body Fields Are Null"),
	CONNECTION_TO_SERVER_CANNOT_BE_MADE("Check The Entered Private Key Or The Internet Connection");

	private String errorMessages;

	private ErrorMessages(String errorMessages) {
		this.errorMessages = errorMessages;
	}

	public String getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(String errorMessages) {
		this.errorMessages = errorMessages;
	}

}
