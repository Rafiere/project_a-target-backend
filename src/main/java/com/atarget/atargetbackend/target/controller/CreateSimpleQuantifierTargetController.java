package com.atarget.atargetbackend.target.controller;

import com.atarget.atargetbackend.target.controller.request.CreateSimpleQuantifierTargetRequest;
import com.atarget.atargetbackend.target.controller.response.CreateSimpleQuantifierTargetResponse;
import com.atarget.atargetbackend.target.service.CreateSimpleQuantifierTargetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CreateSimpleQuantifierTargetController {

	private final CreateSimpleQuantifierTargetService service;

	@PostMapping(path = "/targets/simple-quantifier", consumes = "application/json", produces = "application/json")
	public ResponseEntity<CreateSimpleQuantifierTargetResponse> execute(@RequestBody CreateSimpleQuantifierTargetRequest request){

		var response = service.execute(request);

		return ResponseEntity.status(201).body(response);
	}
}
