package com.webservice.dao;
import com.webservice.entity.User;

public interface UserDAO {
	public User createUser(User user);
	public User getUserById(int id);
	public User getUserByUsername(String username);
	public void updateUser(User user);
	public void deleteUser(User user);
}
