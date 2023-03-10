package com.ezen.delivery.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ezen.delivery.domain.AdminPagingVO;
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
		log.info(fovoList.toString());
		
		for (FoodVO fovo : fovoList) {
			FileVO fivo = fidao.selectByFileCode(fovo.getFile_code());
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
		fdto.getFoodvo().setFile_code(fdto.getFilevo().getFile_code());
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
		FileVO fivo = fidao.selectByFileCode(fvo.getFile_code());
		
		return new FoodDTO(fvo, fivo, null);
	}

	@Override
	public int totalCount(int diner_code) {
		return fdao.selectTotalCount(diner_code);
	}

	@Override
	public List<FoodDTO> getListByDinerCodeWithPaging(int diner_code, AdminPagingVO pgvo) {
		
		List<FoodDTO> fdtoList = new ArrayList<FoodDTO>();
		
		List<FoodVO> fvoList = fdao.selectListByDinerCodeWithPaging(diner_code, pgvo);
		for (FoodVO foodVO : fvoList) {
			FileVO fivo = fidao.selectByFileCode(foodVO.getFile_code());
			fdtoList.add(new FoodDTO(foodVO, fivo, null));
		}
		return fdtoList;
	}



}
