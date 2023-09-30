package com.atarget.atargetbackend.persona.controller;

import com.atarget.atargetbackend.persona.controller.request.CreatePersonaRequest;
import com.atarget.atargetbackend.persona.controller.response.CreatePersonaResponse;
import com.atarget.atargetbackend.persona.service.CreatePersonaService;
import com.atarget.atargetbackend.shared.routes.RoutesGroups;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@Validated
@RestController
@RequiredArgsConstructor
public class CreatePersonaController {

	private final CreatePersonaService service;

	@PostMapping(path = "/personas/register", consumes = MediaType.APPLICATION_JSON_VALUE,
	             produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CreatePersonaResponse> register(final @Valid @NotNull @RequestBody CreatePersonaRequest request) {

		final var serviceWrapper = service.execute(request);

		final var response = CreatePersonaResponse.from(serviceWrapper);

		return ResponseEntity.created(URI.create(RoutesGroups.PERSONA.getPath() +
		                                         response.id()))
		                     .body(response);
	}
}
