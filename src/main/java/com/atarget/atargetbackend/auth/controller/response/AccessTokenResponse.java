package com.atarget.atargetbackend.auth.controller.response;

import com.atarget.atargetbackend.auth.service.wrapper.LoginWrapper;
import com.atarget.atargetbackend.auth.service.wrapper.RenewAccessTokenWithRefreshTokenWrapper;
import com.atarget.atargetbackend.shared.auth.enums.AuthTokenType;

public record AccessTokenResponse(String accessToken, AuthTokenType tokenType, Long expirationInSeconds) {

	public static AccessTokenResponse from(final LoginWrapper loginWrapper) {

		return new AccessTokenResponse(loginWrapper.generatedAccessToken(),
		                               AuthTokenType.ACCESS_TOKEN,
		                               loginWrapper.accessTokenExpirationInSeconds());
	}

	public static AccessTokenResponse from(final RenewAccessTokenWithRefreshTokenWrapper wrapper) {

		return new AccessTokenResponse(wrapper.accessToken(),
									   AuthTokenType.ACCESS_TOKEN,
		                               wrapper.expirationInSeconds());
	}
}
