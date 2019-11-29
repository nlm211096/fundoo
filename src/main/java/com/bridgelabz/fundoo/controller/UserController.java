package com.bridgelabz.fundoo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.dto.RegistrationDTO;
import com.bridgelabz.fundoo.response.Response;
import com.bridgelabz.fundoo.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<Response> register(@Valid @RequestBody RegistrationDTO registrationDTO){
		if(userService.registration(registrationDTO))
		{
			return new ResponseEntity<Response>( new Response(HttpStatus.OK.value(),"success"),HttpStatus.OK);
			
		}
		return new ResponseEntity<Response>( new Response(HttpStatus.BAD_REQUEST.value(),"success"),HttpStatus.BAD_REQUEST);

			
	}
	
	

}
