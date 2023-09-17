package com.atarget.atargetbackend.config.security;

import com.atarget.atargetbackend.auth.repository.PersonaRepository;
import com.atarget.atargetbackend.auth.service.GenerateTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

	private final GenerateTokenService tokenService;

	private final PersonaRepository personaRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request,
	                                HttpServletResponse response,
	                                FilterChain filterChain) throws ServletException, IOException {

		var token = this.recoverToken(request);

		if (token !=
		    null) {
			var login = tokenService.validateToken(token);
			UserDetails user = personaRepository.findByEmail(login)
			                                    .orElseThrow(() -> new IllegalArgumentException("O usuário com o email " + login + " não foi encontrado!"));

			var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

		filterChain.doFilter(request, response);
	}

	private String recoverToken(HttpServletRequest request) {

		var authHeader = request.getHeader("Authorization");

		if (authHeader ==
		    null) {
			return null;
		}

		return authHeader.replace("Bearer ", "");
	}
}
