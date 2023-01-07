package com.ezen.delivery.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ezen.delivery.domain.FoodDTO;
import com.ezen.delivery.domain.FoodVO;
import com.ezen.delivery.repository.FileDAO;
import com.ezen.delivery.repository.FoodDAO;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class FoodServiceImpl implements FoodService {

	@Inject
	FoodDAO fdao;
	
	@Inject
	FileDAO fido;
	
	
	@Override
	public List<FoodVO> getListByDinerCode(int diner_code) {
		
		return fdao.selectList(diner_code);
	}

	@Override
	public int register(FoodDTO fdto) {
		
		int isOk = 1;
		
		if(fdto.getFilevo() != null) {
			fido.insert(fdto.getFilevo());			
		}
		
		isOk *= fdao.insert(fdto.getFoodvo());
		
		return isOk; 
	}

	@Override
	public FoodVO getFood(int food_code) {
		
		return fdao.selectByFoodCode(food_code);
	}

	@Override
	public int update(FoodVO fvo) {
		
		return fdao.update(fvo);
	}

	@Override
	public int remove(int food_code) {

		return fdao.delete(food_code);
	}



}
