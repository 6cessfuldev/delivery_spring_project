package com.ezen.delivery.service;

import java.util.List;

import javax.inject.Inject;

import com.ezen.delivery.domain.ChoiceDTO;
import com.ezen.delivery.repository.ChoiceDAO;

public class ChoiceServiceImpl implements ChoiceService {

	@Inject
	private ChoiceDAO cdao;
	
	@Override
	public List<ChoiceDTO> getList(int food_code) {
		return null;
	}

}
