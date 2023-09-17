package com.atarget.atargetbackend.auth.controller;

import com.atarget.atargetbackend.auth.controller.request.CreatePersonaRequest;
import com.atarget.atargetbackend.auth.controller.response.CreatePersonaResponse;
import com.atarget.atargetbackend.auth.service.CreatePersonaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CreatePersonaController {

	private final CreatePersonaService service;

	@PostMapping(path = "/register", consumes = "application/json", produces = "application/json")
	public ResponseEntity<CreatePersonaResponse> register(CreatePersonaRequest request){

		var response = service.execute(request);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
