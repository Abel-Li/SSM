package com.hqg.dao;

import com.hqg.entity.User;

public interface UserDao {
	
	public User getUserId(String userId);
	
	public void addUser(User user);
	
}
