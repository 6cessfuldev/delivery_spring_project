package com.ezen.delivery.repository;

import static org.junit.Assert.assertThat;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ezen.delivery.domain.FoodVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class FoodDAOTests {
	
	@Inject
	private FoodDAO fdao;
	
	@Test
	@Transactional
	public void testInsert() {
		
		FoodVO fvo = new FoodVO();
		fvo.setDiner_code(100);
		fvo.setFile_code(1);
		fvo.setFood_intro("맛있는 야식");
		fvo.setFood_name("노랑통닭");
		fvo.setFood_price(23000);
		fvo.setFood_state("N");
		
		int isOk = fdao.insert(fvo);
		log.info(isOk+"");
		
	}
	
}
