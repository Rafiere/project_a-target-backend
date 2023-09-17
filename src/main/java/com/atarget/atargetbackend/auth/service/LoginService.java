package com.atarget.atargetbackend.auth.service;

import com.atarget.atargetbackend.auth.controller.request.LoginRequest;
import com.atarget.atargetbackend.auth.controller.response.LoginResponse;
import com.atarget.atargetbackend.auth.domain.User;
import com.atarget.atargetbackend.auth.domain.enums.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginService {

	private final AuthenticationManager authenticationManager;
	private final GenerateTokenService generateTokenService;

	@Value("${environment.api.security.token.access-token.expiration-in-seconds}") private Long accessTokenExpirationInSeconds;

	public LoginResponse execute(LoginRequest request) {

		var usernamePassword = new UsernamePasswordAuthenticationToken(request.email(), request.password());

		var auth = this.authenticationManager.authenticate(usernamePassword);

		var token = generateTokenService.generateToken((User) auth.getPrincipal());

		return new LoginResponse(token, TokenType.BEARER, accessTokenExpirationInSeconds);
	}
}
