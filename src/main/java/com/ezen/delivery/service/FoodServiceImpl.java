package com.ezen.delivery.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ezen.delivery.domain.DinerVO;
import com.ezen.delivery.domain.FoodVO;
import com.ezen.delivery.repository.FoodDAO;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class FoodServiceImpl implements FoodService {

	@Inject
	FoodDAO fdao;
	
	
	@Override
	public List<FoodVO> getFoodByDinerCode(int diner_code) {
		
		return fdao.selectDinerList(diner_code);
	}



}
