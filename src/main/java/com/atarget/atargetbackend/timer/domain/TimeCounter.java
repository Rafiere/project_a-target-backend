package com.atarget.atargetbackend.timer.domain;

import com.atarget.atargetbackend.persona.domain.Persona;
import com.atarget.atargetbackend.timer.domain.enums.TimeCounterMethod;
import com.atarget.atargetbackend.timer.domain.enums.TimeCounterDurationOperation;
import com.atarget.atargetbackend.timer.domain.enums.TimeCounterType;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.jpa.domain.AbstractAuditable;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

@Getter
@Entity
public class TimeCounter extends AbstractAuditable<Persona, String> {

	private String name;
	private String description;
	@Enumerated(EnumType.STRING) private TimeCounterType timeCounterType;
	@Enumerated(EnumType.STRING) private TimeCounterMethod timeCounterMethod;
	@OneToMany(cascade = CascadeType.ALL) private List<TimeInterval> timeIntervals;

	public TimeCounter(){}

	private TimeCounter(final String name,
	                    final String description,
	                    final TimeCounterType timeCounterType,
	                    final TimeCounterMethod timeCounterMethod) {

		this.setId(UUID.randomUUID().toString());
		this.name = name;
		this.description = description;
		this.timeCounterType = timeCounterType;
		this.timeCounterMethod = timeCounterMethod;
	}

	public static TimeCounter of(String name, String description, TimeCounterType timeCounterType, TimeCounterMethod timeCounterMethod) {

		return new TimeCounter(name, description, timeCounterType, timeCounterMethod);
	}

	public void update(String name, String description, Duration durationToProcess, TimeCounterDurationOperation operation){
		if(name != null && !name.isBlank()){
			changeTimerCountName(name);
		}

		if(description != null && !description.isBlank()){
			changeTimerCountDescription(description);
		}

		if(durationToProcess != null && operation != null){
			TimeInterval createdTimeInterval = TimeInterval.of(durationToProcess);

			this.processATimeInterval(createdTimeInterval, operation);
		}
	}

	private void changeTimerCountName(String newTimerCountName){

		if(verifyIfTheTimeCounterIsAssigned()){
			throw new IllegalStateException("Cannot change name of assigned timer count");
		}

		this.name = newTimerCountName;
	}

	private void changeTimerCountDescription(String newTimerCountDescription){

		if(verifyIfTheTimeCounterIsAssigned()){
			throw new IllegalStateException("Cannot change description of assigned timer count");
		}

		this.description = newTimerCountDescription;
	}

	private boolean verifyIfTheTimeCounterIsAssigned() {

		return this.timeCounterType.equals(TimeCounterType.ASSIGNED);
	}

	public void processATimeInterval(final TimeInterval timeInterval, TimeCounterDurationOperation operation) {

		switch (operation) {
			case ADD -> this.timeIntervals.add(timeInterval);
			case SUBTRACT -> this.timeIntervals.remove(timeInterval);
		}
	}

	public Duration sumAllTimeIntervals() {

		return this.timeIntervals.stream()
		                         .map(TimeInterval::getDuration)
		                         .reduce(Duration.ZERO, Duration::plus);

	}
}
