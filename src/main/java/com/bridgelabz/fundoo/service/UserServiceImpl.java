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
import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.repo.UserRepo;
import com.bridgelabz.fundoo.util.JwtServiceProvider;
import com.bridgelabz.fundoo.util.MailServiceProvider;

@Service
@Transactional
//@PropertySource("classpath:info.properties")
public class UserServiceImpl implements UserService {

	@Autowired
	private MailServiceProvider mailServiceProvider;

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

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public RegistrationDTO registration(RegistrationDTO registrationDTO) {
		User users = new User();

		BeanUtils.copyProperties(registrationDTO, users);
		try {
			
			User user = userRepo.checkByEmail(users.getEmail());
			if (user != null) {
				System.out.print("exist");
			}
			
		}catch (Exception e) {
			throw new UserException(emailException);
		}
		

		
		String url = "http://localhost:8080/users/verification/";
		String password = users.getPassword();
		String encryptPassword = passwordEncoder.encode(password);
		users.setPassword(encryptPassword);
		User userss = userRepo.save(users);
		if (userss != null) {
			String emai = userss.getEmail();
			String token = provider.generateToken(emai);

			mailServiceProvider.sendEmail(registrationDTO.getEmail(), "for authontication", url + token);

			BeanUtils.copyProperties(userss, registrationDTO);

			return registrationDTO;
		}

		return null;

	}

	@SuppressWarnings("unused")
	@Override
	public boolean login(LoginDto loginDto) throws SQLException {
		String encryptPassword = passwordEncoder.encode((loginDto.getPassword()));
		User user = userRepo.checkByEmail(loginDto.getEmailId());
		System.out.println("email111:" + user.getEmail());
		boolean s = user.isVerify();
		System.out.println("email==" + s);
		if (user != null) {
			if (user.isVerify()) {
				if (user.getPassword().equals(encryptPassword)) {
					return true;
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
		String email = provider.parseToken(token);
		User user = userRepo.checkByEmail(email);
		user.setVerify(true);
		User users = userRepo.save(user);

		if (users != null) {
			return true;
		}
		return false;

	}

	@Override
	public boolean resetPassword(String password, String token) {
		System.out.println(token);
		String paresedTokenEmail = provider.parseToken(token);
		List<User> users = userRepo.findAll();
		for (User user : users) {
			if (user.getEmail().equals(paresedTokenEmail)) {
				// user.setPassword(encryptPassword(password));
				userRepo.save(user);
				return true;
			}

		}
		return false;
	}
}
