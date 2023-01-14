package com.ezen.delivery.repository;

import java.util.List;

import com.ezen.delivery.domain.BasketDTO;
import com.ezen.delivery.domain.BasketDetailVO;

public interface BasketDetailDAO {

	int insert(BasketDetailVO bdvo);

	List<BasketDetailVO> selectList(int basket_code);

	int deleteByBasketCode(int basket_code);
	
	

}
