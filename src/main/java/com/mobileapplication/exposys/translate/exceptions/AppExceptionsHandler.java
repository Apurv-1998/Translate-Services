package com.mobileapplication.exposys.translate.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.mobileapplication.exposys.translate.response.model.ExceptionMessageResponseModel;

@ControllerAdvice
public class AppExceptionsHandler {

	@ExceptionHandler(value = {TranslationServiceException.class})
	public ResponseEntity<Object> handleTranslationServiceException(TranslationServiceException exception,WebRequest request){
		
		ExceptionMessageResponseModel errorMessage = new ExceptionMessageResponseModel(new Date(),exception.getMessage());
		
		return new ResponseEntity<>(errorMessage,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> handleOtherExceptions(Exception exception,WebRequest request){
		
		ExceptionMessageResponseModel errorMessage = new ExceptionMessageResponseModel(new Date(),exception.getMessage());
		
		return new ResponseEntity<>(errorMessage,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
