package com.atarget.atargetbackend.target.controller;

import com.atarget.atargetbackend.target.controller.request.CreateSimpleTimeCounterTargetRequest;
import com.atarget.atargetbackend.target.controller.response.CreateSimpleTimeCounterTargetResponse;
import com.atarget.atargetbackend.target.service.CreateSimpleTimeCounterTargetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CreateSimpleTimeCounterTargetController {

	private final CreateSimpleTimeCounterTargetService service;

	@PostMapping(path = "/targets/simple-time-counter", consumes = "application/json", produces = "application/json")
	public ResponseEntity<CreateSimpleTimeCounterTargetResponse> execute(@RequestBody CreateSimpleTimeCounterTargetRequest request){

		var response = service.execute(request);

		return ResponseEntity.status(201).body(response);
	}
}
