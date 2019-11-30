package com.bridgelabz.fundoo.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.bridgelabz.fundoo.dto.LoginDto;
import com.bridgelabz.fundoo.dto.RegistrationDTO;
import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.repo.UserRepo;


@Service
public class UserServiceImpl  implements UserService{
	
	
	@Autowired
	private UserRepo userRepo;
    @Transactional
	@Override
	public boolean registration( RegistrationDTO registrationDTO) {
    	User user=registrationDTOToUser(registrationDTO);
    	User userObj=userRepo.save(user);
    	if(userObj!=null) {
    		return true;
    	}
    	return false;
	}
    
    public User registrationDTOToUser(RegistrationDTO registrationDTO) {
    	User user=new User();
    	user.setFirstname(registrationDTO.getFirstname());
    	user.setLastname(registrationDTO.getLastname());
    	user.setEmail(registrationDTO.getEmail());
    	user.setPhone(registrationDTO.getPhone());
    	user.setPassword(registrationDTO.getPassword());
    	user.setCreatedStamp(LocalDateTime.now());
    	user.setVarified(false);
    	user.setUpdatedStamp(LocalDateTime.now());
    	
		return user;
    	
    }

	@Override
	public boolean login(LoginDto loginDto)
	{
		User user=new User();
		System.out.println(loginDto.getEmailId()+loginDto.getEmailId());
		user.setEmail(loginDto.getEmailId());
		user.setPassword(loginDto.getPassword());
		User result=userRepo.checkValidation(user);
		if(result!=null)
		{
			return true;
			
		}
		
		return false;
	}
   
	
	

}
