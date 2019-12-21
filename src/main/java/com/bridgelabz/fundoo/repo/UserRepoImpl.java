package com.bridgelabz.fundoo.repo;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;



import org.hibernate.Session;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bridgelabz.fundoo.model.User;

@Repository
public class UserRepoImpl implements UserRepo {
	@Autowired
	private EntityManager entitymanager;

	@Override
	public User save(User user) {
		
		Session session = entitymanager.unwrap(Session.class);
		session.save(user);
		Serializable serializable= session.save(user);
        if(serializable.hashCode()!=0)
        {
        	return user;
        }
         
	    return user;

	}
	 @Override
	 public List< User > findAll() {
		 Session session=entitymanager.unwrap(Session.class);
		  Query <User> query = session.createQuery("from User", User.class);
		  List<User>userList=query.getResultList();
		  return userList;
		 }

	 
	 
 public User checkByEmail(String email){
	System.out.println();

	Session session = entitymanager.unwrap(Session.class);
	Query query = session.createQuery("from User where email=:email");
	query.setParameter("email", email);
	
	User user=(User) query.uniqueResult();
	if(user==null)
	{
		return null;
	}
	return user;
	
	 }
 
 public User checById(Long userId)
 {
	 Session session = entitymanager.unwrap(Session.class);
		Query query = session.createQuery("from User where userId=:userId");
		query.setParameter("userId", userId);
		User user=(User) query.uniqueResult();
		if(user!=null)
		{
			return user;
		}
		return null;
 }
 
// 
//@Override
//public User checkValidation(User user) {
//	// TODO Auto-generated method stub
//	return null;
//}
	 
	 
//	 
//	@Override
//	public User checkValidation(User user) {
//	
//		List<User> users=findAll();
//		for(User userss:users)
//		{
//			if((userss.getEmail()).equals(user.getEmail())&&userss.getPassword().equals(user.getPassword()))
//			{
//				return user;
//			}
//		}
//		
//		return null;
//	}

}
