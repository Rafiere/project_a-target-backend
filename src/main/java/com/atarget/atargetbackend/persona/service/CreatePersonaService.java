package com.atarget.atargetbackend.persona.service;

import com.atarget.atargetbackend.persona.controller.request.CreatePersonaRequest;
import com.atarget.atargetbackend.persona.controller.response.CreatePersonaResponse;
import com.atarget.atargetbackend.persona.domain.Persona;
import com.atarget.atargetbackend.persona.domain.PersonalData;
import com.atarget.atargetbackend.auth.domain.User;
import com.atarget.atargetbackend.auth.domain.UserRole;
import com.atarget.atargetbackend.auth.repository.PersonaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CreatePersonaService {

	private final PersonaRepository personaRepository;

	public CreatePersonaResponse execute(CreatePersonaRequest request) {

		if (personaRepository.findByEmail(request.email())
		                     .isPresent()) {
			throw new IllegalArgumentException("The email " +
			                                   request.email() +
			                                   " is already in use.");
		}

		String encryptedPassword = new BCryptPasswordEncoder().encode(request.password());

		User user = User.of(request.email(), encryptedPassword, UserRole.COMMON_USER);

		PersonalData personalData = new PersonalData();

		Persona createdPersona = Persona.of(user, personalData);

		Persona savedPersona = personaRepository.save(createdPersona);

		return CreatePersonaResponse.from(savedPersona);
	}
}


