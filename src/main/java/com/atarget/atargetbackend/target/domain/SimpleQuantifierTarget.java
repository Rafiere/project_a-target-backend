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

import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class SimpleQuantifierTarget extends BaseAuditableEntity implements Targetable {

	@Getter @Id private String id;
	@Getter private String name;
	@Getter private String description;
	@Getter private BigDecimal quantity;
	@Getter @Enumerated private final MetaType metaType = MetaType.SIMPLE_WITH_QUANTIFIER;

	public SimpleQuantifierTarget(){}

	private SimpleQuantifierTarget(final String name, final String description, final BigDecimal quantity){
		this.id = UUID.randomUUID().toString();
		this.name = name;
		this.description = description;
		this.quantity = quantity;
	}

	public static SimpleQuantifierTarget of(final String name, final String description, final BigDecimal quantity){
		return new SimpleQuantifierTarget(name, description, quantity);
	}
}
