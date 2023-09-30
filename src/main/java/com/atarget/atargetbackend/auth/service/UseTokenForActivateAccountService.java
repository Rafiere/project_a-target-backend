package com.atarget.atargetbackend.auth.service;

import com.atarget.atargetbackend.auth.domain.Token;
import com.atarget.atargetbackend.auth.domain.User;
import com.atarget.atargetbackend.auth.repository.TokenRepository;
import com.atarget.atargetbackend.persona.repository.UserRepository;
import com.atarget.atargetbackend.shared.exception.custom.ResourceNotFoundException;
import com.atarget.atargetbackend.shared.exception.custom.enums.Resources;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UseTokenForActivateAccountService {

	private final TokenRepository tokenRepository;
	private final UserRepository userRepository;

	public void execute(String token) {

		Token foundToken = tokenRepository.findTokenByTokenText(token)
		                             .orElseThrow(() -> ResourceNotFoundException.of(Resources.TOKEN, token));

		String tokenOwnerUserEmail = foundToken.getEmail();

		User user = userRepository.findUserByEmail(tokenOwnerUserEmail)
		                          .orElseThrow(() -> ResourceNotFoundException.of(Resources.EMAIL, tokenOwnerUserEmail));

		foundToken.validate().useToken();
		user.activateAccount();
	}
}
