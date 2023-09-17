package com.atarget.atargetbackend.timer.controller;

import com.atarget.atargetbackend.timer.controller.request.UpdateTimeCounterRequest;
import com.atarget.atargetbackend.timer.controller.response.GetTimeCounterResponse;
import com.atarget.atargetbackend.timer.service.UpdateTimeCounterByIdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UpdateTimeCounterByIdController {

	private final UpdateTimeCounterByIdService service;

	@PatchMapping(path = "/time-counters/{timeCounterId}")
	public ResponseEntity<GetTimeCounterResponse> execute(@RequestBody UpdateTimeCounterRequest request, @PathVariable String timeCounterId) {

		request.durationToProcess().validate();

		GetTimeCounterResponse result = service.execute(request, timeCounterId);

		return ResponseEntity.status(201)
		                     .body(result);
	}
}
