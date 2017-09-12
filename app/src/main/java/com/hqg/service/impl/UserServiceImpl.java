package com.hqg.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hqg.dao.UserDao;
import com.hqg.entity.User;
import com.hqg.service.UserService;

@Transactional
@Service("UserService")
public class UserServiceImpl implements UserService{

	 @Autowired
	 private UserDao userDao;
	 
	 public User getUserById(String userId) {
		 return userDao.getUserId(userId); 
	 }

	public void addUser(User usera) {
		userDao.addUser(usera);
		
	}
		 
}
