package com.atarget.atargetbackend.timer.controller;

import com.atarget.atargetbackend.timer.controller.response.GetTimeCounterResponse;
import com.atarget.atargetbackend.timer.service.GetAllTimeCountersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GetAllTimeCountersController {

	private final GetAllTimeCountersService service;

	@GetMapping(path = "/time-counters", produces = "application/json")
	public ResponseEntity<List<GetTimeCounterResponse>> execute(){

		List<GetTimeCounterResponse> result = service.execute();

		return ResponseEntity.status(200).body(result);
	}
}
