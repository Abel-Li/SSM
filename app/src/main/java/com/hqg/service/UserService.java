package com.hqg.service;

import com.hqg.entity.User;

public interface UserService {
	
	public void addUser(User user);
	
	public User getUserById(String userId);
}
