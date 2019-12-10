package com.bridgelabz.fundoo.exception;

public class UserAlreadyRegistred extends RuntimeException {
	
	public UserAlreadyRegistred()
	{
		super("you are already registred");
	}

}
