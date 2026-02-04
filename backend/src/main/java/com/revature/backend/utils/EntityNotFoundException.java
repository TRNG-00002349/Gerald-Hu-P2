package com.revature.backend.utils;

public class EntityNotFoundException extends RuntimeException {
	public EntityNotFoundException(String entityType) {
		super(String.format("%s not found", entityType));
	}

	public EntityNotFoundException(String entityType, Integer id) {
		super(String.format("%s not found: %s", entityType, id));
	}

	public EntityNotFoundException(String entityType, String name) {
		super(String.format("%s not found: %s", entityType, name));
	}
}
