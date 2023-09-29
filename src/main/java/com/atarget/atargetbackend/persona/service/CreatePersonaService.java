package com.atarget.atargetbackend.persona.service;

import com.atarget.atargetbackend.auth.service.SendTokenForActivateAccountService;
import com.atarget.atargetbackend.persona.service.wrappers.CreatePersonaWrapper;
import com.atarget.atargetbackend.persona.controller.request.CreatePersonaRequest;
import com.atarget.atargetbackend.persona.domain.Persona;
import com.atarget.atargetbackend.persona.domain.PersonalData;
import com.atarget.atargetbackend.auth.domain.User;
import com.atarget.atargetbackend.auth.domain.UserRole;
import com.atarget.atargetbackend.auth.repository.PersonaRepository;
import com.atarget.atargetbackend.shared.utils.SecurityUtils;
import com.atarget.atargetbackend.shared.utils.validation.UserEntityCommonValidationsUtils;
import com.atarget.atargetbackend.shared.utils.validation.ShouldThrowAnException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CreatePersonaService {

	private final PersonaRepository personaRepository;
	private final UserEntityCommonValidationsUtils userEntityCommonValidationsUtils;
	private final SendTokenForActivateAccountService sendTokenForActivateAccountService;

	public CreatePersonaWrapper execute(CreatePersonaRequest request) {

		userEntityCommonValidationsUtils.verifyIfEmailIsAlreadyUsed(request.email(), ShouldThrowAnException.THROW);

		String encryptedPassword = SecurityUtils.encryptAPassword(request.password());

		var user = User.of(request.email(), encryptedPassword, UserRole.COMMON_USER);

		var personalData = PersonalData.of(PersonalData.generateFirstAccountNickname(request.email()));

		var createdPersona = Persona.of(user, personalData);

		Persona savedPersona = personaRepository.save(createdPersona);

		sendTokenForActivateAccountService.execute(request.email(), createdPersona.getPersonalData()
		                                                                          .getNickname());

		return CreatePersonaWrapper.of(savedPersona);
	}
}


