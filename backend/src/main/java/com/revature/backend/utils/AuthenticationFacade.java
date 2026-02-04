package com.revature.backend.utils;

import com.revature.backend.auth.InvalidCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthenticationFacade implements IAuthenticationFacade {

	@Override
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	@Override
	public Integer getClaimedUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			throw new InvalidCredentialsException("request lacks auth cookie");
		}
		Optional<String> principal = Optional.ofNullable((String) authentication.getPrincipal());
		if (principal.isEmpty()) {
			throw new InvalidCredentialsException("auth cookie doesn't contain a user id");
		}

		return Integer.valueOf(principal.get());
	}
}