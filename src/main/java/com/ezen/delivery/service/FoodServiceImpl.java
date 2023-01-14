package com.ezen.delivery.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ezen.delivery.domain.FileVO;
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
	FileDAO fidao;
	
	
	@Override
	public List<FoodDTO> getListByDinerCode(int diner_code) {
		
		List<FoodDTO> fodtoList = new ArrayList<FoodDTO>();
		List<FoodVO> fovoList = fdao.selectList(diner_code);
		
		for (FoodVO fovo : fovoList) {
			FileVO fivo = fidao.selectByFileCode(fovo.getFood_file_code());
			fodtoList.add(new FoodDTO(fovo, fivo, null));
		}
		
		return fodtoList;
	}

	@Override
	public int register(FoodDTO fdto) {
		
		int isOk = 1;
		
		if(fdto.getFilevo() != null) {
			isOk *= fidao.insert(fdto.getFilevo());			
		}
		fdto.getFoodvo().setFood_file_code(fdto.getFilevo().getFile_code());
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

	@Override
	public FoodDTO getDetail(int food_code) {

		FoodVO fvo = fdao.selectByFoodCode(food_code);
		FileVO fivo = fidao.selectByFileCode(fvo.getFood_file_code());
		
		return new FoodDTO(fvo, fivo, null);
	}



}
