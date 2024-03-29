package com.bridgelabz.fundoo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GeneralException {
	
	@ExceptionHandler(value=UserException.class)
	public ResponseEntity<Object>getUserAuthentication(UserException e)
	{
		return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
		
	}
	
	@ExceptionHandler(value=NullPointerException.class)
	public ResponseEntity<Object>nullValueObject(Exception e)
	{
		return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
		
	}
	
	@ExceptionHandler(value=UserAlreadyRegistred.class)
	public ResponseEntity<Object>userRegistrationException(Exception e)
	{
		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		
	}
	public ResponseEntity<Object>EmailNotFoundException(Exception e)
	{
		return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
		
	}

}
