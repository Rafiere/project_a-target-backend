package com.atarget.atargetbackend.timer.domain;

import com.atarget.atargetbackend.shared.audit.BaseAuditableEntity;
import com.atarget.atargetbackend.timer.domain.enums.TimeCounterMethod;
import com.atarget.atargetbackend.timer.domain.enums.TimeCounterDurationOperation;
import com.atarget.atargetbackend.timer.domain.enums.TimeCounterType;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

@Entity
public class TimeCounter extends BaseAuditableEntity {

	@Getter @Id private String id;
	@Getter private String name;
	@Getter private String description;
	@Getter @Enumerated(EnumType.STRING) private TimeCounterType timeCounterType;
	@Getter @Enumerated(EnumType.STRING) private TimeCounterMethod timeCounterMethod;
	@Getter @OneToMany(cascade = CascadeType.ALL) private List<TimeInterval> timeIntervals;

	public TimeCounter(){}

	private TimeCounter(final String name,
	                    final String description,
	                    final TimeCounterType timeCounterType,
	                    final TimeCounterMethod timeCounterMethod) {

		this.id = UUID.randomUUID().toString();
		this.name = name;
		this.description = description;
		this.timeCounterType = timeCounterType;
		this.timeCounterMethod = timeCounterMethod;
	}

	public static TimeCounter of(final String name, final String description, final TimeCounterType timeCounterType, final TimeCounterMethod timeCounterMethod) {

		return new TimeCounter(name, description, timeCounterType, timeCounterMethod);
	}

	public void update(final String name, final String description, final Duration durationToProcess, final TimeCounterDurationOperation operation){
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

	private void changeTimerCountName(final String newTimerCountName){

		if(verifyIfTheTimeCounterIsAssigned()){
			throw new IllegalStateException("Cannot change name of assigned timer count");
		}

		this.name = newTimerCountName;
	}

	private void changeTimerCountDescription(final String newTimerCountDescription){

		if(verifyIfTheTimeCounterIsAssigned()){
			throw new IllegalStateException("Cannot change description of assigned timer count");
		}

		this.description = newTimerCountDescription;
	}

	private boolean verifyIfTheTimeCounterIsAssigned() {

		return this.timeCounterType.equals(TimeCounterType.ASSIGNED);
	}

	public void processATimeInterval(final TimeInterval timeInterval, final TimeCounterDurationOperation operation) {

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
