package com.atarget.atargetbackend.auth.service;

import com.atarget.atargetbackend.persona.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class VerifyIfTheEmailIsUsedService {

	private final UserRepository userRepository;

	public Boolean execute(String email){

		return userRepository.existsUserByEmail(email);
	}
}
