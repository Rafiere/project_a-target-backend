package com.atarget.atargetbackend.auth.service;

import com.atarget.atargetbackend.auth.domain.Token;
import com.atarget.atargetbackend.auth.domain.enums.TokenType;
import com.atarget.atargetbackend.auth.repository.PersonaRepository;
import com.atarget.atargetbackend.auth.repository.TokenRepository;
import com.atarget.atargetbackend.persona.domain.Persona;
import com.atarget.atargetbackend.shared.email.GenerateEmailComponent;
import com.atarget.atargetbackend.shared.email.SendEmailWithAngusMailComponent;
import com.atarget.atargetbackend.shared.exception.custom.ResourceNotFoundException;
import com.atarget.atargetbackend.shared.exception.custom.enums.Resources;
import com.atarget.atargetbackend.shared.utils.validation.UserEntityCommonValidationsUtils;
import com.atarget.atargetbackend.shared.utils.validation.ShouldThrowAnException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SendTokenForPasswordRecoveryService {

	@Value("${environment.api.security.tokens.recovery-password-token.expiration-in-seconds}") private Integer
			recoveryPasswordTokenExpirationTimeInSeconds;
	@Value("${environment.api.security.tokens.recovery-password-token.length}") private Integer
			recoveryPasswordTokenLength;

	private final TokenRepository tokenRepository;
	private final PersonaRepository personaRepository;
	private final UserEntityCommonValidationsUtils userEntityCommonValidationsUtils;
	private final SendEmailWithAngusMailComponent sendEmailWithJMSComponent;
	private final GenerateEmailComponent generateEmailComponent;

	public void execute(final String email) {

		userEntityCommonValidationsUtils.verifyIfEmailIsNotUsedYet(email, ShouldThrowAnException.THROW);

		final Token generatedTokenText = Token.of(email,
		                                    recoveryPasswordTokenExpirationTimeInSeconds,
		                                    TokenType.PASSWORD_RECOVERY,
		                                    recoveryPasswordTokenLength,
		                                    null,
		                                    null);

		final Token savedToken = tokenRepository.save(generatedTokenText);

		final Persona persona = personaRepository.findPersonaByEmail(email)
		                                   .orElseThrow(() -> ResourceNotFoundException.of(Resources.EMAIL, savedToken.getEmail()));

		final String emailTemplate = generateEmailComponent.generateRecoveryPasswordEmailTemplate(savedToken, persona.getNickname());

		final List<String> emailsToSendTo = List.of(savedToken.getEmail());

		sendEmailWithJMSComponent.execute("Recovery your password!", emailTemplate, emailsToSendTo);

		savedToken.markAsSent();
	}
}
