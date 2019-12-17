package com.bridgelabz.fundoo.repo;




import java.sql.SQLException;
import java.util.List;


import com.bridgelabz.fundoo.model.User;


public interface UserRepo {
	
	public User save(User user);
	 public User checkByEmail(String email) throws SQLException;
//	public User checkValidation(User user);
//	 public List< User > findAll() ;

}
