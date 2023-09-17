package com.atarget.atargetbackend.timer.controller;

import com.atarget.atargetbackend.timer.service.DeleteTimeCounterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class DeleteTimeCounterController {

	private final DeleteTimeCounterService service;

	@DeleteMapping(path = "/time-counters/{timeCounterId}", produces = "application/json")
	public ResponseEntity<Void> execute(@PathVariable String timeCounterId){

		 service.execute(timeCounterId);

		return ResponseEntity.status(204).build();
	}
}
