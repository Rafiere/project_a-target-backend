package com.atarget.atargetbackend.config.security;

import com.atarget.atargetbackend.auth.domain.UserRole;
import com.atarget.atargetbackend.shared.routes.RoutesGroups;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final SecurityFilter securityFilter;


	@Bean
	public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {

		return http.csrf(AbstractHttpConfigurer::disable)
		           .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		           .authorizeHttpRequests(authorizeHttpRequestsConfig -> {
			           authorizeHttpRequestsConfig.requestMatchers(HttpMethod.GET, RoutesGroups.AUTH + "**", "/docs/swagger-ui")
			                                      .permitAll();
			           authorizeHttpRequestsConfig.requestMatchers(HttpMethod.POST, RoutesGroups.AUTH + "**", "/personas/register")
			                                      .permitAll();
			           authorizeHttpRequestsConfig.requestMatchers(HttpMethod.PUT, RoutesGroups.AUTH + "**")
			                                      .permitAll();
			           authorizeHttpRequestsConfig.requestMatchers("/admin/**")
			                                      .hasRole(UserRole.ADMIN.name());
			           authorizeHttpRequestsConfig.anyRequest()
					           .hasRole(UserRole.COMMON_USER.name());
		           }).addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class) //O "securityFilter" será aplicado antes do filtro "UsernamePasswordAuthenticationFilter".
		           .build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {

		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder(){

		return new BCryptPasswordEncoder();
	}
}
