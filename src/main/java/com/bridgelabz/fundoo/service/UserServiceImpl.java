package com.bridgelabz.fundoo.service;

import java.time.LocalDateTime;
import java.util.List;

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
import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.repo.UserRepo;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private MailServiceProvider mailServiceProvider;

	private JwtServiceProvider provider = new JwtServiceProvider();

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private static PasswordEncoder encryptPassword = new BCryptPasswordEncoder();

	@Override
	public boolean registration(RegistrationDTO registrationDTO) {
		User user = registrationDTOToUser(registrationDTO);
		boolean isPresentEmail=validEmailId(user.getEmail());
		if(isPresentEmail)
		{
			return false;
		}
		String url = "http://localhost:8080/user/verification/";
		User userObj = userRepo.save(user);
		if (userObj != null) {

			String password = user.getPassword();
			String encryptPassword = encryptPassword(password);
			user.setPassword(encryptPassword);
			String emai = user.getEmail();
			String token = provider.generateToken(emai);
			mailServiceProvider.sendEmail(registrationDTO.getEmail(), "for authontication", url + token);

			return true;
		}
		return false;
	}

	public User registrationDTOToUser(RegistrationDTO registrationDTO) {
		User user = new User();
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

	private String encryptPassword(String plainTextPassword) {

		return encryptPassword.encode(plainTextPassword);

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

		return false;
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
			 user.setPassword(encryptPassword(password));
			 userRepo.save(user);
			 return true;
			}
		
	}
		return false;
}}
