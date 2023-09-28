package com.atarget.atargetbackend.auth.service;

import com.atarget.atargetbackend.auth.domain.Token;
import com.atarget.atargetbackend.auth.domain.User;
import com.atarget.atargetbackend.auth.repository.TokenRepository;
import com.atarget.atargetbackend.persona.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UseTokenForActivateAccountService {

	private final TokenRepository tokenRepository;
	private final UserRepository userRepository;

	public void execute(String tokenText) {

		Token foundToken = tokenRepository.findTokenByTokenText(tokenText)
		                             .orElseThrow(() -> new IllegalArgumentException("The token " + tokenText + " does not exist."));

		String tokenOwnerUserId = foundToken.getTokenUserId();

		User user = userRepository.findById(tokenOwnerUserId)
		                          .orElseThrow(() -> new IllegalArgumentException("An user with specified token ID does not exist."));

		foundToken.validateGivenEmailWithTokenEmail(user.getEmail());

		foundToken.validate().useToken();
		user.activateAccount();
	}
}
