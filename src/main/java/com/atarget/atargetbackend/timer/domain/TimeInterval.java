package com.atarget.atargetbackend.timer.domain;

import com.atarget.atargetbackend.auth.domain.Persona;
import jakarta.persistence.Entity;
import lombok.Getter;
import org.springframework.data.jpa.domain.AbstractAuditable;

import java.time.Duration;
import java.util.UUID;

@Getter
@Entity
public class TimeInterval extends AbstractAuditable<Persona, String> {

	private String name;
	private String description;
	private Duration duration;

	public TimeInterval(){}

	private TimeInterval(final Duration duration){
		this.setId(UUID.randomUUID().toString());
		this.duration = duration;
	}

	public static TimeInterval of(final Duration duration){
		return new TimeInterval(duration);
	}

	public void updateTimeInterval(String newName, String newDescription) {
		if(newName != null && !newName.isBlank()){

			this.name = newName;
		}

		if(newDescription != null && !newDescription.isBlank()){

			this.description = newDescription;
		}
	}
}
