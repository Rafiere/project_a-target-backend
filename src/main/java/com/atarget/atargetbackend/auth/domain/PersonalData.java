package com.atarget.atargetbackend.auth.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;
import org.springframework.data.jpa.domain.AbstractAuditable;

@Entity
@Getter
public class PersonalData extends AbstractAuditable<Persona, String> {

	private String nickname;
	@Embedded private Avatar avatar;

	public PersonalData(){}

	private PersonalData(String nickname) {
		this.nickname = nickname;
	}

	public static PersonalData of(String nickname) {
		return new PersonalData(nickname);
	}
}
