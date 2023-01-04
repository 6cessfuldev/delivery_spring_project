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
public class ReviewImgVO {

	private int review_img_code;
	private int review_img_file_code;
	private String review_img_uuid;
	private String review_img_save_dir;
	private long review_img_size;
	private String review_img_name;
	private int review_img_type;
	private String review_img_reg_date;
}
