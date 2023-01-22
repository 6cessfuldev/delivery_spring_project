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

	List<DinerVO> selectListByDistance(PagingVO pvo);
	
	List<DinerVO> selectListByReview(PagingVO pvo);
	
	List<DinerVO> selectListByScore(PagingVO pvo);

	DinerVO selectDiner(int diner_code);

	int update(DinerVO dvo);

	int delete(int diner_code);

	List<DinerVO> selectReview(int diner_code);

	List<DinerVO> selectCathListByDistance(PagingVO pvo);

	List<DinerVO> selectCathListByReview(PagingVO pvo);

	List<DinerVO> selectCathListByScore(PagingVO pvo);

	int updateScore(DinerVO dvo);
	
	


}
