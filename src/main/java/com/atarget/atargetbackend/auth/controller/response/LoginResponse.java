package com.atarget.atargetbackend.auth.controller.response;

import com.atarget.atargetbackend.auth.domain.enums.TokenType;

public record LoginResponse(String accessToken, TokenType tokenType, Long expirationInSeconds) {

}
