package com.bridgelabz.fundoo.repo;

import org.springframework.stereotype.Component;

import com.bridgelabz.fundoo.model.User;

@Component
public interface UserRepo {
	
	public User save(User user);

}
