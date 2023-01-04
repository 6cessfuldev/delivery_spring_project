package com.ezen.delivery.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class FileVO {

	private int file_code;
	private String uuid;
	private String save_dir;
	private long file_size;
	private String file_name;
	private int file_type;
	private String file_reg_date;
}
