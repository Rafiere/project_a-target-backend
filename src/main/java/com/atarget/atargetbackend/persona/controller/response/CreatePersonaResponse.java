package com.atarget.atargetbackend.persona.controller.response;

import com.atarget.atargetbackend.auth.domain.UserRole;
import com.atarget.atargetbackend.persona.service.wrappers.CreatePersonaWrapper;

public record CreatePersonaResponse(String id, String nickname, UserRole userRole) {

	public static CreatePersonaResponse from(CreatePersonaWrapper wrapper) {

		return new CreatePersonaResponse(wrapper.persona().getId(),
		                                 wrapper.persona().getPersonalData()
		                                             .getNickname(),
		                                 wrapper.persona().getUser()
		                                             .getUserRole());
	}
}
