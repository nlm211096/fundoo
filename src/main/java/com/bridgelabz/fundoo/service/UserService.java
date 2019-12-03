package com.bridgelabz.fundoo.service;

import com.bridgelabz.fundoo.dto.LoginDto;
import com.bridgelabz.fundoo.dto.RegistrationDTO;
import com.bridgelabz.fundoo.model.User;

public interface UserService {
	
	public boolean registration(RegistrationDTO registrationDTO);
	
	public boolean login(LoginDto loginDto);
	
	public boolean verify(String token);
	public boolean validEmailId(String emailid);
	public boolean resetPassword(String password,String email);
	
	

}
