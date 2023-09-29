package com.atarget.atargetbackend.auth.controller;

import com.atarget.atargetbackend.auth.service.UseTokenForActivateAccountService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
public class UseTokenForActivateAccountController {

	private final UseTokenForActivateAccountService service;

	@GetMapping(path = "/auth/activate-account/{token}")
	public ResponseEntity<Void> register(@NotBlank @PathVariable String token) {

		service.execute(token);

		return ResponseEntity.status(204).build();
	}
}
