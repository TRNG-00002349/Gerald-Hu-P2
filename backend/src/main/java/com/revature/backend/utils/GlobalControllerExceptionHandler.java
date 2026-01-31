package com.revature.backend.utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseBody ErrorInfo handleConstraintViolationException(HttpServletRequest req, ConstraintViolationException e) {
		List<String> violations = new ArrayList<>();
		for (ConstraintViolation violation : e.getConstraintViolations()) {
			violations.add(violation.getMessage());
		}
		return new ErrorInfo(req.getRequestURL(), String.join(", ", violations), HttpStatus.BAD_REQUEST);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody ErrorInfo handleMethodArgumentNotValidException(HttpServletRequest req, MethodArgumentNotValidException e) {
		List<String> violations = new ArrayList<>();
		for (ObjectError violation : e.getAllErrors()) {
			String message = ((FieldError) violation).getField() + " " + violation.getDefaultMessage();
			violations.add(message);
		}
		return new ErrorInfo(req.getRequestURL(), String.join(", ", violations), HttpStatus.BAD_REQUEST);
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseBody ErrorInfo handleNotFoundException(HttpServletRequest req, Exception e) {
		return new ErrorInfo(req.getRequestURL(), e.getMessage(), HttpStatus.NOT_FOUND);
	}
}
