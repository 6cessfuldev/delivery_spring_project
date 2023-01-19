<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>먹어요</title>
<jsp:include page="../include/header.jsp"></jsp:include>
<link type="text/css" rel="stylesheet" href="/resources/css/myDibsList.css">

<div class="category">
	<ul class="nav justify-content-center">
		<li class="nav-item">
			<div class="category-btn" onclick='location.href ="/order/myOrderList";'>주문/결제 조회</div>
		</li>
		<li class="nav-item">
			<div class="category-btn" id="cate-ff">찜목록</div>
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


<main class="py-5">
	<section class="d-flex justify-content-center">
		<div class="wrapper">
			<h2>찜 리스트</h2>
			
			<div class="card mb-3" style="max-width: 900px;">
			  <div class="row g-0 align-items-center">
			    <div class="col-md-2 text-center">
			      <img src="/resources/source/fried.PNG" class="img-fluid rounded-start" alt="...">
			    </div>
			    <div class="col-md-8">
			      <div class="card-body">
			        <h5 class="card-title">후라이드 참 잘하는 집</h5>
			        <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
			      </div>
			    </div>
			    <div class="col-md-2">
			    	<div class="btn border-secondary">장바구니 추가</div>
			    	<div class="btn border-secondary">삭제</div>
			    </div>
			  </div>
			</div>
						
		</div>
	</section>
	

</main>

<jsp:include page="../include/footer2.jsp"></jsp:include>

<script src="/resources/js/detail_userInfo.js"></script>