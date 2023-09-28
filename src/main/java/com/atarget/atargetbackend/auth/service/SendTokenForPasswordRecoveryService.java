package com.atarget.atargetbackend.auth.service;

import com.atarget.atargetbackend.auth.domain.Token;
import com.atarget.atargetbackend.auth.domain.enums.TokenType;
import com.atarget.atargetbackend.auth.repository.TokenRepository;
import com.atarget.atargetbackend.shared.email.SendEmailWithAngusMailComponent;
import com.atarget.atargetbackend.shared.utils.validation.UserEntityCommonValidationsUtils;
import com.atarget.atargetbackend.shared.utils.validation.ShouldThrowAnException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SendTokenForPasswordRecoveryService {

	private final TokenRepository tokenRepository;
	private final UserEntityCommonValidationsUtils userEntityCommonValidationsUtils;
	private final SendEmailWithAngusMailComponent sendEmailWithJMSComponent;
	private static final Integer PASSWORD_RECOVERY_TOKEN_EXPIRATION_TIME_IN_MINUTES = 30;
	private static final Integer PASSWORD_RECOVERY_TOKEN_LENGTH = 5;

	public void execute(String email) {

		userEntityCommonValidationsUtils.verifyIfEmailIsAlreadyUsed(email, ShouldThrowAnException.THROW);

		Token generatedTokenText = Token.of(email,
		                                    PASSWORD_RECOVERY_TOKEN_EXPIRATION_TIME_IN_MINUTES,
		                                    TokenType.PASSWORD_RECOVERY,
		                                    PASSWORD_RECOVERY_TOKEN_LENGTH,
		                                    '@',
		                                    email);

		Token savedToken = tokenRepository.save(generatedTokenText);

//		sendEmailWithJMSComponent.execute("");

		savedToken.markAsSent();
	}
}
