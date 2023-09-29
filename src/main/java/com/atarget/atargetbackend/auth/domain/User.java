package com.atarget.atargetbackend.auth.domain;

import com.atarget.atargetbackend.persona.domain.enums.UserAccountStatus;
import com.atarget.atargetbackend.shared.audit.BaseAuditableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users") //To avoid conflict with Postgres default table.
@Getter
public class User extends BaseAuditableEntity implements UserDetails {

	@Id private String id;
	@Column(unique = true) private String email;

	private String password;

	@Enumerated(EnumType.STRING) private UserRole userRole;
	@Enumerated(EnumType.STRING) private UserAccountStatus userAccountStatus;

	public User() {}

	private User(String email, String password, UserRole userRole) {
		this.id = UUID.randomUUID().toString();
		this.email = email;
		this.password = password;
		this.userRole = userRole;
		this.userAccountStatus = UserAccountStatus.NOT_ACTIVE;
	}

	public static User of(String email, String password, UserRole userRole) {

		return new User(email, password, userRole);
	}

	public User activateAccount() {

		this.userAccountStatus = UserAccountStatus.ACTIVATED;

		return this;
	}

	/**
	 * <p>If the user is an administrator, he will have all the permissions of a regular user, plus
	 * as permissions of an administrator. If he is just a regular user, he will only have permissions to
	 * such.</p>
	 *
	 * @return a collection with the user roles.
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
