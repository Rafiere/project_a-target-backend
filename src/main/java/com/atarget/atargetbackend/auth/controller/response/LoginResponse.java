package com.atarget.atargetbackend.auth.controller.response;

import com.atarget.atargetbackend.auth.service.wrapper.LoginWrapper;

public record LoginResponse(AccessTokenResponse accessTokenResponse, RefreshTokenResponse refreshTokenResponse) {

	public static LoginResponse from(final LoginWrapper loginWrapper){

		return new LoginResponse(AccessTokenResponse.from(loginWrapper),
		                         RefreshTokenResponse.from(loginWrapper));
	}
}
