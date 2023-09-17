package com.atarget.atargetbackend.auth.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users") //To avoid conflict with Postgres default table.
@Getter
public class User extends AbstractAuditable<Persona, String> implements UserDetails {

	@Column(unique = true) private String email;

	private String password;

	@Enumerated(EnumType.STRING) private UserRole userRole;

	public User() {}

	private User(String email, String password, UserRole userRole) {

		this.email = email;
		this.password = password;
		this.userRole = userRole;
	}

	public static User of(String email, String password, UserRole userRole) {

		return new User(email, password, userRole);
	}

	/***
	 * Se o usuário for um administrador, ele terá todas as permissões de um usuário comum, somadas com
	 * as permissões de um administrador. Se ele for apenas um usuário comum, terá apenas as permissões de
	 * tal.
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		if (this.userRole.equals(UserRole.ADMIN)) {
			return List.of(new SimpleGrantedAuthority(UserRole.ADMIN.getRole()),
			               new SimpleGrantedAuthority(UserRole.COMMON_USER.getRole()));
		} else {
			return List.of(new SimpleGrantedAuthority(UserRole.COMMON_USER.getRole()));
		}
	}

	@Override
	public String getUsername() {

		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}
}
