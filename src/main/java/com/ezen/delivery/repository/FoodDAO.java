package com.ezen.delivery.repository;

import java.util.List;

import com.ezen.delivery.domain.FoodVO;

public interface FoodDAO {

	List<FoodVO> selectDinerList(int diner_code);



}
