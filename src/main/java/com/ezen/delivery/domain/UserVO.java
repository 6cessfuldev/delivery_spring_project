package com.ezen.delivery.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserVO implements UserDetails, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String user_id;
	private String user_email;
	private String user_pw;
	private String user_name;
	private String user_phone;
	private String user_birth;
	private String user_naver_id;
	private String user_register_date;
	private String user_modify_date;
	private int user_isAdmin;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		String roleGrant = "ROLE_USER";
        
        GrantedAuthority myGrant = new SimpleGrantedAuthority(roleGrant);
        
        List<GrantedAuthority> authorities = new ArrayList<>();
        
        authorities.add(myGrant);
		return authorities;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.user_pw;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.user_id;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}
