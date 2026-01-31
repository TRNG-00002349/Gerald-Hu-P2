package com.revature.backend.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLException;

@ControllerAdvice
class GlobalControllerExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseBody ErrorInfo handleDataIntegrityViolation(HttpServletRequest req, SQLException e) {
		String message;
		if (e.getSQLState().equals("23502")) {
			message = "null or missing values in data";
		} else if (e.getSQLState().equals("23505")) {
			message = "duplicate data in database";
		} else {
			message = "bad request";
		}
		return new ErrorInfo(req.getRequestURL(), message, HttpStatus.BAD_REQUEST);
	}
}
