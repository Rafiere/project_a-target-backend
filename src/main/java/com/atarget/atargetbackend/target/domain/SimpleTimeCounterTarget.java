package com.atarget.atargetbackend.target.domain;

import com.atarget.atargetbackend.persona.domain.Persona;
import com.atarget.atargetbackend.shared.audit.BaseAuditableEntity;
import com.atarget.atargetbackend.target.domain.enums.MetaType;
import com.atarget.atargetbackend.target.domain.interfaces.Targetable;
import com.atarget.atargetbackend.timer.domain.TimeCounter;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import org.springframework.data.jpa.domain.AbstractAuditable;

import java.util.UUID;

@Entity
public class SimpleTimeCounterTarget extends BaseAuditableEntity implements Targetable {

	@Getter @Id private String id;
	@Getter private String name;
	@Getter private String description;
	@Getter @OneToOne(cascade = CascadeType.ALL) private TimeCounter timeCounter;
	@Getter private final MetaType metaType = MetaType.SIMPLE_WITH_TIME_COUNTER;

	public SimpleTimeCounterTarget(){}

	private SimpleTimeCounterTarget(String name, String description, TimeCounter timeCounter){
		this.id = UUID.randomUUID().toString();
		this.name = name;
		this.description = description;
		this.timeCounter = timeCounter;
	}

	public static SimpleTimeCounterTarget of(String name, String description, TimeCounter timeCounter){
		return new SimpleTimeCounterTarget(name, description, timeCounter);
	}
}
