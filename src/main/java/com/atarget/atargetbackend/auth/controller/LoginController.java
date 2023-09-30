package com.atarget.atargetbackend.auth.controller;

import com.atarget.atargetbackend.auth.controller.request.LoginRequest;
import com.atarget.atargetbackend.auth.controller.response.LoginResponse;
import com.atarget.atargetbackend.auth.service.LoginService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
public class LoginController {

	private final LoginService loginService;

	@PostMapping(path = "/auth/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoginResponse> execute(final @NotNull @RequestBody @Valid LoginRequest request){

		var response = loginService.execute(request);

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
	}
}
