package com.atarget.atargetbackend.auth.controller;

import com.atarget.atargetbackend.auth.service.SendTokenForPasswordRecoveryService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
public class SendTokenForPasswordRecoveryController {

	private final SendTokenForPasswordRecoveryService service;

	@GetMapping("/auth/email/{email}")
	public ResponseEntity<Void> execute(@NotBlank @PathVariable String email){

		service.execute(email);

		return ResponseEntity.status(204).build();
	}
}