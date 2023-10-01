package com.atarget.atargetbackend.auth.controller.response;

import com.atarget.atargetbackend.shared.auth.enums.AuthTokenType;

public record RefreshTokenResponse(String refreshToken, AuthTokenType tokenType, Long expirationInSeconds) {

	public static RefreshTokenResponse of(final String refreshToken, final Long expirationInSeconds) {

		return new RefreshTokenResponse(refreshToken, AuthTokenType.REFRESH_TOKEN, expirationInSeconds);
	}
}
