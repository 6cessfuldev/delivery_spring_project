package com.ezen.delivery.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ezen.delivery.domain.ChoiceVO;
import com.ezen.delivery.repository.ChoiceDAO;

@Service
public class ChoiceServiceImpl implements ChoiceService {

	@Inject
	private ChoiceDAO cdao;
	
	@Override
	public List<ChoiceVO> getList(int food_code) {
		return cdao.selectList(food_code);
	}

}
