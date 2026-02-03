package com.revature.backend.utils;

import org.springframework.security.core.Authentication;
// https://www.baeldung.com/get-user-in-spring-security#interface

public interface IAuthenticationFacade {
	Authentication getAuthentication();
}
