package com.ezen.delivery.service;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ezen.delivery.domain.BasketDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml", 
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/security-context.xml"})
@Transactional
public class BasketServiceTests {
	
	@Inject
	private BasketService service;
	
	@Test
	public void testisExist() {
		log.info(service.toString());
	}
	
	@Test
	public void addCartTest() {
		
		String user_id = "test";
		int food_code = 1;
		int count = 3;
		
		BasketDTO basket = new BasketDTO();
		basket.setBasket_user_id(user_id);
		basket.setBasket_food_code(food_code);
		basket.setBasket_order_count(count);
		
		int result = service.addBasket(basket);
		
		System.out.println("result : " + result);
	}
	
}
