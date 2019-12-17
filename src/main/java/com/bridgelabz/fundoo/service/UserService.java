package com.bridgelabz.fundoo.service;

import java.sql.SQLException;

import com.bridgelabz.fundoo.dto.LoginDto;
import com.bridgelabz.fundoo.dto.RegistrationDTO;
import com.bridgelabz.fundoo.model.User;

public interface UserService {
	
	public RegistrationDTO registration(RegistrationDTO registrationDTO);
	
	public boolean login(LoginDto loginDto) throws SQLException;
	
	public boolean verify(String token);
	
	public boolean resetPassword(String password,String email);
	
	

}
