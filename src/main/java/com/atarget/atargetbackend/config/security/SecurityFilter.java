package com.atarget.atargetbackend.config.security;

import com.atarget.atargetbackend.auth.repository.PersonaRepository;
import com.atarget.atargetbackend.shared.auth.ManipulateAuthTokenComponent;
import com.atarget.atargetbackend.shared.auth.enums.AuthTokenType;
import com.atarget.atargetbackend.shared.exception.custom.ResourceNotFoundException;
import com.atarget.atargetbackend.shared.exception.custom.enums.Resources;
import com.atarget.atargetbackend.shared.routes.RoutesGroups;
import com.auth0.jwt.interfaces.DecodedJWT;
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

	private final ManipulateAuthTokenComponent manipulateAuthTokenComponent;

	private final PersonaRepository personaRepository;

	@Override
	protected void doFilterInternal(final HttpServletRequest request,
	                                final HttpServletResponse response,
	                                final FilterChain filterChain) throws ServletException, IOException {

		final var token = this.recoverToken(request);

		boolean isSomeUrlThatShouldNotPassInsideThisFilter = request.getRequestURI().equals(RoutesGroups.AUTH.getPath() + "refresh-token");

		if (token !=
		    null && (!isSomeUrlThatShouldNotPassInsideThisFilter)) {

			final DecodedJWT decodedJWT = manipulateAuthTokenComponent.validate(AuthTokenType.ACCESS_TOKEN, token);

			final String userEmail = decodedJWT.getSubject();

			final UserDetails user = personaRepository.findByEmail(userEmail)
			                                    .orElseThrow(() -> ResourceNotFoundException.of(Resources.EMAIL, userEmail));

			final var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

		filterChain.doFilter(request, response);
	}

	private String recoverToken(final HttpServletRequest request) {

		final var authHeader = request.getHeader("Authorization");

		if (authHeader ==
		    null) {
			return null;
		}

		return authHeader.replace("Bearer ", "");
	}
}
