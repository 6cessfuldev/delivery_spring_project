package com.ezen.delivery.repository;

import java.util.List;

import com.ezen.delivery.domain.BasketDTO;
import com.ezen.delivery.domain.BasketVO;

public interface BasketDAO {

	public int insert(BasketDTO basket) throws Exception;
	
	public int delete(int basketId);
	
	public int updateCount(BasketDTO basket);
	
	public List<BasketDTO> selectList(String basket_user_id);
	
	public BasketDTO checkBasket(BasketDTO basket);

	public int selectDinerCodeByUserId(String user_id);
	
}
