package com.atarget.atargetbackend.auth.domain;

public enum UserRole {

	ADMIN("ADMIN"),
	COMMON_USER("COMMON_USER");

	private final String role;

	UserRole(final String role) {

		this.role = role;
	}

	public String getRole() {

		return role;
	}
}
