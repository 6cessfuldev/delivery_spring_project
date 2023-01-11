package com.ezen.delivery.repository;

import com.ezen.delivery.domain.FileVO;

public interface FileDAO {

	int insert(FileVO filevo);

	FileVO selectByFileCode(int food_file_code);

	int update(FileVO fivo);

	int delete(int diner_file_code);

}
