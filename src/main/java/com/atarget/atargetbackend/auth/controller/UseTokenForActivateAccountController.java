package com.atarget.atargetbackend.auth.controller;

import com.atarget.atargetbackend.auth.service.UseTokenForActivateAccountService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
public class UseTokenForActivateAccountController {

	private final UseTokenForActivateAccountService service;

	@PostMapping(path = "/tokens/{token}/personas/activate")
	public ResponseEntity<Void> register(@NotBlank @PathVariable String token) {

		service.execute(token);

		return ResponseEntity.status(204).build();
	}
}
