package com.conference.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.conference.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByUserName(String userName);
	
//	@Query("select distinct u from User u left join fetch u.invitedSpeaker")
//	public List<User> findAllUsers();

}
