package com.atarget.atargetbackend.persona.controller;

import com.atarget.atargetbackend.persona.controller.request.UpdatePersonaRequest;
import com.atarget.atargetbackend.persona.controller.response.CreatePersonaResponse;
import com.atarget.atargetbackend.persona.service.UpdatePersonaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UpdatePersonaController {

	//TODO: Pesquisar como saber se a pessoa que está fazendo a requisição possui permissão para isso, ou seja, se ela está
	//tentando alterar o próprio nome, ou o nome de outra conta.

	private final UpdatePersonaService service;

	@PutMapping(path = "/persona", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CreatePersonaResponse> register(@Valid @RequestBody UpdatePersonaRequest request){

		var response = service.execute(request);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
