package com.atarget.atargetbackend.timer.controller.request;

import com.atarget.atargetbackend.timer.domain.enums.TimeCounterDurationOperation;

import java.time.Duration;

public record UpdateTimeCounterDurationRequest(Duration duration, TimeCounterDurationOperation operation) {

	public void validate() {
		if (duration != null && operation == null) {
			throw new IllegalArgumentException("Send a operation to be performed.");
		}

		if (operation != null && duration == null) {
			throw new IllegalArgumentException("Send a duration to be processed.");
		}
	}
}
