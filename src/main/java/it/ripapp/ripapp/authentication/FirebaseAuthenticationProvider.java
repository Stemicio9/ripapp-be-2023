package it.ripapp.ripapp.authentication;

import com.google.api.core.ApiFuture;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import it.ripapp.ripapp.authentication.model.FirebaseAuthenticationToken;
import it.ripapp.ripapp.authentication.model.FirebaseUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;


@Component
public class FirebaseAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	private final FirebaseAuth firebaseAuth;

	@Autowired
	public FirebaseAuthenticationProvider(FirebaseAuth firebaseAuth) {
		this.firebaseAuth = firebaseAuth;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (FirebaseAuthenticationToken.class.isAssignableFrom(authentication));
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
        UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		final FirebaseAuthenticationToken authenticationToken = (FirebaseAuthenticationToken) authentication;

		if (authenticationToken.getEmail() != null && authenticationToken.getUid() != null)
			return new FirebaseUserDetails(authenticationToken.getEmail(), authenticationToken.getUid());


		ApiFuture<FirebaseToken> task = firebaseAuth.verifySessionCookieAsync(authenticationToken.getToken());
		try {
			FirebaseToken token = task.get();
			return new FirebaseUserDetails(token.getEmail(), token.getUid());
		} catch (InterruptedException | ExecutionException e) {
			throw new SessionAuthenticationException(e.getMessage());
		}
	}
}
