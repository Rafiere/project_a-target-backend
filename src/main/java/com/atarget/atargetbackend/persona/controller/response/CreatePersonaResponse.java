package com.atarget.atargetbackend.persona.controller.response;

import com.atarget.atargetbackend.persona.domain.Persona;
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
