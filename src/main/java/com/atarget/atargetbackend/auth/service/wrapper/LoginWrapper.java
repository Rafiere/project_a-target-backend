package com.atarget.atargetbackend.auth.service.wrapper;

public record LoginWrapper(String generatedAccessToken, Long accessTokenExpirationInSeconds,
                           String generatedRefreshToken, Long refreshTokenExpirationInSeconds) {

	public static LoginWrapper of(final String generatedAccessToken,
	                              final Long accessTokenExpirationInSeconds,
	                              final String generatedRefreshToken,
	                              final Long refreshTokenExpirationInSeconds) {

		return new LoginWrapper(generatedAccessToken,
		                        accessTokenExpirationInSeconds,
		                        generatedRefreshToken,
		                        refreshTokenExpirationInSeconds);
	}
}
