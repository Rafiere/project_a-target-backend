package com.atarget.atargetbackend.timer.controller;

import com.atarget.atargetbackend.timer.controller.request.CreateTimeCounterRequest;
import com.atarget.atargetbackend.timer.controller.response.CreateTimeCounterResponse;
import com.atarget.atargetbackend.timer.service.CreateTimerCountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CreateTimeCounterController {

	private final CreateTimerCountService service;

	@PostMapping(path = "/time-counters", consumes = "application/json", produces = "application/json")
	public ResponseEntity<CreateTimeCounterResponse> execute(@RequestBody CreateTimeCounterRequest request){

		CreateTimeCounterResponse result = service.execute(request);

		return ResponseEntity.status(201).body(result);
	}
}
