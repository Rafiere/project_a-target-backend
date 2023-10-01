package com.atarget.atargetbackend.auth.controller.response;

import com.atarget.atargetbackend.auth.service.wrapper.LoginWrapper;
import com.atarget.atargetbackend.shared.auth.enums.AuthTokenType;

public record RefreshTokenResponse(String refreshToken, AuthTokenType tokenType, Long expirationInSeconds) {

	public static RefreshTokenResponse from(final LoginWrapper loginWrapper) {

		return new RefreshTokenResponse(loginWrapper.generatedRefreshToken(),
		                                AuthTokenType.REFRESH_TOKEN,
		                                loginWrapper.refreshTokenExpirationInSeconds());
	}
}
