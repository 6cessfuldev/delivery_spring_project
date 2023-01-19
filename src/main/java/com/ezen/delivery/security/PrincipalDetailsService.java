package com.ezen.delivery.security;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ezen.delivery.domain.UserVO;
import com.ezen.delivery.repository.UserDAO;
import com.ezen.delivery.security.oauth2.PrincipalDetails;

@Service
public class PrincipalDetailsService implements UserDetailsService {

	@Inject
	private UserDAO udao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 
		System.out.println(username);
		UserVO byUsername = udao.getUser(username);
		System.out.println(byUsername.toString());
        if(byUsername != null){
            return new PrincipalDetails(byUsername);
        }
        return null;
	}

}
