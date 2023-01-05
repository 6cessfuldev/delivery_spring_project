package com.ezen.delivery.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class ReviewDTO {
	private ReviewVO rvo;
	private List<ReviewImgVO> fList;
}
