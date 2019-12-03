package com.bridgelabz.fundoo.repo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
        System.out.println("hi dao");
         System.out.println(user.isVarified());
         
	    return user;

	}
	
	 public List< User > findAll() {
		 Session session=entitymanager.unwrap(Session.class);
		  Query <User> query = session.createQuery("from User", User.class);
		  List<User>userList=query.getResultList();
		  return userList;
		 }

	 
	 
	@Override
	public User checkValidation(User user) {
	
		List<User> users=findAll();
		for(User userss:users)
		{
			if((userss.getEmail()).equals(user.getEmail())&&userss.getPassword().equals(user.getPassword()))
			{
				return user;
			}
		}
		
		return null;
	}

}
