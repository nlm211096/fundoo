package com.bridgelabz.fundoo.service;

import java.sql.SQLException;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bridgelabz.fundoo.dto.LoginDto;
import com.bridgelabz.fundoo.dto.RegistrationDTO;
import com.bridgelabz.fundoo.exception.EmailNotFoundException;
import com.bridgelabz.fundoo.exception.UserAlreadyRegistred;
import com.bridgelabz.fundoo.exception.UserException;
import com.bridgelabz.fundoo.exception.UserNotFoundException;
import com.bridgelabz.fundoo.model.Mail;
import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.repo.UserRepo;
import com.bridgelabz.fundoo.util.JwtServiceProvider;
import com.bridgelabz.fundoo.util.SendMail;

import com.bridgelabz.fundoo.util.RabbitMqSender;

@Service
@Transactional
//@PropertySource("classpath:info.properties")
public class UserServiceImpl implements UserService {
//
//	@Autowired
//	private MailServiceProvider mailServiceProvider;

	@Autowired
	private JwtServiceProvider provider;

	@Autowired
	private UserRepo userRepo;

	@Value("${emailException}")
	String emailException;

	@Value("${passwordException}")
	String passwordException;

	@Value("${verifyException}")
	String verifyException;

	@Value("${emailExistence}")
	String emailExistence;

	@Autowired
	private PasswordEncoder passwordEncoder;
	

	@Autowired
	SendMail sendMail;

	String fromEmail = System.getenv("fromEmail");
	String password = System.getenv("password");

	@Override
	public User registration(RegistrationDTO registrationDTO) {
		User users = new User();

		BeanUtils.copyProperties(registrationDTO, users);
		User user = userRepo.checkByEmail(users.getEmail());
		if (user != null) {
			throw new UserException(emailExistence);
		}
		String url = "http://localhost:8080/users/verification/";

		String encryptPassword = passwordEncoder.encode(users.getPassword());
		users.setPassword(encryptPassword);
		User userss = userRepo.save(users);
		if (userss != null) {

			String token = provider.generateToken(userss.getUserId());

			Mail mail = new Mail(fromEmail, userss.getEmail(), "neelam", url + token);

			sendMail.sendSimpleMessage(mail);
//			RabbitMqSender message = new RabbitMqSender();
//			message.setEmail(emai);
//			message.setLink("for Authorization");
//			message.setToken(url+token);
			// mailServiceProvider.rabitMailSend(message);

			// mailServiceProvider.sendEmail(registrationDTO.getEmail(), "for
			// authontication", url + token);

		}
		return userss;

	}

	// @SuppressWarnings("unused")
	
	
	@Override
	public String login(LoginDto loginDto) {

		
		
		
		User user = userRepo.checkByEmail(loginDto.getEmailId());
		boolean encryptPassword = passwordEncoder.matches(loginDto.getPassword(), user.getPassword());

		
		if (user != null) {
			if (user.isVerify()) {
				if (encryptPassword) {
					String token = provider.generateToken(user.getUserId());
					return token;
				} else {
					throw new UserException(passwordException);
				}
			} else {
				throw new UserException(verifyException);
			}
		} else {

			throw new UserException(emailException);
		}
	}

	@Override
	public boolean verify(String token) {
		Long userId = provider.parseToken(token);
		User user = userRepo.checById(userId);
		user.setVerify(true);
		User users = userRepo.save(user);

		if (users != null) {
			return true;
		}
		return false;

	}

	public boolean validEmailId(String email) {
		User user = userRepo.checkByEmail(email);
		if (user == null) {
			return false;
		}

		return true;

	}

	@Override
	public boolean resetPassword(String password, String token) {
		System.out.println(token);
		Long paresedUserId = provider.parseToken(token);
		User user = userRepo.checById(paresedUserId);
		user.setPassword(password);
		User use = userRepo.save(user);
		if (use == null) {

			return false;

		}
		return true;
	}

	@Override
	public List<User> getAllUser() {

		userRepo.findAll();

		return null;
	}

}
