package com.ezen.delivery.security;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.ezen.delivery.security.oauth2.OAuth2Provider;
import com.ezen.delivery.security.oauth2.PrincipalOauth2UserService;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity
@PropertySource("classpath:application-auth.properties")
@Slf4j
@ComponentScan("com.ezen.delivery")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Inject
	private PrincipalOauth2UserService principalOauth2UserService;
	
	@Resource
    private Environment env;

    private static String CLIENT_PROPERTY_KEY= "spring.security.oauth2.client.registration.";
    private static List<String> clients = Arrays.asList("google", "naver", "facebook", "kakao");

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        List<ClientRegistration> registrations = clients.stream()
                .map(c -> getRegistration(c))
                .filter(registration -> registration != null)
                .collect(Collectors.toList());
        return new InMemoryClientRegistrationRepository(registrations);
    }

    private ClientRegistration getRegistration(String client){
        // API Client Id 불러오기
        String clientId = env.getProperty(
                CLIENT_PROPERTY_KEY + client + ".client-id");

        // API Client Id 값이 존재하는지 확인하기
        if (clientId == null) {
            return null;
        }

        // API Client Secret 불러오기
        String clientSecret = env.getProperty(
                CLIENT_PROPERTY_KEY + client + ".client-secret");

        if (client.equals("google")) {
            return OAuth2Provider.GOOGLE.getBuilder(client)
                    .clientId(clientId)
                    .clientSecret(clientSecret)
                    .scope("email", "profile")
                    .build();
        }

        if (client.equals("facebook")) {
            return OAuth2Provider.FACEBOOK.getBuilder(client)
                    .clientId(clientId)
                    .clientSecret(clientSecret)
                    .build();
        }

        if (client.equals("kakao")) {
            return OAuth2Provider.KAKAO.getBuilder(client)
                    .clientId(clientId)
                    .clientSecret(clientSecret)
                    .build();
        }

        if (client.equals("naver")) {
            return OAuth2Provider.NAVER.getBuilder(client)
                    .clientId(clientId)
                    .clientSecret(clientSecret)
                    .build();
        }

        return null;
    }
	 
    @Override
    public void configure(WebSecurity web) throws Exception {
        // resources 모든 접근을 허용하는 설정을 해버리면
        // HttpSecurity 설정한 ADIM권한을 가진 사용자만 resources 접근가능한 설정을 무시해버린다.
    	web.ignoring()
                .antMatchers("/resources/**")
                .antMatchers("/upload/**");
                
            
    }
	 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable()
			.cors().disable()
				.authorizeRequests()
				.antMatchers("/order/**").hasRole("USER")
			.and()
				.formLogin()
				.loginPage("/member/login")
				.loginProcessingUrl("/member/login")
				.successHandler(loginSuccessHandler())
				.usernameParameter("user_id")
				.passwordParameter("user_pw")
			.and()
				.oauth2Login()
			    .clientRegistrationRepository(clientRegistrationRepository())
                .authorizedClientService(authorizedClientService())
                .loginPage("/member/login.html")
				.userInfoEndpoint()			// 로그인 성공 후 사용자정보를 가져온다
		    		.userService(principalOauth2UserService)
			.and()
				.defaultSuccessUrl("/")
				.successHandler(loginSuccessHandler())
				.failureHandler(loginFailureHandler())

			.and()
				.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/")
				.deleteCookies("JSESSIONID", "remember-me");
			    
	}

	@Bean
    public OAuth2AuthorizedClientService authorizedClientService() {
        return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository());
    }
	
	@Bean
	public AuthenticationSuccessHandler loginSuccessHandler() {
		return new CustomLoginSuccessHandler();
	}
	
	@Bean
	public AuthenticationFailureHandler loginFailureHandler() {
		return new CustomLoginFailureHandler();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(customUserService())
			.passwordEncoder(bcryptPasswordEncoder());
	}

	@Bean
	public UserDetailsService customUserService() {
		return new PrincipalDetailsService();
	}
	
	@Bean
	public PasswordEncoder bcryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}