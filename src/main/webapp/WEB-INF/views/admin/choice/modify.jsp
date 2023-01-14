<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<title>관리자 페이지</title>

<jsp:include page="../include/header.jsp"></jsp:include>

<h1>${food.food_name} 옵션 수정</h1>

	<form action="/admin/choice/update">
		옵션 코드 : <input type="text" name="choice_code" value="${choice.choice_code }" readonly="readonly"><br>
		옵션 내용 : <input type="text" name="choice_content" value="${choice.choice_content }"><br>
		옵션 가격 : <input type="number" name="choice_price" value="${choice.choice_price }"><br>
		음식 코드 : <input type="text" name="food_code" value="${food.food_code }" readonly="readonly"><br>
	
		<button type="submit">수정</button>
	</form>

<jsp:include page="../include/footer.jsp"></jsp:include>