package com.ezen.delivery.repository;

import static org.junit.Assert.*;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ezen.delivery.domain.UserVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class DatasourceTests {

	@Inject
	private UserDAO udao;
	
	@Test
	public void userdaoInsertTest(){
		
		UserVO uvo = new UserVO();
		uvo.setUser_birth(null);
		uvo.setUser_birth(null);
		uvo.setUser_birth(null);
		uvo.setUser_birth(null);
		uvo.setUser_birth(null);

		int isOk = udao.insertUser(uvo);
		
	}

}
