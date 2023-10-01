package com.atarget.atargetbackend.auth.service;

import com.atarget.atargetbackend.auth.controller.request.LoginRequest;
import com.atarget.atargetbackend.auth.controller.response.LoginResponse;
import com.atarget.atargetbackend.auth.domain.User;
import com.atarget.atargetbackend.auth.service.wrapper.LoginWrapper;
import com.atarget.atargetbackend.shared.auth.GenerateAuthTokenComponent;
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
	private final GenerateAuthTokenComponent generateAuthTokenComponent;

	@Value("${environment.api.security.tokens.access-token.expiration-in-seconds}") private Long
			accessTokenExpirationInSeconds;

	@Value("${environment.api.security.tokens.refresh-token.expiration-in-seconds}") private Long refreshTokenExpirationInSeconds;

	public LoginWrapper execute(final LoginRequest request) {

		final var usernamePassword = new UsernamePasswordAuthenticationToken(request.email(), request.password());

		final var auth = this.authenticationManager.authenticate(usernamePassword);

		final String generatedAccessToken = generateAuthTokenComponent.generateAccessToken((User) auth.getPrincipal());
		final String generatedRefreshToken = generateAuthTokenComponent.generateRefreshToken((User) auth.getPrincipal());

		return LoginWrapper.of(generatedAccessToken, accessTokenExpirationInSeconds, generatedRefreshToken, refreshTokenExpirationInSeconds);
	}
}
