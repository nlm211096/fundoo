package com.bridgelabz.fundoo.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class RegistrationDTO {
    
	@Pattern(regexp="^[a-zA-Z0-9]{3}",message="length must be 3")  
	private String firstname;
     
	
	@Pattern(regexp="^[a-zA-Z0-9]{3}",message="length must be 3")
	private String lastname;
    
	@Email
	private String email;

	private Long phone;

	private String password;

	
}
