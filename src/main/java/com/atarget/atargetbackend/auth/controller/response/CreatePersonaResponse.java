package com.atarget.atargetbackend.auth.controller.response;

import com.atarget.atargetbackend.auth.domain.Persona;
import com.atarget.atargetbackend.auth.domain.UserRole;

public record CreatePersonaResponse(String id, String nickname, UserRole userRole) {

	public static CreatePersonaResponse from(Persona savedPersona) {

		return new CreatePersonaResponse(savedPersona.getId(),
		                                 savedPersona.getPersonalData()
		                                             .getNickname(),
		                                 savedPersona.getUser()
		                                             .getUserRole());
	}
}
