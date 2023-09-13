package com.atarget.atargetbackend.timer.controller.response;

import com.atarget.atargetbackend.timer.domain.TimeCounter;
import com.atarget.atargetbackend.timer.domain.enums.TimeCounterMethod;
import com.atarget.atargetbackend.timer.domain.enums.TimeCounterType;

import java.time.Duration;
import java.util.List;

public record GetTimeCounterResponse(String name, TimeCounterType timeCounterType, TimeCounterMethod timeCounterMethod, List<GetTimerIntervalResponse> timerIntervalsResponses, Duration sumOfTimeIntervals){


	public static GetTimeCounterResponse from(TimeCounter timeCounter){

		return new GetTimeCounterResponse(timeCounter.getName(),
		                                  timeCounter.getTimeCounterType(),
		                                  timeCounter.getTimeCounterMethod(),
		                                  timeCounter.getTimeIntervals()
		                                            .stream()
		                                            .map(GetTimerIntervalResponse::from)
		                                            .toList(),
		                                  timeCounter.sumAllTimeIntervals());
	}
}
