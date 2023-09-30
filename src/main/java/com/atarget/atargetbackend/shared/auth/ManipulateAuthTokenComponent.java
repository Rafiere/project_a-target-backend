package com.atarget.atargetbackend.shared.auth;

import com.atarget.atargetbackend.shared.auth.enums.AuthTokenType;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ManipulateAuthTokenComponent {

	@Value("${environment.app.name}") String appName;
	@Value("${environment.api.security.tokens.access-token.secret}") private String accessTokenSecret;
	@Value("${environment.api.security.tokens.refresh-token.secret}") private String refreshTokenSecret;

	public DecodedJWT validate(final AuthTokenType authTokenType, String token) {

		return switch (authTokenType) {
			case ACCESS_TOKEN -> validateAccessToken(token);
			case REFRESH_TOKEN -> validateRefreshToken(token);
		};
	}

	private DecodedJWT validateRefreshToken(String token) {

		final Algorithm algorithm = Algorithm.HMAC512(refreshTokenSecret);

		return JWT.require(algorithm)
		          .withIssuer(appName)
		          .build()
		          .verify(token);
	}

	private DecodedJWT validateAccessToken(final String token) {

		final Algorithm algorithm = Algorithm.HMAC512(accessTokenSecret);

		return JWT.require(algorithm)
		          .withIssuer(appName)
		          .build()
		          .verify(token);
	}
}
