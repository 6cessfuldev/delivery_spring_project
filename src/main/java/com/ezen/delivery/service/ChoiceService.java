package com.ezen.delivery.service;

import java.util.List;

import com.ezen.delivery.domain.ChoiceDTO;

public interface ChoiceService {

	public List<ChoiceDTO> getList(int food_code);
	
}
