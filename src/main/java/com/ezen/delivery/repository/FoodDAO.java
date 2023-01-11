package com.ezen.delivery.repository;

import java.util.List;

import com.ezen.delivery.domain.FoodVO;

public interface FoodDAO {

	List<FoodVO> selectList(int diner_code);

	int insert(FoodVO fvo);

	FoodVO selectByFoodCode(int food_code);

	int update(FoodVO fvo);

	int delete(int food_code);



}
