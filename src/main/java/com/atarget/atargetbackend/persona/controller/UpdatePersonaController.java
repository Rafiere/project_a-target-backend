package com.atarget.atargetbackend.persona.controller;

import com.atarget.atargetbackend.persona.controller.request.UpdatePersonaRequest;
import com.atarget.atargetbackend.persona.controller.response.CreatePersonaResponse;
import com.atarget.atargetbackend.persona.service.UpdatePersonaService;
import com.atarget.atargetbackend.shared.routes.RoutesGroups;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@Validated
@RestController
@RequiredArgsConstructor
public class UpdatePersonaController {

	//TODO: Pesquisar como saber se a pessoa que está fazendo a requisição possui permissão para isso, ou seja, se ela está
	//tentando alterar o próprio nome, ou o nome de outra conta.

	private final UpdatePersonaService service;

	@PutMapping(path = "/persona/{personaId}", consumes = MediaType.APPLICATION_JSON_VALUE,
	            produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CreatePersonaResponse> register(@PathVariable @NotBlank final String personaId,
	                                                      @NotNull @Valid @RequestBody final UpdatePersonaRequest request) {

		final var wrapper = service.execute(personaId, request);

		final var response = CreatePersonaResponse.from(wrapper);

		return ResponseEntity.created(URI.create(RoutesGroups.PERSONA.getPath() +
		                                         response.id()))
		                     .body(response);
	}
}
