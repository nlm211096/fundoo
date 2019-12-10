package com.bridgelabz.fundoo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.bridgelabz.fundoo.config.JwtServiceProvider;
import com.bridgelabz.fundoo.config.MailServiceProvider;
import com.bridgelabz.fundoo.dto.LoginDto;
import com.bridgelabz.fundoo.dto.RegistrationDTO;
import com.bridgelabz.fundoo.exception.UserAlreadyRegistred;
import com.bridgelabz.fundoo.exception.UserNotFoundException;
import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.repo.UserRepo;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private MailServiceProvider mailServiceProvider;
    
	@Autowired
	private JwtServiceProvider provider ;

	@Autowired
	private UserRepo userRepo;
	
	
	

	@Autowired
	private  PasswordEncoder  passwordEncoder  ;

	@Override
	public boolean registration(RegistrationDTO registrationDTO) {
		User users=new User();
		
		BeanUtils.copyProperties(registrationDTO, users);
		User user=userRepo.checkByEmail(users.getEmail());
	
		if(user!=null)
		{
			throw new UserAlreadyRegistred();
		}
		    String url = "http://localhost:8080/user/verification/";
		    String password = users.getPassword();
			String encryptPassword = passwordEncoder.encode(password); 
			users.setPassword(encryptPassword);
	      	User userss=userRepo.save(users);
	      	if(userss!=null)
	      	{	
			String emai = userss.getEmail();
			String token = provider.generateToken(emai);
			mailServiceProvider.sendEmail(registrationDTO.getEmail(), "for authontication", url + token);

		
		}
		return false;
	}


	

	@Override
	
	public boolean login(LoginDto loginDto) {
		User user = new User();
		System.out.println(loginDto.getEmailId() + loginDto.getEmailId());
		user.setEmail(loginDto.getEmailId());
		user.setPassword(loginDto.getPassword());
		User result = userRepo.checkValidation(user);
		if (result != null) {
			if (user.isVarified()) {
				return true;
			}

		}

		throw new UserNotFoundException();
	}

	@Override
	public boolean verify(String token) {

		List<User> user = userRepo.findAll();
		String paresedToken = provider.parseToken(token);
		for (User use : user) {
			String email = use.getEmail();
			if (email.equals(paresedToken) && (use.isVarified() == false)) {
				Boolean t = true;

				use.setVarified(t);
				userRepo.save(use);
				return true;
			} else {
				return false;
			}
		}

		return false;
	}

	@Override
	public boolean validEmailId(String emailId) {
		List<User> users = userRepo.findAll();
		for (User user : users) {
			if(user.getEmail().equals(emailId))
			{   
				
				
				String token = provider.generateToken(emailId);

				String url="http://localhost:8080/user/reSetPassword/";
				mailServiceProvider.sendEmail(emailId, "for update password", url + token);

				
				return true;
				
			}
			

		}
		return false;
	}
    
	
	@Override
	public boolean resetPassword(String password,String token) {
		System.out.println(token);
		String paresedTokenEmail = provider.parseToken(token);
		List<User> users = userRepo.findAll();
		for (User user : users) {
			if(user.getEmail().equals(paresedTokenEmail))
			{
			// user.setPassword(encryptPassword(password));
			 userRepo.save(user);
			 return true;
			}
		
	}
		return false;
}}
