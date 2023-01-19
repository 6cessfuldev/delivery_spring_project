package com.ezen.delivery.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

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
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Transactional
public class BasketDAOTests {

   @Inject
   private BasketDAO bdao;
   
   @Test
   public void testInsert() throws Exception {
      
      String user_id = "test";
      int food_code = 1;
      int count = 2;
      
      BasketDTO basket = new BasketDTO();
      basket.setUser_id(user_id);
      basket.setFood_code(food_code);
      basket.setBasket_order_count(count);
      
      assertThat(bdao.insert(basket), is(1));
   }
   
   @Test
   public void testModify() {
      int basket_code = 1;
      int count = 3;
      
      BasketDTO basket = new BasketDTO();
      basket.setBasket_code(basket_code);
      basket.setBasket_order_count(count);
      
      bdao.updateCount(basket);
   }
   
   @Test
   public void testDelete() {
      int basket_code = 1;
      bdao.delete(basket_code);
   }
   
   @Test
   public void testSelectList() {
      String user_id = "test";
      
      List<BasketDTO> list = bdao.selectList(user_id);
      for (BasketDTO basket : list) {
         log.info(basket.toString());
         basket.initSalePerOne();
         log.info("init price : "+basket.toString());
      }
   }
   
   @Test
   public void testCheckBasket() {
      String user_id = "test";
      int food_code = 1;
      
      BasketDTO basket = new BasketDTO();
      basket.setUser_id(user_id);
      basket.setFood_code(food_code);
      
      BasketDTO resultBasket = bdao.checkBasket(basket);
      log.info(resultBasket.toString());
   }
}