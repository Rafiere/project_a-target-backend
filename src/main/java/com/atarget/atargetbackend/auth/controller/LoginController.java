package com.atarget.atargetbackend.auth.controller;

import com.atarget.atargetbackend.auth.controller.request.LoginRequest;
import com.atarget.atargetbackend.auth.controller.response.LoginResponse;
import com.atarget.atargetbackend.auth.service.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

	private final LoginService loginService;

	@PostMapping(path = "/auth/login", consumes = "application/json", produces = "application/json")
	public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request){

		var response = loginService.execute(request);

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
	}
}
