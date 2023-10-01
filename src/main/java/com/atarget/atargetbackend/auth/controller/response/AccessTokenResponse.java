package com.atarget.atargetbackend.auth.controller.response;

import com.atarget.atargetbackend.shared.auth.enums.AuthTokenType;

public record AccessTokenResponse(String accessToken, AuthTokenType tokenType, Long expirationInSeconds) {

	public static AccessTokenResponse of(final String accessToken, final Long expirationInSeconds) {

		return new AccessTokenResponse(accessToken, AuthTokenType.ACCESS_TOKEN, expirationInSeconds);
	}
}
