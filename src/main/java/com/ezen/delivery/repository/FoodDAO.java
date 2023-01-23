package com.ezen.delivery.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ezen.delivery.domain.AdminPagingVO;
import com.ezen.delivery.domain.FoodDTO;
import com.ezen.delivery.domain.FoodVO;

public interface FoodDAO {

	List<FoodVO> selectList(int diner_code);

	int insert(FoodVO fvo);

	FoodVO selectByFoodCode(int food_code);

	int update(FoodVO fvo);

	int delete(int food_code);

	int selectTotalCount(int diner_code);

	List<FoodVO> selectListByDinerCodeWithPaging(@Param("diner_code")int diner_code, @Param("pgvo")AdminPagingVO pgvo);


}
