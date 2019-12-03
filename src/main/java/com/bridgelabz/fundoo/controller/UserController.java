package com.bridgelabz.fundoo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.dto.LoginDto;
import com.bridgelabz.fundoo.dto.RegistrationDTO;
import com.bridgelabz.fundoo.response.Response;
import com.bridgelabz.fundoo.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	String str;
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<Response> register(@Valid @RequestBody RegistrationDTO registrationDTO){
		if(userService.registration(registrationDTO))
		{   
			registrationDTO.setPassword("**********");
			return new ResponseEntity<Response>( new Response(HttpStatus.OK.value(), "Successfully"),HttpStatus.OK);
		}			
			
			
		return new ResponseEntity<Response>( new Response(HttpStatus.BAD_REQUEST.value(),"success"),HttpStatus.BAD_REQUEST);

			
	}
	
	
	@GetMapping("/login")
	public ResponseEntity<Response> login(@Valid @RequestBody LoginDto logindto)
	{
		if(userService.login(logindto))
		{
			return new ResponseEntity<Response>( new Response(HttpStatus.OK.value(),"success"),HttpStatus.OK);
				
		}
		return new ResponseEntity<Response>( new Response(HttpStatus.BAD_REQUEST.value(),"not authorized"),HttpStatus.BAD_REQUEST);

		
		
	}
	
	
	@GetMapping("/verification/{token}")
	public ResponseEntity<Response> verification(@PathVariable(name="token") String token){
	boolean verify=userService.verify(token);
	if(verify)
	{
	return new ResponseEntity<Response>( new Response(HttpStatus.OK.value(),"verification success"),HttpStatus.OK);
		
	}
	else return new ResponseEntity<Response>( new Response(HttpStatus.BAD_REQUEST.value(),"already verified"),HttpStatus.BAD_REQUEST);

		
	}
	
	
	@GetMapping("/forgot/{email}")
	public ResponseEntity<Response> forgotPassword(@PathVariable(name="email")String emailId)
	{   System.out.println("before valid");
		boolean isValidEmail=userService.validEmailId(emailId);
		if(isValidEmail)
		{  str=emailId;
			return new ResponseEntity<Response>( new Response(HttpStatus.OK.value(),"please check your emailid"),HttpStatus.OK);
				
		}
		return new ResponseEntity<Response>( new Response(HttpStatus.BAD_REQUEST.value(),"this emailid is not existed"),HttpStatus.BAD_REQUEST);

		
	}
	
	@RequestMapping("/resetpassword/{password}")
	public ResponseEntity<Response> resetPassword(@PathVariable(name="password")String password)
	{   
		boolean isReset=userService.resetPassword(password,str);
		if(isReset)
		{
			return new ResponseEntity<Response>( new Response(HttpStatus.OK.value(),"you have successfully forget password"),HttpStatus.OK);
				
		}
		return new ResponseEntity<Response>( new Response(HttpStatus.BAD_REQUEST.value(),"unsuccess"),HttpStatus.BAD_REQUEST);

	}

	
}
