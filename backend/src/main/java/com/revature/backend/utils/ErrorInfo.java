package com.revature.backend.utils;

public class ErrorInfo {
	public final String url;
	public final String message;

	public ErrorInfo(String url, String message) {
		this.url = url;
		this.message = message;
	}

	public ErrorInfo(StringBuffer url, String message) {
		this.url = url.toString();
		this.message = message;
	}
}
