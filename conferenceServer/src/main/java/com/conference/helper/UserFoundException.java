package com.conference.helper;

public class UserFoundException extends Exception {
	
	public UserFoundException() {
		super("User with this user name is already there !! try with another user name...");
	}
	
	public UserFoundException( String msg ) {
		super(msg);
	}
}
