package com.trungtamjava.service;

import java.util.List;

import com.trungtamjava.model.UserDTO;

public interface UserService {

public void addUser(UserDTO userDTO);
	
	public void updateUser(UserDTO userDTO);
	
	public void deleteUser(int id);
	
	public UserDTO getUserById(int id);
	
	public List<UserDTO> getAllUsers();
	
	List<UserDTO> search(String username, int start, int length);
	
	public int countUserWhenSearch(String username);
}
