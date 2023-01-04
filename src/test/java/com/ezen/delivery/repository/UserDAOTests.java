package com.ezen.delivery.repository;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ezen.delivery.domain.UserVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class UserDAOTests {

	
	@Inject
	private UserDAO udao;
	
	@Test
	public void testInsertUser() {
		UserVO user = new UserVO();
		user.setUser_email("test@test.test");
		user.setUser_birth("19930513");
		user.setUser_name("test");
		user.setUser_nick("666");
		user.setUser_phone("01012341234");
		user.setUser_pw("testtest");
		
		int isOk = udao.insertUser(user);
		
		log.info(isOk+"");
	}
	
	@Test
	public void testGetUser() {
	
		String email = "test@test.test";
		
		UserVO user = udao.getUser(email);
		
		log.info(user.toString());
	}
	
	
}
