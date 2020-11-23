package com.example.exception.hanlder;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.custom.exceptions.NotesAlreadyExistsException;


@ControllerAdvice
public class CommonExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {
	    String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();
	 
	    ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
	    return new ResponseEntity<Object>(
	      apiError, new HttpHeaders(), apiError.getStatus());
	}
	
	@ExceptionHandler(NotesAlreadyExistsException.class)
	public ResponseEntity<Object> handleNotesAlreadyExists(NotesAlreadyExistsException ex) {
		ApiError apiError = new ApiError(HttpStatus.NOT_ACCEPTABLE, ex.getLocalizedMessage(), "duplicate notes");
		//log.error("Logging the information that Notes Already Exists");
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
	}
}
