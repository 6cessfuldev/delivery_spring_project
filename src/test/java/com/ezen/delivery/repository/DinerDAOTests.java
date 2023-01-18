package com.ezen.delivery.repository;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ezen.delivery.domain.DinerVO;
import com.ezen.delivery.domain.PagingVO;
import com.ezen.delivery.util.DistanceCheck;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@Transactional
public class DinerDAOTests {

	@Inject
	private DinerDAO ddao;
	
	@Test
	public void testSelectListFirst() {
		List<DinerVO> list = ddao.selectListFirst();
		assertThat(list.size(), is(20));
	}
	
	@Test
	public void testSelectList() {
		
		PagingVO pgvo = new PagingVO();
		pgvo.setLng("128.8107130286");
		pgvo.setLat("35.10145118813");
		
		List<DinerVO> list = ddao.selectList(pgvo);
		
		double prev = 0;
		double next = 0;
		
		log.info(list.size()+"");
		for (DinerVO dinerVO : list) {
			next = DistanceCheck.getDistance(pgvo.getLat(), pgvo.getLng(), dinerVO.getDiner_addr_lat(), dinerVO.getDiner_addr_lng());			
			assertThat(prev, is(lessThanOrEqualTo(next)));
			prev = next;
		}
		
		assertThat(list.size(), is( lessThanOrEqualTo(10) ) );
	}
	
	@Test
	public void testSelectListByCate() {
		
		PagingVO pgvo = new PagingVO();
		pgvo.setCategory("ff");
		pgvo.setLng("128.8107130286");
		pgvo.setLat("35.10145118813");
		
		List<DinerVO> list = ddao.selectListbyCate(pgvo);
		
		for (DinerVO dinerVO : list) {
			assertThat(dinerVO.getDiner_category(),containsString("ff"));
		}
		
		assertThat(list.size(), is( lessThanOrEqualTo(10) ) );
	}
	
	@Test
	public void testInsert() {
		
		String diner_name = "testName";
		String diner_address = "경상남도 창원시 진해구 용원동 1213-1 파라다이스 1213-1";
		int file_code = 1;
		int diner_min_pay = 10000;
		String diner_notice = "공지사항 테스트입니다.";
		String diner_open_time="10:00";
		String diner_close_time="23:00";
		String diner_method_pay="테스트결제";
		String diner_business_name = "족발야시장 간석오거리점";
		String diner_company_name = "205-40-75572";
		String diner_category = "ffhhkk";
		int diner_delivery_fee = 4000;
		String diner_addr_lng = "126.707584822067";
		String diner_addr_lat = "37.4654802111296";
		
		DinerVO dvo = new DinerVO();
		dvo.setDiner_name(diner_name);
		dvo.setDiner_address(diner_address);
		dvo.setFile_code(file_code);
		dvo.setDiner_min_pay(diner_min_pay);
		dvo.setDiner_notice(diner_notice);
		dvo.setDiner_open_time(diner_open_time);
		dvo.setDiner_close_time(diner_close_time);
		dvo.setDiner_method_pay(diner_method_pay);
		dvo.setDiner_business_name(diner_business_name);
		dvo.setDiner_company_num(diner_company_name);
		dvo.setDiner_category(diner_category);
		dvo.setDiner_delivery_fee(diner_delivery_fee);
		dvo.setDiner_addr_lng(diner_addr_lng);
		dvo.setDiner_addr_lat(diner_addr_lat);
		
		int isOk = ddao.insert(dvo);
		
		assertThat(isOk, is(1));
	}
	
	@Test
	public void testSelectDiner() {
		
		int diner_code = 101;
		DinerVO dvo = ddao.selectDiner(diner_code);
		
		assertThat(dvo.getDiner_code(),is(diner_code));		
	}
	
	@Test
	public void testUpdate() {
		
		DinerVO dvo = new DinerVO();
		int diner_code = 132;
		String diner_name = "수정된 이름";
		String diner_address = "경상남도 창원시 진해구 용원동 1213-1 파라다이스 1213-1";
		int file_code = 1;
		int diner_min_pay = 10000;
		String diner_notice = "수정된 공지사항 테스트입니다.";
		String diner_open_time="10:00";
		String diner_close_time="23:00";
		String diner_method_pay="테스트결제";
		String diner_business_name = "족발야시장 간석오거리점";
		String diner_company_name = "205-40-75572";
		String diner_category = "ffhhkk";
		int diner_delivery_fee = 4000;
		String diner_addr_lng = "126.707584822067";
		String diner_addr_lat = "37.4654802111296";
		
		dvo.setDiner_code(diner_code);
		dvo.setDiner_name(diner_name);
		dvo.setDiner_address(diner_address);
		dvo.setFile_code(file_code);
		dvo.setDiner_min_pay(diner_min_pay);
		dvo.setDiner_notice(diner_notice);
		dvo.setDiner_open_time(diner_open_time);
		dvo.setDiner_close_time(diner_close_time);
		dvo.setDiner_method_pay(diner_method_pay);
		dvo.setDiner_business_name(diner_business_name);
		dvo.setDiner_company_num(diner_company_name);
		dvo.setDiner_category(diner_category);
		dvo.setDiner_delivery_fee(diner_delivery_fee);
		dvo.setDiner_addr_lng(diner_addr_lng);
		dvo.setDiner_addr_lat(diner_addr_lat);
		
		int isUp = ddao.update(dvo);
		assertThat(isUp, is(1));
	}
	
	@Test
	public void testDelete() {
		
		int diner_code = 132;
		int isDel = ddao.delete(diner_code);
		assertThat(isDel, is(1));
		
	}
}
