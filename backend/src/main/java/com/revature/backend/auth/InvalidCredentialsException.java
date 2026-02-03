package com.revature.backend.auth;

public class InvalidCredentialsException extends RuntimeException {
	public InvalidCredentialsException(String message) {
		super(message);
	}

	public InvalidCredentialsException(Integer claimedUserId, String entityType, Integer pathId) {
		super(String.format("Current user %s doesn't own %s %s",
				claimedUserId.toString(),
				entityType,
				pathId.toString()
		));
	}
}
