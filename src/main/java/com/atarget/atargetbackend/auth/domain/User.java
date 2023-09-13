package com.atarget.atargetbackend.auth.domain;

import jakarta.persistence.Entity;
import org.springframework.data.jpa.domain.AbstractAuditable;

@Entity
public class User extends AbstractAuditable<User, String> {

	private String name;


	public User(){

	}

	private User(String name) {
		this.name = name;
	}

	public static User of() {

		return new User();
	}
}
