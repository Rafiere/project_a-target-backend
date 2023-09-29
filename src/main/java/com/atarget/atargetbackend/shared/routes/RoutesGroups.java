package com.atarget.atargetbackend.shared.routes;

public enum RoutesGroups {

	PERSONA("/personas/"),
	TARGET("/targets/"),
	AUTH("/auth/");

	private final String path;

	RoutesGroups(String path){
		this.path = path;
	}

	public String getPath() {
		return path;
	}
}
