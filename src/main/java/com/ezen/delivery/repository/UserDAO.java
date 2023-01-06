package com.ezen.delivery.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ezen.delivery.domain.UserVO;

public interface UserDAO {

	UserVO getUser(String user_email);

	int insertUser(UserVO uvo);

//	UserVO selectCntById(String user_id);

	int selectId(String user_id);

	List<String> selectAllId();

	UserVO findIdByEmail(String user_email);

	int updatePw(@Param("getEmail") String getEmail, @Param("new_pw") String new_pw);

}
