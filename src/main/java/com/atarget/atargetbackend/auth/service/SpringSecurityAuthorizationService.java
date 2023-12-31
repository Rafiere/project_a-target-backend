package com.atarget.atargetbackend.auth.service;

import com.atarget.atargetbackend.auth.repository.PersonaRepository;
import com.atarget.atargetbackend.shared.exception.custom.ResourceNotFoundException;
import com.atarget.atargetbackend.shared.exception.custom.enums.Resources;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SpringSecurityAuthorizationService implements UserDetailsService {

	private final PersonaRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {

		return userRepository.findByEmail(email).orElseThrow(() -> ResourceNotFoundException.of(Resources.EMAIL, email));
	}
}

