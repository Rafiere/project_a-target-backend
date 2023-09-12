package com.atarget.atargetbackend.timer.domain;

import com.atarget.atargetbackend.auth.domain.User;
import com.atarget.atargetbackend.timer.domain.enums.CountTimeMethod;
import com.atarget.atargetbackend.timer.domain.enums.TimerCountType;
import lombok.Getter;
import org.springframework.data.jpa.domain.AbstractAuditable;

import java.time.Duration;
import java.util.List;

@Getter
public class TimerCount extends AbstractAuditable<User, String> {

	private String name;
	private final TimerCountType timerCountType;
	private final CountTimeMethod countTimeMethod;
	private List<TimeInterval> timeIntervals;

	private TimerCount(final String name,
	                   final TimerCountType timerCountType,
	                   final CountTimeMethod countTimeMethod) {

		this.name = name;
		this.timerCountType = timerCountType;
		this.countTimeMethod = countTimeMethod;
	}

	public static TimerCount of(String name, TimerCountType timerCountType, CountTimeMethod countTimeMethod) {

		return new TimerCount(name, timerCountType, countTimeMethod);
	}

	public TimerCount changeTimerCountName(String newTimerCountName){

		if(this.timerCountType.equals(TimerCountType.ASSIGNED)){
			throw new IllegalStateException("Cannot change name of assigned timer count");
		}

		this.name = newTimerCountName;

		return this;
	}

	public TimerCount addATimeInterval(final TimeInterval timeInterval) {

		this.timeIntervals.add(timeInterval);

		return this;
	}

	public Duration sumAllTimeIntervals() {

		return this.timeIntervals.stream()
		                         .map(TimeInterval::getDuration)
		                         .reduce(Duration.ZERO, Duration::plus);

	}
}
