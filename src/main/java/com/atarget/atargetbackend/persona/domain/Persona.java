package com.atarget.atargetbackend.persona.domain;

import com.atarget.atargetbackend.auth.domain.User;
import com.atarget.atargetbackend.shared.audit.BaseAuditableEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Getter
@Entity
public class Persona extends BaseAuditableEntity {

	@Id private String id;

	@OneToOne(cascade = CascadeType.ALL) private PersonalData personalData;

	@OneToOne(cascade = CascadeType.ALL) private User user;

	public Persona(){}

	private Persona(PersonalData personalData, User user){
		this.id = UUID.randomUUID().toString();
		this.personalData = personalData;
		this.user = user;
	}

	public static Persona of(User user, PersonalData personalData) {
		return new Persona(personalData, user);
	}

	public String getNickname() {
		return personalData.getNickname();
	}

}
