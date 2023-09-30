package com.atarget.atargetbackend.timer.domain;

import com.atarget.atargetbackend.shared.audit.BaseAuditableEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.Duration;
import java.util.UUID;

@Entity
public class TimeInterval extends BaseAuditableEntity {

	@Getter @Id private String id;
	@Getter private String name;
	@Getter private String description;
	@Getter private Duration duration;

	public TimeInterval(){}

	private TimeInterval(final Duration duration){
		this.id = UUID.randomUUID().toString();
		this.duration = duration;
	}

	public static TimeInterval of(final Duration duration){
		return new TimeInterval(duration);
	}

	public void updateTimeInterval(final String newName, final String newDescription) {
		if(newName != null && !newName.isBlank()){

			this.name = newName;
		}

		if(newDescription != null && !newDescription.isBlank()){

			this.description = newDescription;
		}
	}
}
