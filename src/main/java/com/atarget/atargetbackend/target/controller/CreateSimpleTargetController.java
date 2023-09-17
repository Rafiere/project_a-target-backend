package com.atarget.atargetbackend.target.controller;

import com.atarget.atargetbackend.target.controller.request.CreateSimpleTargetRequest;
import com.atarget.atargetbackend.target.controller.response.CreateSimpleTargetResponse;
import com.atarget.atargetbackend.target.service.CreateSimpleTargetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CreateSimpleTargetController {

	private final CreateSimpleTargetService service;

	@PostMapping(path = "/targets/simple", consumes = "application/json", produces = "application/json")
	public ResponseEntity<CreateSimpleTargetResponse> execute(@RequestBody CreateSimpleTargetRequest request){

		var response = service.execute(request);

		return ResponseEntity.status(201).body(response);
	}
}
