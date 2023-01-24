<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="./include/header.jsp"></jsp:include>

<div class="table-responsive">
	<form id="search-form" action="/admin/order/search">
		 <span id="order_p3">정렬기준&nbsp;&nbsp;</span>
         <select name="order_sort" id="order_sort">
             <option value="user_email" selected="selected">이메일</option>
             <option value="date">주문번호</option>
             <option value="diner_name">음식점</option>
         </select>
         <input type="text" name="keyword" id="keyword" value="">
         <input type="submit" id="submit" value="검색">
	</form>
	<table class="table table-striped table-sm">
	    <thead>
	        <tr>
	            <th scope="col">이메일</th>
	            <th scope="col">주문번호</th>
	            <th scope="col">음식점</th>
	        </tr>
	    </thead>
	    <tbody>
	        <c:forEach items="${userOrderHistoryList}" var="uohList">
	            <tr>
	                <td>${uohList[0].user_email }</td>
	                <td><a href="/admin/order/detail?order_code=${uohList[0].order_code }">${uohList[0].order_code }</a></td>
	                <td>${uohList[0].diner_name }</td>
	            </tr>
	        </c:forEach>
	    </tbody>
	</table>	
</div>

<script type="text/javascript" src="/resources/js/orderHistory.js"></script>

<jsp:include page="./include/footer.jsp"></jsp:include>