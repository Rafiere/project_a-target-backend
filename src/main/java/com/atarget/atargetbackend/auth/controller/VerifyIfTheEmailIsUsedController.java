package com.atarget.atargetbackend.auth.controller;

import com.atarget.atargetbackend.auth.service.VerifyIfTheEmailIsUsedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VerifyIfTheEmailIsUsedController {

	private final VerifyIfTheEmailIsUsedService service;

	@GetMapping("/auth/email/{email}")
	public ResponseEntity<Boolean> execute(@PathVariable String email){

		var response = service.execute(email);

		return ResponseEntity.status(200).body(response);
	}
}
