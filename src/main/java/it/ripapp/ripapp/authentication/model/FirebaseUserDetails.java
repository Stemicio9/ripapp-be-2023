package it.ripapp.ripapp.authentication.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class FirebaseUserDetails implements UserDetails {
	private static final long serialVersionUID = 1L;

	private final boolean enabled = true;
	private final boolean credentialsNonExpired = true;
	private final boolean accountNonLocked = true;
	private final boolean accountNonExpired = true;
	private final String password = null;
	private String username ;
	private String id;

	public FirebaseUserDetails(String email, String uid) {
		this.username = email;
		this.id = uid;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public String getPassword() {
		return password;
	}

}
