package com.ezen.delivery.service;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ezen.delivery.domain.UserVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml", 
								"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
								"file:src/main/webapp/WEB-INF/spring/appServlet/security-context.xml"})
public class UserServiceTests {

	@Inject
	private UserService service;
	
	@Test
	public void testExist() {
		log.info(service.toString());
		assertNotNull(service);
	}
	
	@Test
	public void testSignUp(){
		UserVO user = new UserVO();
		user.setUser_email("test@test.test");
		user.setUser_birth("19930513");
		user.setUser_name("test");
		user.setUser_phone("01012341234");
		user.setUser_pw("testtest");
		
		service.signUp(user);
		
		log.info("회원가입 유저 정보 : "+user.toString());
	}
	
	@Test
	public void testIsUser() {
	
		String email = "test@test.test";
		String pw = "testtest";
		
		UserVO user = service.isUser(email, pw);
	
		assertEquals(user.getUser_email(), email);
		assertEquals(user.getUser_pw(), pw);
	
	}
	
	
}
