package com.ezen.delivery.security;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml", 
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/security-context.xml"})
@Slf4j
public class PasswordEncoderTests {

	@Inject
	private PasswordEncoder pwEncoder;
	
	@Test
	public void testEncode() {
		String str = "member";
		String enStr = pwEncoder.encode(str);
		log.info(enStr);
	}
}
