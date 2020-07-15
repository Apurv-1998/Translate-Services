package com.mobileapplication.exposys.translate.response.model;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TranslationResponse extends RepresentationModel<TranslationResponse> {

	public int statusCode;
	
	@JsonProperty("Response")
	public Object responseBody;
	

	public TranslationResponse(int statusCode) {
		super();
		this.statusCode = statusCode;
	}

	public TranslationResponse(int statusCode, Object responseBody) {
		super();
		this.statusCode = statusCode;
		this.responseBody = responseBody;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public Object getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(Object responseBody) {
		this.responseBody = responseBody;
	}

	
	@Override
	public String toString() {
		return "TranslationResponse [statusCode=" + statusCode + ", responseBody=" + responseBody + "]";
	}
	
	
	
	
}
