package com.conference.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.conference.config.JwtUtil;
import com.conference.entity.JwtRequest;
import com.conference.entity.JwtResponse;
import com.conference.entity.User;
import com.conference.helper.UserFoundException;
import com.conference.service.impl.UserDetailsServiceImpl;

@RestController
@CrossOrigin("*")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	//Generating token using API
	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken( @RequestBody JwtRequest jwtRequest ) throws Exception {
		try {
			authenticate( jwtRequest.getUserName(), jwtRequest.getPassword() );
		}catch( UserFoundException e) {
			e.printStackTrace();
			throw new Exception("User is already there !!");
		}
		
		UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(jwtRequest.getUserName());
		String token = this.jwtUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new JwtResponse(token));
	}

	private void authenticate(String userName, String password) throws Exception {
		try {
			authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(userName, password) );
		}catch( DisabledException e ) {
			e.printStackTrace();
			throw new Exception("User is disable !!");
		}catch( BadCredentialsException e ) {
			e.printStackTrace();
			throw new Exception("Invalid credentials !!");
		}
	}
	
	//fetching current user
	@GetMapping("/current-user")
	public User currentUser( Principal principal) {
		return (User) this.userDetailsServiceImpl.loadUserByUsername( principal.getName() );
		
	}
}
