package com.ezen.delivery.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ezen.delivery.domain.BasketDTO;
import com.ezen.delivery.domain.BasketDetailVO;
import com.ezen.delivery.domain.BasketListDTO;
import com.ezen.delivery.domain.ChoiceVO;
import com.ezen.delivery.domain.DinerVO;
import com.ezen.delivery.repository.BasketDAO;
import com.ezen.delivery.repository.BasketDetailDAO;
import com.ezen.delivery.repository.ChoiceDAO;
import com.ezen.delivery.repository.DinerDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BasketServiceImpl implements BasketService {

	@Inject
	private BasketDAO bdao;
	
	@Inject
	private BasketDetailDAO bddao;
	
	@Inject
	private ChoiceDAO cdao;
	
	@Inject
	private DinerDAO ddao;
	
	@Override
	@Transactional
	public int addBasket(BasketDTO basket) {
		
		BasketDTO checkBasket = bdao.checkBasket(basket);
		
		if(checkBasket != null) {
			return 2;
		}
		
		try {
			int isOk = 1;
			isOk *= bdao.insert(basket);
			for (ChoiceVO cvo : basket.getChoiceList()) {
				BasketDetailVO bdvo = new BasketDetailVO(basket.getBasket_code(), cvo.getChoice_code());
				isOk *= bddao.insert(bdvo);
			}
			return isOk;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	@Transactional
	public List<BasketDTO> getList(String user_id) {
		
		//장바구니 기본정보 basket_Code / food_code / basket_order_count
		List<BasketDTO> bdtoList = bdao.selectList(user_id);
		
		for (BasketDTO bdto : bdtoList) {
			
			//주문 옵션 리스트 choiceList
			List<ChoiceVO> choiceList = new ArrayList<ChoiceVO>();
			List<BasketDetailVO> bdvoList = bddao.selectList(bdto.getBasket_code());
			for (BasketDetailVO bdvo : bdvoList) {
				choiceList.add(cdao.selectOne(bdvo.getChoice_code()));
			}
			
			bdto.setChoiceList(choiceList);
			
			bdto.initBasket_content();
			bdto.initSalePerOne();
		}
		return bdtoList;
	}
	

	@Override
	@Transactional
	public int removeByBasketCode(int basket_code) {
		
		int isOk = bdao.delete(basket_code);
		isOk *= bddao.deleteByBasketCode(basket_code);
		
		return isOk;
	}

	@Override
	public int modifyCount(BasketDTO basket) {
			
		return bdao.updateCount(basket);
	}

	@Override
	public int getDinerCode(String user_id) {
		
		int diner_code = bdao.selectDinerCodeByUserId(user_id);
		log.info(diner_code+"");
		return diner_code;
	}

	@Override
	public DinerVO getDiner(String user_id) {
		
		return bdao.selectDinerByUserId(user_id);
	}

	@Override
	public int getCount(String user_id) {
		
		return bdao.selectCount(user_id);
	}

	@Override
	public int getDinerCodeByBasketCode(int basket_code) {
		return bdao.selectDinerCodeByBasketCode(basket_code);
	}

}
