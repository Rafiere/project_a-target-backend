package com.atarget.atargetbackend.timer.controller.response;

import com.atarget.atargetbackend.timer.domain.TimerCount;

import java.time.LocalDateTime;

public record CreateTimerCountResponse(String id, String name, String timerCountType, String countTimeMethod, LocalDateTime createdDateTime) {

	public static CreateTimerCountResponse of(TimerCount createdTimerCount) {

		return new CreateTimerCountResponse(
				createdTimerCount.getId(),
				createdTimerCount.getName(),
				createdTimerCount.getTimerCountType().name(),
				createdTimerCount.getCountTimeMethod().name(),
				createdTimerCount.getCreatedDate().orElse(null));
	}
}
