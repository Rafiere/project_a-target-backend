package com.atarget.atargetbackend.auth.repository;

import com.atarget.atargetbackend.persona.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface PersonaRepository extends JpaRepository<Persona, String> {

	@Query("SELECT u " +
	       "FROM User u " +
	       "WHERE u.email = :email")
	Optional<UserDetails> findByEmail(String email);

	@Query("SELECT p " +
	       "FROM Persona p " +
	       "WHERE p.user.email = :email")
	Optional<Persona> findPersonaByEmail(String email);
}
