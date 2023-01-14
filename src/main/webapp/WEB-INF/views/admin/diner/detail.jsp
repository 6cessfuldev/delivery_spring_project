<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<jsp:include page="../include/header.jsp"></jsp:include>
음식점 >>> 
<a href="/admin/diner/modify?diner_code=${diner.diner_code}">수정</a>
<a href="/admin/diner/remove?diner_code=${diner.diner_code}">삭제</a>
<br><br>
    <caption>음식점 상세정보</caption>
    <img src="/upload/${fn:replace(file.file_save_dir, '\\','/')}/${file.file_uuid}_${file.file_name}" width="100px">
<table>
    <tr>
        <th>음식점 코드</th>
        <td>${diner.diner_code}</td>
    </tr>
    <tr>
        <th>음식점 파일 코드</th>
        <td>${file_code}</td>
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
    	<th>배달비</th>
    	<td>${diner.diner_delivery_fee}</td>	
    </tr>
    <tr>
        <th>카테고리</th>
        <td>
        <c:if test="${fn:contains(diner.diner_category, 'aa')}">1인분 주문 </c:if> 
        <c:if test="${fn:contains(diner.diner_category, 'ff')}">프랜차이즈 </c:if> 
        <c:if test="${fn:contains(diner.diner_category, 'hh')}">치킨 </c:if> 
        <c:if test="${fn:contains(diner.diner_category, 'pp')}">피자/양식 </c:if> 
        <c:if test="${fn:contains(diner.diner_category, 'cc')}">중국집 </c:if> 
        <c:if test="${fn:contains(diner.diner_category, 'kk')}">한식 </c:if> 
        <c:if test="${fn:contains(diner.diner_category, 'jj')}">일식/돈까스 </c:if> 
        <c:if test="${fn:contains(diner.diner_category, 'mm')}">족발/보쌈 </c:if> 
        <c:if test="${fn:contains(diner.diner_category, 'nn')}">야식 </c:if> 
        <c:if test="${fn:contains(diner.diner_category, 'tt')}">분식 </c:if> 
        <c:if test="${fn:contains(diner.diner_category, 'dd')}">카페/디저트 </c:if> 
        <c:if test="${fn:contains(diner.diner_category, 'ss')}">편의점/마트 </c:if> 
    	</td>
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
         	<c:forEach items="${foodList}" var="list">
            <tr>
              <td>${list.foodvo.food_code}</td>
              <td><a href="/admin/food/detail?food_code=${list.foodvo.food_code}">${list.foodvo.food_name}</a></td>
              <td>${list.foodvo.food_price}</td>
            </tr>
            </c:forEach>
          </tbody>
       	</table>
     	</div>
	
<jsp:include page="../include/footer.jsp"></jsp:include>