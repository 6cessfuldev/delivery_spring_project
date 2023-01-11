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
	private String file_uuid;
	private String file_save_dir;
	private long file_size;
	private String file_name;
	private int file_type;
	private String file_reg_date;
}
