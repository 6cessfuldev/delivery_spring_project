package com.ezen.delivery.service;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ezen.delivery.domain.DinerDTO;
import com.ezen.delivery.domain.DinerVO;
import com.ezen.delivery.domain.FileVO;
import com.ezen.delivery.domain.PagingVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml", 
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/security-context.xml"})
public class DinerServiceTests {

	@Inject
	private DinerService dsv;
	
	@Test
	public void testGetList() {
		
		List<DinerVO> dinerList = dsv.getList();	
		log.info(dinerList.size()+"");
	}
	
	@Test
	public void testGetListFirst() {
		List<DinerVO> dinerList = dsv.getListFirst();
		assertThat(dinerList.size(), lessThanOrEqualTo(20));
	}
	
	@Test
	public void testRegister() {

		DinerDTO ddto = new DinerDTO();
		
		String diner_name = "testName";
		String diner_address = "경상남도 창원시 진해구 용원동 1213-1 파라다이스 1213-1";
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
		
		FileVO fivo = new FileVO();
		fivo.setFile_name("meat.jpg");
		fivo.setFile_uuid("2b115af3-9d58-4c31-bbe0-d233783c202f");
		fivo.setFile_save_dir("2022\11\12");
		fivo.setFile_size(1000);
		fivo.setFile_type(1);
		
		ddto.setDvo(dvo);
		ddto.setFivo(fivo);
		
		int isOk = dsv.register(ddto);
		assertThat(isOk, is(1));
		
	}
	
	@Test
	public void testGetListWithPagingVO() {
		
		//카테고리 값이 있는 경우 "ff" 문자열을 포함한 category 매개변수가 있는 diner만 가져온다.
		PagingVO pgvo = new PagingVO();
		pgvo.setCategory("ff");
		pgvo.setLng("137");
		pgvo.setLat("30");
		pgvo.setPageNum(2);
		
		List<DinerDTO> ddtoList = dsv.getList(pgvo);
		for (DinerDTO ddto : ddtoList) {
			assertThat(ddto.getDvo().getDiner_category(), containsString("ff"));
		}
		
		//카테고리 값이 없는 경우 "all" 전체 가져오기
		PagingVO pgvo2 = new PagingVO();
		pgvo2.setCategory("all");
		pgvo2.setLng("137");
		pgvo2.setLat("30");
		pgvo2.setPageNum(2);
		
		List<DinerDTO> ddtoList2 = dsv.getList(pgvo);
		assertThat(ddtoList2.size(), lessThanOrEqualTo(10));
	}
	
	@Test
	public void testGetDiner() {
		
		int diner_code = 101;
		DinerDTO ddto = dsv.getDiner(diner_code);
		assertThat(ddto.getDvo().getDiner_code(), is(diner_code));
		assertThat(ddto.getDvo().getFile_code(), is(ddto.getFivo().getFile_code()));
		
	}
	
	@Test
	public void testUpdate() {
		DinerDTO ddto = new DinerDTO();

		int diner_code = 101;
		String diner_name = "updateName";
		String diner_address = "경상남도 창원시 진해구 업데이트동 1213-1 파라다이스 1213-1";
		int diner_min_pay = 12345;
		String diner_notice = "공지사항 update입니다.";
		String diner_open_time="10:00:00";
		String diner_close_time="23:00:00";
		String diner_method_pay="업데이트테스트결제";
		String diner_business_name = "업데이트족발야시장 간석오거리점";
		String diner_company_name = "205-40-75572";
		String diner_category = "ffhhkk";
		int diner_delivery_fee = 4000;
		String diner_addr_lng = "126.707584822067";
		String diner_addr_lat = "37.4654802111296";
		
		DinerVO dvo = new DinerVO();
		dvo.setDiner_code(diner_code);
		dvo.setDiner_name(diner_name);
		dvo.setDiner_address(diner_address);
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

		//입력되는 새로운 파일이 있을 경우
		FileVO fivo = new FileVO();
		String file_name= "meat.jpg";
		String file_uuid= "2b115af3-9d58-4c31-bbe0-d233783c202f";
		String file_save_dir= "2022\11\12";
		int file_size = 1235;
		int file_type = 1;
		
		fivo.setFile_name(file_name);
		fivo.setFile_uuid(file_uuid);
		fivo.setFile_save_dir(file_save_dir);
		fivo.setFile_size(file_size);
		fivo.setFile_type(file_type);
		
		ddto.setDvo(dvo);
		ddto.setFivo(fivo);
		
		int isUp =dsv.update(ddto);
		assertThat(isUp, is(1));
		
		DinerDTO resultdto = dsv.getDiner(diner_code);
		DinerVO resultdvo = resultdto.getDvo();
		assertThat(resultdto.getDvo().getDiner_code(), is(diner_code));
		assertThat(resultdto.getDvo().getDiner_name(),is(diner_name));
		assertThat(resultdto.getDvo().getDiner_address(),is(diner_address));
		assertThat(resultdto.getDvo().getDiner_min_pay(),is(diner_min_pay));
		assertThat(resultdto.getDvo().getDiner_notice(),is(diner_notice));
		assertThat(resultdto.getDvo().getDiner_open_time(),is(diner_open_time));
		assertThat(resultdto.getDvo().getDiner_close_time(),is(diner_close_time));
		assertThat(resultdto.getDvo().getDiner_method_pay(),is(diner_method_pay));
		assertThat(resultdto.getDvo().getDiner_business_name(),is(diner_business_name));
		assertThat(resultdto.getDvo().getDiner_company_num(),is(diner_company_name));
		assertThat(resultdto.getDvo().getDiner_category(),is(diner_category));
		assertThat(resultdto.getDvo().getDiner_delivery_fee(),is(diner_delivery_fee));
		assertThat(resultdto.getDvo().getDiner_addr_lng(),is(diner_addr_lng));
		assertThat(resultdto.getDvo().getDiner_addr_lat(),is(diner_addr_lat));
		
		FileVO resultfivo = resultdto.getFivo();
		assertThat(resultfivo.getFile_name(), is(file_name));
		assertThat(resultfivo.getFile_uuid(), is(file_uuid));
		assertThat(resultfivo.getFile_save_dir(), is(file_save_dir));
		assertThat(resultfivo.getFile_size(), is(file_size));
		assertThat(resultfivo.getFile_type(), is(file_type));
		assertNotNull(resultfivo.getFile_reg_date());
	}
	
	
}
