package com.atarget.atargetbackend.timer.controller.response;

import com.atarget.atargetbackend.timer.domain.TimeInterval;

public record GetTimerIntervalResponse(String name, String duration) {

	public static GetTimerIntervalResponse from(TimeInterval timeInterval) {

		return new GetTimerIntervalResponse(timeInterval.getName(),
		                                    timeInterval.getDuration()
		                                                .toString());
	}
}
