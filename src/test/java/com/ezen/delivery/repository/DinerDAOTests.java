package com.ezen.delivery.repository;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ezen.delivery.domain.DinerVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class DinerDAOTests {

	@Inject
	private DinerDAO ddao;
	
	@Test
	public void testInsert() {
		
		DinerVO diner = new DinerVO();
		
		
	}
	
}
