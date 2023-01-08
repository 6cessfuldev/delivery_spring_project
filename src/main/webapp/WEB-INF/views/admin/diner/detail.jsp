<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../include/header.jsp"></jsp:include>
음식점 >>> 
<a href="/admin/diner/modify?diner_code=${diner.diner_code}">수정</a>
<a href="/admin/diner/remove?diner_code=${diner.diner_code}">삭제</a>
<br><br>
    <caption>음식점 상세정보</caption>
<table>
    <tr>
        <th>음식점 코드</th>
        <td>${diner.diner_code}</td>
    </tr>
    <tr>
        <th>음식점 이미지</th>
        <td>${diner.diner_file_code}</td>
    </tr>
    <tr>
        <th>음식점 이름</th>
        <td>${diner.diner_name}</td>
    </tr>
    <tr>
        <th>음식점 주소</th>
        <td>${diner.diner_address}</td>
    </tr>
    <tr>
        <th>음식점 최소 결제 금액</th>
        <td>${diner.diner_min_pay}</td>
    </tr>
    <tr>
        <th>음식점 공지사항</th>
        <td>${diner.diner_notice}</td>
    </tr>
        <tr>
        <th>영업 시간</th>
        <td>${diner.diner_open_time} ~ ${diner.diner_close_time}</td>
    </tr>
    <tr>
        <th>결제수단</th>
        <td>${diner.diner_method_pay}</td>
    </tr>
    <tr>
        <th>상호명</th>
        <td>${diner.diner_business_name}</td>
    </tr>
        <tr>
        <th>사업자등록번호</th>
        <td>${diner.diner_company_num}</td>
    </tr>
    <tr>
        <th>카테고리</th>
        <td>${diner.diner_category}</td>
    </tr>
    <tr>
        <th>음식점 좌표</th>
        <td>${diner.diner_addr_lng}, ${diner.diner_addr_lat}</td>
    </tr>
</table>
<br>
<br>
음식 리스트 <a href="/admin/food/register?diner_code=${diner.diner_code}">추가</a>

	<div class="table-responsive">
        <table class="table table-striped table-sm">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">메뉴 이름</th>
              <th scope="col">메뉴 가격</th>
            </tr>
          </thead>
          <tbody>
         	<c:forEach items="${foodList}" var="food">
            <tr>
              <td>${food.food_code}</td>
              <td><a href="/admin/food/detail?food_code=${food.food_code}">${food.food_name}</a></td>
              <td>${food.food_price}</td>
            </tr>
            </c:forEach>
          </tbody>
       	</table>
     	</div>
	
<jsp:include page="../include/footer.jsp"></jsp:include>