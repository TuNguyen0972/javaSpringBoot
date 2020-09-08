package com.trungtamjava.dao;

import java.util.List;

import com.trungtamjava.entity.User;

public interface UserDao {
	public void addUser(User user);

	public void updateUser(User user); // update theo userEntity

	public void deleteUser(User user);

	public User getUserById(int id); // tim theo userEntity

	public List<User> getAllUsers();

	public User getUserByUsername(String username);
	
	List<User> search(String username, int start, int length);
	
	public int countUserWhenSearch(String username);
}
