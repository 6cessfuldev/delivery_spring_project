<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link type="text/css" rel="stylesheet" href="/resources/css/myOrderList.css">

<div class="category">
	<ul class="nav justify-content-center">
		<li class="nav-item">
			<div class="category-btn" onclick='location.href ="/order/myOrderList";'>주문/결제 조회</div>
		</li>
		<li class="nav-item">
			<div class="category-btn" onclick='location.href ="/dibs/myDibsList";' id="cate-ff">찜목록</div>
		</li>
		<li class="nav-item">
			<div class="category-btn" id="cate-hh">리뷰 관리</div>
		</li>
		<li class="nav-item">
			<div class="category-btn" id="cate-pp">적립금 내역</div>
		</li>
		<li class="nav-item">
			<div class="category-btn" onclick='location.href ="/member/detail_userInfo";'>회원 정보</div>
		</li>
	</ul>
</div>