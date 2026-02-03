package com.revature.backend.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final SecretKey secretKey;
	private static final Set<String> unsecuredPaths = new HashSet<>();
	{
		unsecuredPaths.add("/register");
		unsecuredPaths.add("/login");
	}

	public JwtAuthenticationFilter(@Value("${secretkey}") String secretKeyString) {
		byte[] decodedKey = Base64.getDecoder().decode(secretKeyString);
		this.secretKey = Keys.hmacShaKeyFor(decodedKey);
		//System.out.println("DEBUG: Secret Key Set");
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		String path = request.getRequestURI();
		//System.out.println("ShouldFilter " + path + "? " + !unsecurePaths.contains(path));
		return unsecuredPaths.contains(path) || request.getMethod().equals(HttpMethod.GET.toString());
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
																	FilterChain chain) throws ServletException, IOException {

		Claims claims = extractToken(request);
		if (claims == null || !validToken(claims)) {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.getWriter().write(
					String.format(
							"{\"status\": 403, \"path\": \"%s\", \"error\": \"invalid token\", \"timestamp\": \"%s\"}",
							request.getRequestURL(),
							LocalDateTime.now()
					));
			return;
		}

		String username = (String) claims.get("username");
		String userId = (String) claims.get("userId");
		Authentication auth = new UsernamePasswordAuthenticationToken(
				userId,
				null,
				List.of(new SimpleGrantedAuthority("USER")));
		SecurityContextHolder.getContext().setAuthentication(auth);
		chain.doFilter(request, response);
	}

	private Claims extractToken(HttpServletRequest request) throws IOException {
		Cookie authCookie = WebUtils.getCookie(request, "Authentication");
		try {
			return Jwts.parser()
					.verifyWith(secretKey)
					.build()
					.parseSignedClaims(authCookie.getValue())
					.getPayload();
		} catch (NullPointerException e) {
			return null;
		}
	}

	private boolean validToken(Claims claims) {
		// I couldn't think of anything else to add here...
		return claims.containsKey("username");
	}
}
