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

	@Override
	public int register(ChoiceVO cvo) {
		return cdao.insertChoice(cvo);
	}

	@Override
	public ChoiceVO getChoice(int choice_code) {
		return cdao.selectOne(choice_code);
	}

	@Override
	public int remove(int choice_code) {
		return cdao.deleteChoice(choice_code);
	}

	@Override
	public int update(ChoiceVO cvo) {
		return cdao.updateChoice(cvo);
	}

}
