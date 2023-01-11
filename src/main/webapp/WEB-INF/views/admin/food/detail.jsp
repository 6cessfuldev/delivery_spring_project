<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<jsp:include page="../include/header.jsp"></jsp:include>
음식점 >>> 음식 >>>
<a href="/admin/food/modify?food_code=${food.food_code}">수정</a>
<a href="/admin/food/remove?food_code=${food.food_code}">삭제</a>
<br><br>
    <caption>음식점 상세정보</caption><br>
    <img src="/upload/${fn:replace(file.file_save_dir, '\\','/')}/${file.file_uuid}_${file.file_name}" width="200px">
<table>
    <tr>
        <th>음식 코드</th>
        <td>${food.food_code}</td>
    </tr>
    <tr>
        <th>음식 이름</th>
        <td>${food.food_name}</td>
    </tr>
    <tr>
        <th>음식 가격</th>
        <td>${food.food_price}</td>
    </tr>
    <tr>
        <th>음식 소개</th>
        <td>${food.food_intro}</td>
    </tr>
    <tr>
        <th>음식 등록일</th>
        <td>${food.food_reg_date}</td>
    </tr>
        <tr>
        <th>품절 여부</th>
        <td>${food.food_state}</td>
    </tr>
	<tr>
        <th>음식점 코드</th>
        <td>${food.food_diner_code}</td>
    </tr>
</table>
<br>
<br>
	
<jsp:include page="../include/footer.jsp"></jsp:include>