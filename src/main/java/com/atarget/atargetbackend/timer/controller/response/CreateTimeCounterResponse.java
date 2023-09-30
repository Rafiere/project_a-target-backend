package com.atarget.atargetbackend.timer.controller.response;

import com.atarget.atargetbackend.timer.domain.TimeCounter;

import java.time.LocalDateTime;

public record CreateTimeCounterResponse(String id, String name, String timerCountType, String countTimeMethod, LocalDateTime createdDateTime) {

	public static CreateTimeCounterResponse from(TimeCounter createdTimeCounter) {

		return new CreateTimeCounterResponse(
				createdTimeCounter.getId(),
				createdTimeCounter.getName(),
				createdTimeCounter.getTimeCounterType().name(),
				createdTimeCounter.getTimeCounterMethod().name(),
				createdTimeCounter.getCreatedDate());
	}
}
