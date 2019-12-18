package com.bridgelabz.fundoo.service;

import java.sql.SQLException;
import java.util.List;

import com.bridgelabz.fundoo.dto.LoginDto;
import com.bridgelabz.fundoo.dto.RegistrationDTO;
import com.bridgelabz.fundoo.model.User;

public interface UserService {
	
	public User registration(RegistrationDTO registrationDTO);
	
	public User login(LoginDto loginDto) ;
	
	public boolean verify(String token);
	
	public boolean resetPassword(String password,String email);
	
	public boolean validEmailId(String email);
	
	public List<User> getAllUser();

}
