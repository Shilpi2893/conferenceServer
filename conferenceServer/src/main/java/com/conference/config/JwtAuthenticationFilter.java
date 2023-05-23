package com.conference.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.conference.service.impl.UserDetailsServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String requestTokenHeader = request.getHeader("Authorization");
		System.out.println("Request Token Header : " + requestTokenHeader);
		
		String userName = null;
		String jwtToken = null;
		
		if( requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ") ) {
			
			jwtToken = requestTokenHeader.substring(7);
			
			try {
				userName = this.jwtUtil.extractUsername(jwtToken);
				
			}catch(ExpiredJwtException e) {
				e.printStackTrace();
				System.out.println("Error : " + e.getMessage());
			}catch( Exception e) {
				e.printStackTrace();
				System.out.println("Error : " + e.getMessage());
			}
		}else {
			System.out.println("Invalid token, not starting with Bearer");
		}
		
		//Validating Token
		if( userName != null && SecurityContextHolder.getContext().getAuthentication() == null ) {
			
			final UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(userName);
			
			if( this.jwtUtil.validateToken(jwtToken, userDetails) ) {
				//Here we validating the token
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
						new UsernamePasswordAuthenticationToken( userDetails, null, userDetails.getAuthorities() );
				
				usernamePasswordAuthenticationToken.setDetails( new WebAuthenticationDetailsSource().buildDetails(request) );
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}else {
				System.out.println("Token is not valid");
			}
		}
		
		filterChain.doFilter(request, response);
	}

}
