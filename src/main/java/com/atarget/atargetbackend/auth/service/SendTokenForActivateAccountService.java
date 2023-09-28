package com.atarget.atargetbackend.auth.service;

import com.atarget.atargetbackend.auth.domain.Token;
import com.atarget.atargetbackend.auth.domain.enums.TokenType;
import com.atarget.atargetbackend.auth.repository.TokenRepository;
import com.atarget.atargetbackend.shared.email.GenerateEmailComponent;
import com.atarget.atargetbackend.shared.email.SendEmailWithAngusMailComponent;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SendTokenForActivateAccountService {

	private final TokenRepository tokenRepository;
	private final SendEmailWithAngusMailComponent sendEmailWithJMSComponent;
	private final GenerateEmailComponent generateEmailComponent;

	@Value("${environment.api.security.tokens.activate-account-token.expiration-in-seconds}") private Integer
			activateAccountTokenExpirationTimeInSeconds;
	@Value("${environment.api.security.tokens.activate-account-token.length}") private Integer
			activateAccountTokenLength;
	@Value("${environment.api.security.tokens.activate-account-token.char-to-concatenate-with-token}") private Character
			charToConcatenateWithToken;

	public void execute(@Email String email, String personaId) {

		Token generatedToken = Token.of(email,
		                                activateAccountTokenExpirationTimeInSeconds,
		                                TokenType.ACTIVATE_ACCOUNT,
		                                activateAccountTokenLength,
		                                charToConcatenateWithToken,
		                                personaId);

		tokenRepository.save(generatedToken);

		List<String> emailsToSendTo = List.of(email);

		sendEmailWithJMSComponent.execute("Activate your account!",
		                                  generateEmailComponent.generateActivateAccountEmailTemplate(generatedToken),
		                                  emailsToSendTo);

		generatedToken.markAsSent();
	}
}
