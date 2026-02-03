package com.revature.backend.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final CorsFilter corsFilter;

	public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter, CorsFilter corsFilter) {
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
		this.corsFilter = corsFilter;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests((auth) -> auth
						//These next two items set paths to be permitted through spring security.
						//This doesn't allow it through our custom JWT filter, we need to also handle these paths there.
						.requestMatchers("/ping").permitAll()
						.requestMatchers("/register").permitAll()
						.requestMatchers("/login").permitAll()
						//.requestMatchers("/cookie-test").permitAll()
						// .requestMatchers("/api/admin/**").hasRole("ADMIN")
						.anyRequest().authenticated()
				)
//				.addFilterBefore(corsFilter, CorsFilter.class)
				.addFilterAfter(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);


		return http.build();
	}
}