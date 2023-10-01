package com.atarget.atargetbackend.auth.service;

import com.atarget.atargetbackend.auth.controller.response.AccessTokenResponse;
import com.atarget.atargetbackend.auth.domain.User;
import com.atarget.atargetbackend.persona.repository.UserRepository;
import com.atarget.atargetbackend.shared.auth.GenerateAuthTokenComponent;
import com.atarget.atargetbackend.shared.auth.ManipulateAuthTokenComponent;
import com.atarget.atargetbackend.shared.auth.enums.AuthTokenType;
import com.atarget.atargetbackend.shared.exception.custom.ResourceNotFoundException;
import com.atarget.atargetbackend.shared.exception.custom.enums.Resources;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RenewAccessTokenWithRefreshTokenService {

	private final ManipulateAuthTokenComponent manipulateAuthTokenComponent;
	private final GenerateAuthTokenComponent generateAuthTokenComponent;
	private final UserRepository userRepository;

	@Value("${environment.api.security.tokens.access-token.expiration-in-seconds}") private Long
			accessTokenExpirationInSeconds;

		public AccessTokenResponse execute(final String refreshToken) {

		final var validatedJWT = manipulateAuthTokenComponent.validate(AuthTokenType.REFRESH_TOKEN, refreshToken);

		final String userEmail = validatedJWT.getSubject();

		final User user =  userRepository.findUserByEmail(userEmail).orElseThrow(() -> ResourceNotFoundException.of(Resources.EMAIL, userEmail));

		final String newAccessToken = generateAuthTokenComponent.generateAccessToken(user);

		return AccessTokenResponse.of(newAccessToken, accessTokenExpirationInSeconds);
	}
}
