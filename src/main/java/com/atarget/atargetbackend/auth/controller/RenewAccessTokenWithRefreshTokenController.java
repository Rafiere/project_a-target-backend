package com.atarget.atargetbackend.auth.controller;

import com.atarget.atargetbackend.auth.controller.response.AccessTokenResponse;
import com.atarget.atargetbackend.auth.service.RenewAccessTokenWithRefreshTokenService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
public class RenewAccessTokenWithRefreshTokenController {

	private final RenewAccessTokenWithRefreshTokenService renewAccessTokenWithRefreshTokenService;

	@PostMapping(path = "/auth/refresh-token")
	public ResponseEntity<AccessTokenResponse> execute(@NotBlank @RequestHeader(HttpHeaders.AUTHORIZATION) String refreshToken){

		final String refreshTokenWithoutBearer = refreshToken.replace("Bearer ", "");

		final var response = renewAccessTokenWithRefreshTokenService.execute(refreshTokenWithoutBearer);

		return ResponseEntity.status(200).body(response);
	}
}
