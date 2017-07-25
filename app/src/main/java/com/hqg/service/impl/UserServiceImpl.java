package com.hqg.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hqg.dao.UserDao;
import com.hqg.entity.User;
import com.hqg.service.UserService;

@Service("UserService")
public class UserServiceImpl implements UserService{

	 @Resource
	 private UserDao userDao;
	 
	 public User getUserById(String userId) {
		 return userDao.getUserId(userId); 
	 }

	public void addUser(User usera) {
		userDao.addUser(usera);
		
	}
		 
}
