package com.atarget.atargetbackend.shared.auth;

import com.atarget.atargetbackend.auth.domain.User;
import com.atarget.atargetbackend.shared.auth.enums.AuthTokenType;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GenerateAuthTokenComponent {

	@Value("${environment.app.name}") String appName;

	@Value("${environment.api.security.tokens.access-token.secret}") private String accessTokenSecret;
	@Value("${environment.api.security.tokens.access-token.expiration-in-seconds}") private Long
			accessTokenExpirationInSeconds;

	@Value("${environment.api.security.tokens.refresh-token.secret}") private String refreshTokenSecret;
	@Value("${environment.api.security.tokens.refresh-token.expiration-in-seconds}") private Long
			refreshTokenExpirationInSeconds;

	private static final String AMERICA_SAO_PAULO_OFF_SET = "-03:00";

	public String generateAccessToken(final User user) {

		final Algorithm algorithm = Algorithm.HMAC512(accessTokenSecret);

		try {
			return JWT.create()
			          .withJWTId(UUID.randomUUID()
			                         .toString())
			          .withIssuer(appName)
			          .withIssuedAt(Instant.now())
			          .withSubject(user.getEmail())
			          .withExpiresAt(generateExpirationDate(AuthTokenType.ACCESS_TOKEN))
			          .sign(algorithm);

		} catch (final JWTCreationException e) {

			throw new JWTCreationException("Error while generating the access token", e);
		}
	}

	public String generateRefreshToken(final User user) {

		final Algorithm algorithm = Algorithm.HMAC512(refreshTokenSecret);

		try {
			return JWT.create()
			          .withJWTId(UUID.randomUUID()
			                         .toString())
			          .withIssuer(appName)
			          .withIssuedAt(Instant.now())
			          .withSubject(user.getEmail())
			          .withExpiresAt(generateExpirationDate(AuthTokenType.REFRESH_TOKEN))
			          .sign(algorithm);

		} catch (final JWTCreationException e) {

			throw new JWTCreationException("Error while generating the refresh token", e);
		}
	}

	private Instant generateExpirationDate(final AuthTokenType authTokenType){

		return LocalDateTime.now()
		                    .plusSeconds(getCorrectExpirationTimeBasedInAuthTokenType(authTokenType))
		                    .toInstant(ZoneOffset.of(AMERICA_SAO_PAULO_OFF_SET));
	}

	private Long getCorrectExpirationTimeBasedInAuthTokenType(final AuthTokenType authTokenType) {

		return switch (authTokenType) {
			case ACCESS_TOKEN -> accessTokenExpirationInSeconds;
			case REFRESH_TOKEN -> refreshTokenExpirationInSeconds;
		};
	}
}