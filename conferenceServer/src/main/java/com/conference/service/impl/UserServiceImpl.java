package com.conference.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.conference.entity.User;
import com.conference.entity.UserRole;
import com.conference.entity.speaker.InvitedSpeaker;
import com.conference.repo.RoleRepository;
import com.conference.repo.UserRepository;
import com.conference.service.UserService;

@Service("UserService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	//Creating user
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
		
		//Here, will find user by user name.
		User local = this.userRepository.findByUserName( user.getUserName() );
		
		//if user is not null that means user is already there
		if( local != null ) {
			System.out.println("User is already exist !!");
			throw new Exception("User is already exist !!");
		}else {
			//getting user roles and save accordingly
			for( UserRole ur : userRoles ) {
				roleRepository.save( ur.getRole() );
			}
			//adding user roles to the user
			user.getUserRoles().addAll(userRoles);
			
			//saving the user
			local = this.userRepository.save(user);
		}
		
		//Returning user
		return local;
	}
	
	//Getting user by user name
	@Override
	public User getUser(String userName) {
		return this.userRepository.findByUserName(userName);
	}
	
	//Deleting user by id
	@Override
	public void deleteUser(Long userId) {
		this.userRepository.deleteById(userId);
	}

	@Override
	public List<User> getAllUser() {
		return this.userRepository.findAll();
	}
	
//	@Override
//	public List<User> getUsers() {
//		return this.userRepository.findAllUsers();
//	}

}
