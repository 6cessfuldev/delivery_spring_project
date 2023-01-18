package com.ezen.delivery.security;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ezen.delivery.repository.UserDAO;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Inject
	private UserDAO udao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 
		UserDetails userInfo = null;
	        try {
	            userInfo = (UserDetails) udao.getUser(username); 
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        return userInfo;
	}

}
