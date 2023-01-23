<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<title>먹어요</title>
<jsp:include page="../include/header.jsp"></jsp:include>
<link type="text/css" rel="stylesheet" href="/resources/css/myOrderList.css">

<jsp:include page="../include/myPageHeader.jsp"></jsp:include>


<main>
	<div class="row justify-content-center pt-5">
	
	<c:forEach items="${userOrderHistoryList }" var="uohList">
		<fmt:parseDate value="${fn:substring(uohList[0].order_code, 0, 8)}" var="dateValue" pattern="yyyyMMdd"/>
		<div class="col-sm-8 card-wrap">
		  <h5 class="card-head"><fmt:formatDate value="${dateValue }" pattern="yyyy.MM.dd"/> 주문</h5>
		  	<div class=col-md-2>
			     <div class="btn border-secondary mb-2 review-btn" style="--bs-border-opacity: .5;" onclick="location.href='/diner/review?order_code=${uohList[0].order_code}';">리뷰 작성하기</div>
			     <div class="btn border-secondary review-btn" style="--bs-border-opacity: .5;">교환 반품 신청</div>
			</div>
			<div class="card mb-3">
				<div class=dinerName>😋 ${uohList[0].diner_name}</div>
				<!-- 여러 메뉴 주문 시 반복 -->
			   <c:forEach items="${uohList}" var="ohList">
					<div class="row g-0 align-items-center">
					   <div class="col-md-2 ">
					     <img src="/upload/${fn:replace(ohList.fivo.file_save_dir, '\\','/')}/${ohList.fivo.file_uuid}_${ohList.fivo.file_name}" class="img-fluid rounded-start" alt="...">
					   </div>
					   <div class="col-md-8">
					     <div class="card-body">
					       <h5 class="card-title">${ohList.food_name}</h5>
					       <fmt:formatNumber value="${ohList.total_price}" var="price" pattern="#,###" />
					       <p class="card-text">${price } ＊ ${ohList.order_count }개</p>
					       <p class="card-text"><small class="text-muted">옵션 : ${ohList.choice_contents }</small></p>
					     </div>
					   </div>
					</div>
			    </c:forEach>
			</div>
		  </div>
	</c:forEach>
	
	</div>
	

</main>

<jsp:include page="../include/footer2.jsp"></jsp:include>