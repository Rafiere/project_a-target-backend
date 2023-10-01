package com.atarget.atargetbackend.persona.service;

import com.atarget.atargetbackend.persona.controller.request.UpdatePersonaRequest;
import com.atarget.atargetbackend.persona.controller.response.CreatePersonaResponse;
import com.atarget.atargetbackend.auth.repository.PersonaRepository;
import com.atarget.atargetbackend.persona.service.wrapper.CreatePersonaWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UpdatePersonaService {

	private final PersonaRepository personaRepository;

	public CreatePersonaWrapper execute(final String personaId, final UpdatePersonaRequest request) {

//		if (personaRepository.findByEmail(request.email())
//		                     .isPresent()) {
//			throw new IllegalArgumentException("The email " +
//			                                   request.email() +
//			                                   " is already in use.");
//		}
//
//		String encryptedPassword = new BCryptPasswordEncoder().encode(request.password());
//
//		User user = User.of(request.email(), encryptedPassword, UserRole.COMMON_USER);
//
//		PersonalData personalData = new PersonalData();
//
//		Persona createdPersona = Persona.of(user, personalData);
//
//		Persona savedPersona = personaRepository.save(createdPersona);
//
//		return CreatePersonaResponse.from(savedPersona);

		return null;
	}
}
