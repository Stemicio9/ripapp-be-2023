package it.ripapp.ripapp.authentication.model;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class FirebaseAuthenticationToken extends UsernamePasswordAuthenticationToken {
	private static final long serialVersionUID = 1L;
	private String token;
	private String uid;
	private String email;

	public FirebaseAuthenticationToken(final String uid, final String email) {
		super(null, null);
		this.uid = uid;
		this.email = email;
	}

	public FirebaseAuthenticationToken(final String token) {
		super(null, null);
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public String getUid() {
		return uid;
	}

	public String getEmail() {
		return email;
	}
}
