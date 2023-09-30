package com.atarget.atargetbackend.auth.service;

import com.atarget.atargetbackend.auth.controller.request.UseTokenForRecoveryPasswordRequest;
import com.atarget.atargetbackend.auth.domain.Token;
import com.atarget.atargetbackend.auth.domain.User;
import com.atarget.atargetbackend.auth.repository.TokenRepository;
import com.atarget.atargetbackend.persona.repository.UserRepository;
import com.atarget.atargetbackend.shared.exception.custom.BusinessRuleException;
import com.atarget.atargetbackend.shared.exception.custom.ResourceNotFoundException;
import com.atarget.atargetbackend.shared.exception.custom.enums.Resources;
import com.atarget.atargetbackend.shared.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UseTokenForRecoveryPasswordService {

	private final TokenRepository tokenRepository;
	private final UserRepository userRepository;

	public void execute(UseTokenForRecoveryPasswordRequest request, String tokenText){

		final Token foundToken = tokenRepository.findTokenByTokenText(tokenText)
		                                  .orElseThrow(() -> ResourceNotFoundException.of(Resources.TOKEN, tokenText));

		foundToken.validate();

		final String tokenOwnerUserEmail = foundToken.getEmail();

		final User user = userRepository.findUserByEmail(tokenOwnerUserEmail)
		                          .orElseThrow(() -> ResourceNotFoundException.of(Resources.EMAIL, tokenOwnerUserEmail));

		if(!user.isEnabled()){
			throw BusinessRuleException.of("This account is not activated yet.");
		}

		final String encryptedPassword = SecurityUtils.encryptAPasswordWithBCrypt(request.newPassword());

		user.changePassword(encryptedPassword);

		foundToken.useToken();
	}
}
