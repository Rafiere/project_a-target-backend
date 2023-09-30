package com.atarget.atargetbackend.auth.controller.response;

import com.atarget.atargetbackend.shared.auth.enums.AuthTokenType;

public record LoginResponse(String accessToken, AuthTokenType tokenType, Long expirationInSeconds){

	public static LoginResponse of(String accessToken, Long expirationInSeconds){
		return new LoginResponse(accessToken, AuthTokenType.ACCESS_TOKEN, expirationInSeconds);
	}
}
