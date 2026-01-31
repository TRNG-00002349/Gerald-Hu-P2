package com.revature.backend.utils;

public class EntityNotFoundException extends RuntimeException {
	public EntityNotFoundException(String entityType, Integer userId) {
		super(String.format("%s not found: %s", entityType, userId));
	}
}
