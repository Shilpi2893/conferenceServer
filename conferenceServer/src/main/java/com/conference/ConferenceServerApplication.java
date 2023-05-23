package com.conference;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.conference.entity.Role;
import com.conference.entity.User;
import com.conference.entity.UserRole;
import com.conference.service.UserService;

@SpringBootApplication
public class ConferenceServerApplication implements CommandLineRunner {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ConferenceServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Starting application !!");
		
//		User user = new User();
//		user.setUserName("Shilpi12");
//		user.setFirstName("Shilpi");
//		user.setLastName("Yadav");
//		user.setEmail("shilpi@gmail.com");
//		user.setPassword(this.bCryptPasswordEncoder.encode( "abc" ));
//		user.setPhone("123456789");
//		user.setUniversityName("Swansea University");
//		user.setProfile("default.png");
//		
//		Role role = new Role();
//		role.setRoleId(40L);
//		role.setRoleName("ADMIN");
//		
//		UserRole userRole = new UserRole();
//		userRole.setRole(role);
//		userRole.setUser(user);
//		
//		Set<UserRole> userRoleSet = new HashSet<>();
//		userRoleSet.add(userRole);
//		
//		User user1 = this.userService.createUser(user, userRoleSet);
//		System.out.println(user1.getUserName());
	}

}
