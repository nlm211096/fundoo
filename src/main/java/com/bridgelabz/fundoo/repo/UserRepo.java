package com.bridgelabz.fundoo.repo;





import java.util.List;


import com.bridgelabz.fundoo.model.User;


public interface UserRepo {
	
	public User save(User user);
	 public User checkByEmail(String email) ;
	 public List< User > findAll();
	 public User checById(Long userId);
//	public User checkValidation(User user);
//	 public List< User > findAll() ;

}
