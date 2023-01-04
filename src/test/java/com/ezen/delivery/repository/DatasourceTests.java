package com.ezen.delivery.repository;

import static org.junit.Assert.fail;

import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class DatasourceTests {

	@Inject
	private DataSource dataSource;
	
	@Inject
	private SqlSessionFactory factory;
	
	@Test
	public void testConnection() {
		
		try {
			Connection con = dataSource.getConnection();
			log.info(con.toString());
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
	}
	
	@Test
	public void testMybatis() {
		
		try {
			SqlSession session = factory.openSession();
			Connection con = session.getConnection();
			
			log.info(session.toString());
			log.info(con.toString());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
		

}
