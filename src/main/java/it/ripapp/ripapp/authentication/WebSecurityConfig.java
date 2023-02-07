package it.ripapp.ripapp.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
   
    private final FirebaseAuthenticationProvider authenticationProvider;

    @Autowired
    public WebSecurityConfig(FirebaseAuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(Arrays.asList(authenticationProvider));
    }

    
    public FirebaseAuthenticationTokenFilter authenticationTokenFilterBean() {
        FirebaseAuthenticationTokenFilter authenticationTokenFilter = new FirebaseAuthenticationTokenFilter();
        authenticationTokenFilter.setAuthenticationManager(authenticationManager());
        authenticationTokenFilter.setAuthenticationSuccessHandler((request, response, authentication) -> {});
        return authenticationTokenFilter;
    }

    

    @Override
	public void configure(WebSecurity web) {
		web.ignoring()
			.antMatchers(HttpMethod.OPTIONS);
	}


	@Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
        		.cors()
        		.and()
                .csrf().disable()
                .authorizeRequests()
                	.antMatchers(HttpMethod.OPTIONS).permitAll()
                	.antMatchers("/auth/**").authenticated()
                    .antMatchers("/func/**").authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //.and()

        httpSecurity
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

    }
}
