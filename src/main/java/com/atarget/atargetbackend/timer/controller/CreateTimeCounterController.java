package com.atarget.atargetbackend.timer.controller;

import com.atarget.atargetbackend.timer.controller.request.CreateTimeCounterRequest;
import com.atarget.atargetbackend.timer.controller.response.CreateTimeCounterResponse;
import com.atarget.atargetbackend.timer.service.CreateTimerCountService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
public class CreateTimeCounterController {

	private final CreateTimerCountService service;

	@PostMapping(path = "/time-counters", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CreateTimeCounterResponse> execute(@NotNull @RequestBody final CreateTimeCounterRequest request){

		request.validate();

		final CreateTimeCounterResponse result = service.execute(request);

		return ResponseEntity.status(201).body(result);
	}
}
