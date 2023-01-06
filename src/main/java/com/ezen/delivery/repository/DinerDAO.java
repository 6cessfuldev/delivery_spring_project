package com.ezen.delivery.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ezen.delivery.domain.PagingVO;
import com.ezen.delivery.domain.DinerVO;

public interface DinerDAO {

	List<DinerVO> selectListFirst();

	List<DinerVO> selectTenDinerByCategory(PagingVO dsvo);

	int insert(DinerVO dvo);

	List<DinerVO> selectList(PagingVO pvo);

	DinerVO selectDiner(int diner_code);

	List<DinerVO> selectListbyCate(PagingVO pvo);

	List<DinerVO> selectListAll();

}
