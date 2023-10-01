package com.atarget.atargetbackend.auth.controller.response;

public record LoginResponse(AccessTokenResponse accessTokenResponse, RefreshTokenResponse refreshTokenResponse) {

	public static LoginResponse of(final String accessToken,
	                               final Long accessTokenExpirationInSeconds,
	                               final String refreshToken,
	                               final Long refreshTokenExpirationInSeconds) {

		return new LoginResponse(AccessTokenResponse.of(accessToken, accessTokenExpirationInSeconds),
		                         RefreshTokenResponse.of(refreshToken, refreshTokenExpirationInSeconds));
	}
}
