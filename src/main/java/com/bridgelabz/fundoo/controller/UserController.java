package com.bridgelabz.fundoo.controller;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.dto.LoginDto;
import com.bridgelabz.fundoo.dto.RegistrationDTO;
import com.bridgelabz.fundoo.model.Password;
import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.response.Response;
import com.bridgelabz.fundoo.service.UserService;

@RestController
@RequestMapping("/users")
//@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<Response> register(@Valid @RequestBody RegistrationDTO registrationDTO){
		
		RegistrationDTO user=(userService.registration(registrationDTO));
		
		if(user!=null)
		{   
			return new ResponseEntity<Response>( new Response(HttpStatus.OK.value(), "please verify your email",user),HttpStatus.OK);
		}			
		else{
			return new ResponseEntity<Response>( new Response(HttpStatus.BAD_REQUEST.value(),"registration unsuccessfull",user),HttpStatus.BAD_REQUEST);
		}
      
			
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<Response> login(@Valid @RequestBody LoginDto logindto)
	
	{
		System.out.println(logindto.getEmailId());
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
	{   
		boolean isValidEmail=userService.validEmailId(emailId);
		if(isValidEmail)
		{ 
		
			return new ResponseEntity<Response>( new Response(HttpStatus.OK.value(),"please check your emailid"),HttpStatus.OK);
				
		}
		return new ResponseEntity<Response>( new Response(HttpStatus.BAD_REQUEST.value(),"this emailid is not existed"),HttpStatus.BAD_REQUEST);

		
	  }
	
	@GetMapping("/reSetPassword/{token}")
	public ResponseEntity<Response> resetPassword(@Valid @RequestBody Password pass,@PathVariable(name="token")String token)
			
	{  
		System.out.println("controller"+token);
		
		boolean isReset=userService.resetPassword(pass.getPassword(),token);
		if(isReset)
		{
			return new ResponseEntity<Response>( new Response(HttpStatus.OK.value(),"you have successfully forget password"),HttpStatus.OK);
				
		}
		return new ResponseEntity<Response>( new Response(HttpStatus.BAD_REQUEST.value(),"unsuccess"),HttpStatus.BAD_REQUEST);

	}

	
}
