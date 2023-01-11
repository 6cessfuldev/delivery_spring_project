package com.ezen.delivery.service;

import java.util.List;

import com.ezen.delivery.domain.OptionDTO;

public interface OptionService {

	public List<OptionDTO> getList(int food_code);
	
}
