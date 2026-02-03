package com.revature.backend.utils;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerMapping;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;


public class UserOwnershipFilter extends HttpFilter {

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response,
																	FilterChain chain) throws ServletException, IOException {

		System.out.println("do we even do this filter?");

		final Map<String, String> pathVariables = (Map<String, String>) request
				.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		// https://gist.github.com/ankushs92/0e97e070401a1bb5fe55

		final Optional<String> optionalUserId = Optional.ofNullable(pathVariables.get("userId"));

		if (optionalUserId.isPresent()) {
			Integer userId = Integer.valueOf(optionalUserId.get());
			filterUserOwnership(userId);
		}

		chain.doFilter(request, response);

	}

	private void filterUserOwnership(@Valid Integer userId) {
		System.out.println(userId);
		System.out.println("wassuppp");
	}

	private void filterPostOwnership(@Valid Integer postId) {

	}
}
