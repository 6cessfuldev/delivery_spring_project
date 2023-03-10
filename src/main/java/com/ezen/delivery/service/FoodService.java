package com.ezen.delivery.service;

import java.util.List;

import com.ezen.delivery.domain.AdminPagingVO;
import com.ezen.delivery.domain.DinerVO;
import com.ezen.delivery.domain.FoodDTO;
import com.ezen.delivery.domain.FoodVO;

public interface FoodService {

	List<FoodDTO> getListByDinerCode(int diner_code);

	int register(FoodDTO fdto);

	FoodVO getFood(int food_code);

	int update(FoodVO fvo);

	int remove(int food_code);

	FoodDTO getDetail(int food_code);

	int totalCount(int diner_code);

	List<FoodDTO> getListByDinerCodeWithPaging(int diner_code, AdminPagingVO pgvo);

	//List<FoodVO> getFoodDinerCode(int diner_code);

}
