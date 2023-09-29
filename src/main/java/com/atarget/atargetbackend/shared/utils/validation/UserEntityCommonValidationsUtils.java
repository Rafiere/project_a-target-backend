package com.atarget.atargetbackend.shared.utils.validation;

import com.atarget.atargetbackend.auth.repository.PersonaRepository;
import com.atarget.atargetbackend.shared.exception.custom.BusinessRuleException;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
@RequiredArgsConstructor
public class UserEntityCommonValidationsUtils {

	private final PersonaRepository personaRepository;

	public boolean verifyIfEmailIsAlreadyUsed(@Email(message = "The email format is incorrect.") String email,
	                                          ShouldThrowAnException throwAnExceptionWhenValidationFails) {

		boolean personaWithEmailExists = personaRepository.findByEmail(email)
		                                                   .isPresent();

		if (personaWithEmailExists &&
		    throwAnExceptionWhenValidationFails.equals(ShouldThrowAnException.THROW)) {
			throw BusinessRuleException.of("The email '" + email + "' is already used.");
		}

		return personaWithEmailExists;
	}
}
