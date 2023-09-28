package com.atarget.atargetbackend.persona.controller;

import com.atarget.atargetbackend.persona.controller.request.UpdatePersonaRequest;
import com.atarget.atargetbackend.persona.controller.response.CreatePersonaResponse;
import com.atarget.atargetbackend.persona.service.UpdatePersonaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UpdatePersonaController {

	private final UpdatePersonaService service;

	@PostMapping(path = "/persona", consumes = "application/json", produces = "application/json")
	public ResponseEntity<CreatePersonaResponse> register(UpdatePersonaRequest request){

		var response = service.execute(request);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
