package com.ezen.delivery.repository;

import com.ezen.delivery.domain.UserVO;

public interface UserDAO {

	UserVO getUser(String user_email);

	int insertUser(UserVO uvo);


}
