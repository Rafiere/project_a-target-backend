package com.atarget.atargetbackend.timer.controller;

import com.atarget.atargetbackend.timer.controller.request.UpdateTimeIntervalRequest;
import com.atarget.atargetbackend.timer.controller.response.GetTimerIntervalResponse;
import com.atarget.atargetbackend.timer.service.UpdateTimeIntervalByIdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UpdateTimeIntervalByIdController {

	private final UpdateTimeIntervalByIdService service;

	@PatchMapping(path = "/time-counters/interval/{timeIntervalId}")
	public ResponseEntity<GetTimerIntervalResponse> execute(@RequestBody UpdateTimeIntervalRequest request,
	                                                        @PathVariable String timeIntervalId) {

		GetTimerIntervalResponse result = service.execute(request, timeIntervalId);

		return ResponseEntity.status(201)
		                     .body(result);
	}
}
