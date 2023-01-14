package com.ezen.delivery.repository;

import java.util.List;

import com.ezen.delivery.domain.ChoiceVO;

public interface ChoiceDAO {

	List<ChoiceVO> selectList(int food_code);


	int insertChoice(ChoiceVO cvo);

	ChoiceVO selectOne(int choice_code);

	int deleteChoice(int choice_code);

	int updateChoice(ChoiceVO cvo);




}
