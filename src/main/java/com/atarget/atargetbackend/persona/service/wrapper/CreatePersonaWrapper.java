package com.atarget.atargetbackend.persona.service.wrapper;

import com.atarget.atargetbackend.persona.domain.Persona;

public record CreatePersonaWrapper(Persona persona) {

	public static CreatePersonaWrapper of(final Persona persona){

		return new CreatePersonaWrapper(persona);
	}
}
