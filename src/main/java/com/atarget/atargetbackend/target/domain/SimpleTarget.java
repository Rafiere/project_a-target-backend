package com.atarget.atargetbackend.target.domain;

import com.atarget.atargetbackend.auth.domain.User;
import com.atarget.atargetbackend.target.domain.enums.MetaType;
import com.atarget.atargetbackend.target.domain.interfaces.Targetable;
import jakarta.persistence.Entity;
import lombok.Getter;
import org.springframework.data.jpa.domain.AbstractAuditable;

import java.util.UUID;

@Getter
@Entity
public class SimpleTarget extends AbstractAuditable<User, String> implements Targetable {

	private String name;
	private String description;
	private final MetaType metaType = MetaType.SIMPLE;

	public SimpleTarget(){}

	private SimpleTarget(String name, String description){
		this.setId(UUID.randomUUID().toString());
		this.name = name;
		this.description = description;
	}

	public static SimpleTarget of(String name, String description){
		return new SimpleTarget(name, description);
	}
}
