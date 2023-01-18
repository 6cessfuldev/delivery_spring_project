package com.ezen.delivery.security;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Resource
	CustomUserDetailsService customUserDetailsService;
	
	@Resource
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) authentication; 
		 
        UserDetails userInfo = customUserDetailsService.loadUserByUsername(authToken.getName()); 
        if (userInfo == null) {
          throw new UsernameNotFoundException(authToken.getName());
        }
 
        if (!matchPassword(userInfo.getPassword(), authToken.getCredentials())) {
          throw new BadCredentialsException("not matching username or password");
        }
 
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) userInfo.getAuthorities();
        
        return new UsernamePasswordAuthenticationToken(userInfo,null,authorities);
        
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

	private boolean matchPassword(String password, Object credentials) {
        return passwordEncoder.matches((String) credentials, password);
    }
}
