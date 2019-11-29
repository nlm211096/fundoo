package com.bridgelabz.fundoo.repo;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bridgelabz.fundoo.model.User;

@Component
public class UserRepoImpl implements UserRepo {
	@Autowired
	private EntityManager entitymanager;

	@Override
	public User save(User user) {
		Integer status=0;
		Session session = entitymanager.unwrap(Session.class);
		session.save(user);
		return user;

	}

}
