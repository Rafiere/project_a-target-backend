package com.atarget.atargetbackend.timer.controller;

import com.atarget.atargetbackend.timer.controller.response.GetTimeCounterResponse;
import com.atarget.atargetbackend.timer.service.GetTimeCounterByIdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GetTimeCounterByIdController {

	private final GetTimeCounterByIdService service;

	@GetMapping(path = "/time-counters/{timeCounterId}")
	public ResponseEntity<GetTimeCounterResponse> execute(@PathVariable String timeCounterId){

		GetTimeCounterResponse result = service.execute(timeCounterId);

		return ResponseEntity.status(200).body(result);
	}
}
