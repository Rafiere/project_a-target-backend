package com.atarget.atargetbackend.auth.controller;

import com.atarget.atargetbackend.auth.controller.response.VerifyIfTheEmailIsUsedResponse;
import com.atarget.atargetbackend.auth.service.VerifyIfTheEmailIsUsedService;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
public class VerifyIfTheEmailIsUsedController {

	private final VerifyIfTheEmailIsUsedService service;

	@GetMapping(value = "/auth/email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<VerifyIfTheEmailIsUsedResponse> execute(@Email @NotBlank @PathVariable String email){

		var serviceWrapper = service.execute(email);

		var response = VerifyIfTheEmailIsUsedResponse.from(serviceWrapper);

		return ResponseEntity.status(200).body(response);
	}
}
