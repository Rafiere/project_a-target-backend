package com.atarget.atargetbackend.auth.controller.response;

import com.atarget.atargetbackend.auth.domain.enums.TokenType;

public record RefreshTokenResponse(String refreshToken, TokenType tokenType, Long expirationInSeconds) {

}
