package com.atarget.atargetbackend.target.domain;

import com.atarget.atargetbackend.auth.domain.User;
import com.atarget.atargetbackend.target.domain.enums.MetaType;
import com.atarget.atargetbackend.target.domain.interfaces.Targetable;
import jakarta.persistence.Entity;
import lombok.Getter;
import org.springframework.data.jpa.domain.AbstractAuditable;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Entity
public class SimpleQuantifierTarget extends AbstractAuditable<User, String> implements Targetable {

	private String name;
	private String description;
	private BigDecimal quantity;
	private final MetaType metaType = MetaType.SIMPLE_WITH_QUANTIFIER;

	public SimpleQuantifierTarget(){}

	private SimpleQuantifierTarget(String name, String description, BigDecimal quantity){
		this.setId(UUID.randomUUID().toString());
		this.name = name;
		this.description = description;
		this.quantity = quantity;
	}

	public static SimpleQuantifierTarget of(String name, String description, BigDecimal quantity){
		return new SimpleQuantifierTarget(name, description, quantity);
	}
}
