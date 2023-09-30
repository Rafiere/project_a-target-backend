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
public class User extends BaseAuditableEntity implements UserDetails {

	@Getter @Id private String id;
	@Getter @Column(unique = true) private String email;

	@Getter private String password;

	@Getter @Enumerated(EnumType.STRING) private UserRole userRole;
	@Getter @Enumerated(EnumType.STRING) private UserAccountStatus userAccountStatus;

	public User() {}

	private User(final String email, final String password, final UserRole userRole) {
		this.id = UUID.randomUUID().toString();
		this.email = email;
		this.password = password;
		this.userRole = userRole;
		this.userAccountStatus = UserAccountStatus.NOT_ACTIVE;
	}

	public static User of(final String email, final String password, final UserRole userRole) {

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

		return this.getUserAccountStatus().equals(UserAccountStatus.ACTIVATED);
	}

	public void changePassword(final String encryptedPassword) {

		this.password = encryptedPassword;
	}
}
