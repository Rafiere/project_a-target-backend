package com.atarget.atargetbackend.auth.service.wrapper;

public record RenewAccessTokenWithRefreshTokenWrapper(String accessToken, Long expirationInSeconds) {

	public static RenewAccessTokenWithRefreshTokenWrapper of(final String accessToken,
	                                                         final Long expirationInSeconds) {

		return new RenewAccessTokenWithRefreshTokenWrapper(accessToken,
		                                                   expirationInSeconds);
	}
}