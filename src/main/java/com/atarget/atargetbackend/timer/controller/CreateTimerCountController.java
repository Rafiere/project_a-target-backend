package com.atarget.atargetbackend.timer.controller;

import com.atarget.atargetbackend.timer.controller.request.CreateTimerCountRequest;
import com.atarget.atargetbackend.timer.controller.response.CreateTimerCountResponse;
import com.atarget.atargetbackend.timer.service.CreateTimerCountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CreateTimerCountController {

	private final CreateTimerCountService service;

	@PostMapping(path = "/timer-count")
	public ResponseEntity<CreateTimerCountResponse> execute(@RequestBody CreateTimerCountRequest request){

		CreateTimerCountResponse result = service.execute(request);

		return ResponseEntity.status(201).body(result);
	}
}
