package com.atarget.atargetbackend.persona.controller.response;

import com.atarget.atargetbackend.auth.domain.UserRole;
import com.atarget.atargetbackend.persona.service.wrapper.CreatePersonaWrapper;

public record CreatePersonaResponse(String id, String nickname, UserRole userRole) {

	public static CreatePersonaResponse from(final CreatePersonaWrapper wrapper) {

		return new CreatePersonaResponse(wrapper.persona().getId(),
		                                 wrapper.persona().getPersonalData()
		                                             .getNickname(),
		                                 wrapper.persona().getUser()
		                                             .getUserRole());
	}
}
