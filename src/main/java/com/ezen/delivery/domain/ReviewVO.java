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
public class ReviewVO {

	private int review_code;
	private int review_order_code;
	private int review_diner_code;
	private String review_user_id;
	private String review_content;
	private int review_score;
//	private int review_taste_score;
//	private int review_amount_score;
//	private int review_delivery_score;
	private String review_reg_date;
	private String review_mod_date;
// 	private String review_boss_comment;
//  private int review_count;
}
