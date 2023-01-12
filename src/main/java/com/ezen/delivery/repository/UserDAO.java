package com.ezen.delivery.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ezen.delivery.domain.UserVO;

public interface UserDAO {

	UserVO getUser(String user_id);

	boolean insertUser(UserVO uvo);

	boolean insertNaverUser(UserVO naverUser);
	
	int selectCntById(String user_id);

	List<String> selectAllId();

	UserVO findIdByEmail(String user_email);

	int updatePw(@Param("getEmail") String getEmail, @Param("new_pw") String new_pw);

	UserVO selectUserOne(String user_id);
	
//	int selectUserOne(UserVO uvo);

	int updateUser(@Param("user_id") String user_id, @Param("new_pw") String new_pw, @Param("new_phone") String new_phone);

	int updateUser(UserVO uvo);
	
	int selectCntByEmail(String user_email);

	UserVO getUserPw(String getEmail);

	int deleteUser(String user_id);

	List<UserVO> selectList();





}
