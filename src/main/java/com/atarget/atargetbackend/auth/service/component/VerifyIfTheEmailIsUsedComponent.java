package com.atarget.atargetbackend.auth.service.component;

import com.atarget.atargetbackend.persona.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VerifyIfTheEmailIsUsedComponent {

	private final UserRepository userRepository;

	public Boolean execute(String email){

		return userRepository.existsUserByEmail(email);
	}
}
