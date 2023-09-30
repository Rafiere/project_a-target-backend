package com.atarget.atargetbackend.persona.repository;

import com.atarget.atargetbackend.auth.domain.User;
import com.atarget.atargetbackend.persona.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

	@Query("SELECT p " +
	       "FROM Persona p " +
	       "WHERE p.user.email = :email")
	Optional<Persona> findPersonaByEmail(String email);

	boolean existsUserByEmail(String email);

	@Query("SELECT u FROM User u WHERE u.email = :tokenOwnerUserEmail")
	Optional<User> findUserByEmail(String tokenOwnerUserEmail);
}
