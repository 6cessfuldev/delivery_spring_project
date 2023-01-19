package com.ezen.delivery.test;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ezen.delivery.domain.UserVO;
import com.ezen.delivery.repository.UserDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class UserDAOTest {
	
	@Inject
	private UserDAO udao;
	
	@Test
	public void insertUser() {
		
		UserVO uvo = new UserVO();
		
		uvo.setUser_id("testId");
		uvo.setUser_email("test1@test.com");
		uvo.setUser_pw("testPw*");
		uvo.setUser_name("kang");
		uvo.setUser_birth("950101");
		uvo.setUser_phone("010-1234-5678");
		
		boolean isOk = udao.insertUser(uvo);
		
		log.info("insert user test >>> " + (isOk ? "Ok" : "Fail"));
	}

}
