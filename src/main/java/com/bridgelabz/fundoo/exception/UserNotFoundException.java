package com.bridgelabz.fundoo.exception;

public class UserNotFoundException extends RuntimeException {
	
     public UserNotFoundException()
     {
    	 super("user is not  found");
     }

}
