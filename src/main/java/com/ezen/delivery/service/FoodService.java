package com.ezen.delivery.service;

import java.util.List;

import com.ezen.delivery.domain.DinerVO;
import com.ezen.delivery.domain.FoodVO;

public interface FoodService {



	List<FoodVO> getFoodByDinerCode(int diner_code);

	//List<FoodVO> getFoodDinerCode(int diner_code);

}
