package com.trungtamjava.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trungtamjava.dao.UserDao;
import com.trungtamjava.entity.User;
import com.trungtamjava.model.UserDTO;
import com.trungtamjava.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Override
	public void addUser(UserDTO userDTO) {
		User u = new User();
		u.setUsername(userDTO.getUsername());
		// mã hóa encode k có dicode thử viện 1 chiều
		//u.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
		u.setPhone(userDTO.getPhone());
		u.setAddress(userDTO.getAddress());
		u.setFullname(userDTO.getFullname());
		u.setImage(userDTO.getImage());
		u.setMail(userDTO.getMail());
		u.setInfomation(userDTO.getInfomation());
		u.setRole(userDTO.getRole());
		u.setEnable(userDTO.getEnable());

		userDao.addUser(u);
		
	}

	@Override
	public void updateUser(UserDTO userDTO) {
		User user = userDao.getUserById(userDTO.getId());
		if (user != null) {
			user.setUsername(userDTO.getUsername());
			//user.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
			user.setPhone(userDTO.getPhone());
			user.setAddress(userDTO.getAddress());
			user.setFullname(userDTO.getFullname());
			user.setImage(userDTO.getImage());
			user.setMail(userDTO.getMail());
			user.setInfomation(userDTO.getInfomation());
			user.setRole(userDTO.getRole());
			user.setEnable(userDTO.getEnable());
			
			userDao.updateUser(user);
		}

	}

	@Override
	public void deleteUser(int id) {
		User user = userDao.getUserById(id);
	       if(user != null){
	    	   userDao.deleteUser(user);
	       }
	}

	@Override
	public UserDTO getUserById(int id) {
		 User user = userDao.getUserById(id);
	        UserDTO userDTO = new UserDTO();
	        userDTO.setId(user.getId());
	        userDTO.setUsername(user.getUsername());
	        //userDTO.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
			userDTO.setPhone(user.getPhone());
			userDTO.setAddress(user.getAddress());
			userDTO.setFullname(user.getFullname());
			userDTO.setImage(user.getImage());
			userDTO.setMail(user.getMail());
			userDTO.setInfomation(user.getInfomation());
			userDTO.setRole(user.getRole());
			userDTO.setEnable(user.getEnable());
			
			return userDTO;
	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<User> users = userDao.getAllUsers();
		List<UserDTO> userDTOs = new ArrayList<UserDTO>();
		
		for(User user : users){
			UserDTO userDTO = new UserDTO();
			userDTO.setId(user.getId());
	        userDTO.setUsername(user.getUsername());
	        //userDTO.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
			userDTO.setPhone(user.getPhone());
			userDTO.setAddress(user.getAddress());
			userDTO.setFullname(user.getFullname());
			userDTO.setImage(user.getImage());
			userDTO.setMail(user.getMail());
			userDTO.setInfomation(user.getInfomation());
			userDTO.setRole(user.getRole());
			userDTO.setEnable(user.getEnable());
			
			userDTOs.add(userDTO);
		}
		return userDTOs;
	
	}

	@Override
	public List<UserDTO> search(String username, int start, int length) {
		List<User> users = userDao.search(username, start, length);
		List<UserDTO> userDTOs = new ArrayList<UserDTO>();
		for (User user : users) {
			userDTOs.add(convert(user));
		}
		return userDTOs;
		
	}

	private UserDTO convert(User user) {
		UserDTO userDTO = new UserDTO();
	     userDTO.setId(user.getId());
	     userDTO.setUsername(user.getUsername());
	     userDTO.setFullname(user.getFullname());
	     userDTO.setAddress(user.getAddress());
	     userDTO.setImage(user.getImage());
	     userDTO.setPhone(user.getPhone());
	     userDTO.setInfomation(user.getInfomation());
	     userDTO.setMail(user.getMail());
	     userDTO.setRole(user.getRole());
	     userDTO.setEnable(user.getEnable());
	     
		return userDTO;
	     
	}

	@Override
	public int countUserWhenSearch(String username) {
		
		return userDao.countUserWhenSearch(username);
	}
}
