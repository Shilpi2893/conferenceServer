package com.conference.service;

import java.util.List;
import java.util.Set;

import com.conference.entity.User;
import com.conference.entity.UserRole;

public interface UserService {
	
	//For creating user
	public User createUser( User user, Set<UserRole> userRoles ) throws Exception;
	
	//Getting user by user name
	public User getUser( String userName );
	
	//Getting all user
	public List<User> getAllUser();
	
	//Deleting user by id
	public  void deleteUser( Long userId );

}
