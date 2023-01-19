package com.ezen.delivery.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ezen.delivery.domain.PagingVO;
import com.ezen.delivery.domain.ReviewVO;
import com.ezen.delivery.domain.DinerDTO;
import com.ezen.delivery.domain.DinerVO;

public interface DinerDAO {
	
	List<DinerVO> selectListAll();

	List<DinerVO> selectListFirst();

	int insert(DinerVO dvo);

	List<DinerVO> selectList(PagingVO pvo);

	DinerVO selectDiner(int diner_code);

	List<DinerVO> selectListbyCate(PagingVO pvo);


	int update(DinerVO dvo);

	int delete(int diner_code);

	void selectReview(int diner_code);

//	DinerDTO selectDinerCount(int diner_code);

}
