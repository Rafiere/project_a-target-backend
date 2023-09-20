package com.atarget.atargetbackend.target.domain;

import com.atarget.atargetbackend.persona.domain.Persona;
import com.atarget.atargetbackend.target.domain.enums.MetaType;
import com.atarget.atargetbackend.target.domain.interfaces.Targetable;
import com.atarget.atargetbackend.timer.domain.TimeCounter;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import org.springframework.data.jpa.domain.AbstractAuditable;

import java.util.UUID;

@Getter
@Entity
public class SimpleTimeCounterTarget extends AbstractAuditable<Persona, String> implements Targetable {

	private String name;
	private String description;
	@OneToOne(cascade = CascadeType.ALL) private TimeCounter timeCounter;
	private final MetaType metaType = MetaType.SIMPLE_WITH_TIME_COUNTER;

	public SimpleTimeCounterTarget(){}

	private SimpleTimeCounterTarget(String name, String description, TimeCounter timeCounter){
		this.setId(UUID.randomUUID().toString());
		this.name = name;
		this.description = description;
		this.timeCounter = timeCounter;
	}

	public static SimpleTimeCounterTarget of(String name, String description, TimeCounter timeCounter){
		return new SimpleTimeCounterTarget(name, description, timeCounter);
	}
}
