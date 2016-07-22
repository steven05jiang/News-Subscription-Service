package com.webservice.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.webservice.entity.User;
import com.webservice.exceptions.InvalidIndexException;

public class UserDAOImp implements UserDAO {

	private Session session;
	
	public UserDAOImp(Session session){
		this.session = session;
	}
	@Override
	public User createUser(User user){
		// TODO Auto-generated method stub
		if(user == null || user.getUsername() == null || user.getPassword() == null) return null;
		if(getUserByUsername(user.getUsername()) == null){
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
			session.refresh(user);
			return user;
		}
		return null;
	}

	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub 
		User user = (User)session.get(User.class, id);
		if(user == null){
			throw new InvalidIndexException();
		}
		session.refresh(user);
		return user;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public User getUserByUsername(String username){
		Criteria criteria = session.createCriteria(User.class);  
		criteria.add( Restrictions.eq("username", username) );
		List results = criteria.list();
		if(results.size() == 0){
			throw new InvalidIndexException();
		}
		User user = (User) results.get(0);
		session.refresh(user);
		return user;
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		if(user == null){
			throw new NullPointerException();
		}
		session.beginTransaction();
		session.update(user);
		session.getTransaction().commit();
	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		if(user == null){
			throw new NullPointerException();
		}
		session.beginTransaction();
		session.delete(user);
		session.getTransaction().commit();
	}

}
