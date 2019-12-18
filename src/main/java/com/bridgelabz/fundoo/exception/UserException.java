package com.bridgelabz.fundoo.exception;

import lombok.Getter;

import lombok.Setter;

@Getter
@Setter
public class UserException extends RuntimeException{
	
	String message ;
	
	public UserException(String message)
	{
		
	    super(message);
	    this.message=message;
	}
	
	
	
	
	
}
