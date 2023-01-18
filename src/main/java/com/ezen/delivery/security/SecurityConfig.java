package com.ezen.delivery.security;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity
@PropertySource("classpath:application.properties")
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static List<String> clients = Arrays.asList("google", "facebook");
	
	@Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        List<ClientRegistration> registrations = clients.stream()
          .map(c -> getRegistration(c))
          .filter(registration -> registration != null)
          .collect(Collectors.toList());
        
        return new InMemoryClientRegistrationRepository(registrations);
    }
	
	private static String CLIENT_PROPERTY_KEY 
	  = "spring.security.oauth2.client.registration.";

	@Autowired
	private Environment env;

	private ClientRegistration getRegistration(String client) {
	    String clientId = env.getProperty(
	      CLIENT_PROPERTY_KEY + client + ".client-id");

	    if (clientId == null) {
	        return null;
	    }

	    String clientSecret = env.getProperty(
	      CLIENT_PROPERTY_KEY + client + ".client-secret");
	 
	    if (client.equals("google")) {
	        return CommonOAuth2Provider.GOOGLE.getBuilder(client)
	          .clientId(clientId).clientSecret(clientSecret).build();
	    }
	    if (client.equals("facebook")) {
	        return CommonOAuth2Provider.FACEBOOK.getBuilder(client)
	          .clientId(clientId).clientSecret(clientSecret).build();
	    }
	    return null;
	}
	
	@Bean
	public OAuth2AuthorizedClientService authorizedClientService() {
	 
	    return new InMemoryOAuth2AuthorizedClientService(
	      clientRegistrationRepository());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
				.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
			.and()
				.formLogin()
				.loginPage("/member/login")
				.loginProcessingUrl("/login")
				.successHandler(loginSuccessHandler())
			.and()
				.logout()
				.logoutUrl("/member/logout")
				.invalidateHttpSession(true)
				.deleteCookies("remember-me", "JESSION_ID")
			.and()
				.oauth2Login()
				.clientRegistrationRepository(clientRegistrationRepository())
			    .authorizedClientService(authorizedClientService())
			    .loginPage("/member/login");

	}


	@Bean
	public AuthenticationSuccessHandler loginSuccessHandler() {
		return new CustomLoginSuccessHandler();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(customUserService())
			.passwordEncoder(passwordEncoder());
	}

	@Bean
	public UserDetailsService customUserService() {
		return new CustomUserDetailsService();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}