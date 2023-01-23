<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<title>먹어요</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">

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
			
				<c:forEach items="${ddtoList }" var="dib">
			<div class="card mb-3" style="max-width: 900px;">
				  <div class="row g-0 align-items-center">
				    <div class="col-md-2 text-center">
				      <img src="/resources/source/fried.PNG" class="img-fluid rounded-start" alt="...">
				    </div>
				    <div class="col-md-8">
				      <div class="card-body">
				        <h5 class="card-title">${dib.dvo.diner_name }</h5>
				      	<div class="diner-infos">
						<p class="score">
						<c:choose>
							<c:when test="${dib.dvo.diner_score_avg == 5.0}">
								<i class="bi bi-star-fill"></i>
								<i class="bi bi-star-fill"></i>
								<i class="bi bi-star-fill"></i>
								<i class="bi bi-star-fill"></i>
								<i class="bi bi-star-fill"></i>
							</c:when>
							<c:when test="${dib.dvo.diner_score_avg >= 4.5}">
								<i class="bi bi-star-fill"></i>
								<i class="bi bi-star-fill"></i>
								<i class="bi bi-star-fill"></i>
								<i class="bi bi-star-fill"></i>
								<i class="bi bi-star-half"></i>
							</c:when>
							<c:when test="${dib.dvo.diner_score_avg >= 4.0}">
								<i class="bi bi-star-fill"></i>
								<i class="bi bi-star-fill"></i>
								<i class="bi bi-star-fill"></i>
								<i class="bi bi-star-fill"></i>
								<i class="bi bi-star"></i>
							</c:when>
							<c:when test="${dib.dvo.diner_score_avg >= 3.5}">
								<i class="bi bi-star-fill"></i>
								<i class="bi bi-star-fill"></i>
								<i class="bi bi-star-fill"></i>
								<i class="bi bi-star-half"></i>
								<i class="bi bi-star"></i>
							</c:when>
							<c:when test="${dib.dvo.diner_score_avg >= 3.0}">
								<i class="bi bi-star-fill"></i>
								<i class="bi bi-star-fill"></i>
								<i class="bi bi-star-fill"></i>
								<i class="bi bi-star"></i>
								<i class="bi bi-star"></i>
							</c:when>
							<c:when test="${dib.dvo.diner_score_avg >= 2.5}">
								<i class="bi bi-star-fill"></i>
								<i class="bi bi-star-fill"></i>
								<i class="bi bi-star-half"></i>
								<i class="bi bi-star"></i>
								<i class="bi bi-star"></i>
							</c:when>
							<c:when test="${dib.dvo.diner_score_avg >= 2.0}">
								<i class="bi bi-star-fill"></i>
								<i class="bi bi-star-fill"></i>
								<i class="bi bi-star"></i>
								<i class="bi bi-star"></i>
								<i class="bi bi-star"></i>
							</c:when>
							<c:when test="${dib.dvo.diner_score_avg >= 1.5}">
								<i class="bi bi-star-fill"></i>
								<i class="bi bi-star-half"></i>
								<i class="bi bi-star"></i>
								<i class="bi bi-star"></i>
								<i class="bi bi-star"></i>
							</c:when>
							<c:when test="${dib.dvo.diner_score_avg >= 1.0}">
								<i class="bi bi-star-fill"></i>
								<i class="bi bi-star"></i>
								<i class="bi bi-star"></i>
								<i class="bi bi-star"></i>
								<i class="bi bi-star"></i>
							</c:when>
							<c:otherwise>
								등록된 리뷰가 없습니다.
							</c:otherwise>
						</c:choose>
						</p>
						<fmt:formatNumber value="${dib.dvo.diner_min_pay}" var="minPay" pattern="#,###" />
						<p>최소주문금액 ${minPay}원</p>
						<p>결제 <span>${dib.dvo.diner_method_pay}</span></p>
					</div>
				      </div>
				    </div>
				    <div class="col-md-2">
				    	<div class="btn border-secondary">리뷰보기</div>
				    	<div class="btn border-secondary">삭제</div>
				    </div>
				  </div>
			</div>
				</c:forEach>
						
		</div>
	</section>
	

</main>

<jsp:include page="../include/footer2.jsp"></jsp:include>