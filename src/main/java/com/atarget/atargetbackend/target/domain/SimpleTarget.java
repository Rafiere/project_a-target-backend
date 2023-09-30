package com.atarget.atargetbackend.target.domain;

import com.atarget.atargetbackend.persona.domain.Persona;
import com.atarget.atargetbackend.shared.audit.BaseAuditableEntity;
import com.atarget.atargetbackend.target.domain.enums.MetaType;
import com.atarget.atargetbackend.target.domain.interfaces.Targetable;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Getter;
import org.springframework.data.jpa.domain.AbstractAuditable;

import java.util.UUID;

@Entity
public class SimpleTarget extends BaseAuditableEntity implements Targetable {

	@Getter @Id private String id;
	@Getter private String name;
	@Getter private String description;
	@Getter @Enumerated private final MetaType metaType = MetaType.SIMPLE;

	public SimpleTarget(){}

	private SimpleTarget(final String name, final String description){
		this.id = UUID.randomUUID().toString();
		this.name = name;
		this.description = description;
	}

	public static SimpleTarget of(final String name, final String description){
		return new SimpleTarget(name, description);
	}
}
