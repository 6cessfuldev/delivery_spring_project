package com.ezen.delivery.service;

import java.util.List;

import com.ezen.delivery.domain.ChoiceVO;

public interface ChoiceService {

	public List<ChoiceVO> getList(int food_code);

	public int register(ChoiceVO cvo);
	
}
