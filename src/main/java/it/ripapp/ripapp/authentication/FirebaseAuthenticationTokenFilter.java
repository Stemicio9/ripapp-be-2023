package it.ripapp.ripapp.authentication;

import com.google.api.client.util.Strings;
import it.ripapp.ripapp.authentication.model.FirebaseAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class FirebaseAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

    public FirebaseAuthenticationTokenFilter() {
        super("/auth/**");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {

        String header = request.getHeader("cookie");

        if (request.getHeader("cookie") == null || header.split(";").length == 0)
        {
            logFailure(request);
            throw new AuthException("cookie not found");
        }

        String cookie = "";
        String cookieUid = "";

        for (String h : header.split(";")) {
            if (h.split("=")[0].trim().equalsIgnoreCase("firebasecookie"))
                cookie = h.split("=")[1];

            if (h.split("=")[0].trim().equalsIgnoreCase("uid"))
                cookieUid = h.split("=")[1];
        }

        if (Strings.isNullOrEmpty(cookie)) {
            logFailure(request);
            throw new AuthException("cookie not found");
        }


        String email = request.getHeader("x-firebase-email");
        String uid = request.getHeader("x-firebase-uid");

        if (email != null && uid != null)
        {
            if (!cookieUid.equals(uid))
                throw new AuthException("uid mismatch");

            return getAuthenticationManager().authenticate(new FirebaseAuthenticationToken(uid, email));
        }






        return getAuthenticationManager().authenticate(new FirebaseAuthenticationToken(cookie));
    }


    private void logFailure(HttpServletRequest request){
        logger.warn(request.getMethod()+" "+ request.getRequestURL());

        if (request.getQueryString() != null)
            logger.warn(request.getQueryString());

        if (request.getHeader("cookie") != null && !request.getHeader("cookie").isEmpty())
        {
            String[] cookies = request.getHeader("cookie").split(";");

            for (String cookie : cookies) {
                logger.warn(cookie);
//                if (cookie.split("=")[0].equals("userid"))
//                    logger.warn(cookie.split("=")[1]);
            }
        } else
            logger.warn("no cookie in header");

        logger.warn("Authentication failed \n");

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);

        // As this authentication is in HTTP header, after success we need to continue the request normally
        // and return the response as if the resource was not secured at all
        chain.doFilter(request, response);
    }
}