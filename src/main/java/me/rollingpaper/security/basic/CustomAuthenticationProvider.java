package me.rollingpaper.security.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider{
	
	@Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
        String password = (String)authentication.getCredentials();
        log.info("username : " + username);
        log.info("password : " + password);
        
        User user = null;
        
        try {
        	user = (User) userDetailsService.loadUserByUsername(username);
        }catch(NullPointerException e) {
        	throw new BadCredentialsException("BadCredentialException");
        }
        
        // password 일치하지 않으면!
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new BadCredentialsException("BadCredentialException");
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), null, 
        																								  user.getAuthorities());
        return authenticationToken;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
