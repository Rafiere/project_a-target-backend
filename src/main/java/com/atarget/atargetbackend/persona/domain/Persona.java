package com.atarget.atargetbackend.persona.domain;

import com.atarget.atargetbackend.auth.domain.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import org.springframework.data.jpa.domain.AbstractAuditable;

@Entity
@Getter
public class Persona extends AbstractAuditable<Persona, String> {

	@OneToOne(cascade = CascadeType.ALL) private PersonalData personalData;

	@OneToOne private User user;

	public Persona(){}

	private Persona(PersonalData personalData, User user){
		this.personalData = personalData;
		this.user = user;
	}

	public static Persona of(User user, PersonalData personalData) {
		return new Persona(personalData, user);
	}
}
