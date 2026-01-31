package com.revature.backend.utils;

public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(Integer userId) {
		super(String.format("user not found: %s", userId));
	}
}
