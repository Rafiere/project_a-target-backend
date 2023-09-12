package com.atarget.atargetbackend.timer.domain;

import com.atarget.atargetbackend.auth.domain.User;
import lombok.Getter;
import org.springframework.data.jpa.domain.AbstractAuditable;

import java.time.Duration;

@Getter
public class TimeInterval extends AbstractAuditable<User, String> {

	private String name;
	private final Duration duration;

	private TimeInterval(final String name, final Duration duration){
		this.name = name;
		this.duration = duration;
	}

	public static TimeInterval of(final String name, final Duration duration){
		return new TimeInterval(name, duration);
	}

	public TimeInterval changeTimeIntervalName(final String newName){

		if(newName.isBlank()){
			throw new IllegalArgumentException("Name cannot be blank");
		}
		this.name = newName;

		return this;
	}
}
