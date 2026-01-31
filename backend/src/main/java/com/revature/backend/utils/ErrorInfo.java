package com.revature.backend.utils;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorInfo {
	public final String path;
	public final String error;
	public final LocalDateTime timestamp = LocalDateTime.now();
	public final Integer status;

	public ErrorInfo(StringBuffer path, String error, HttpStatus status) {
		this.path = path.toString();
		this.error = error;
		this.status = status.value();
	}
}
