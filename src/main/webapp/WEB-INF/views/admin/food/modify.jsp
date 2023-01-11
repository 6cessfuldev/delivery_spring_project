<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="../include/header.jsp"></jsp:include>
		
<style>
	#dinerForm>input[type="text"]{
		width:700px;
	}
</style>
		<form action="/admin/food/update">
			음식 코드 : <input type="text" name="food_code" value="${food.food_code}" readonly><br>
			음식 파일 코드 : <input type="text" name="food_file_code" value="${food.food_file_code}"><br>
			음식 이름 : <input type="text" name="food_name" value="${food.food_name}"> <br>
			음식 가격 : <input type="number" name="food_price" value="${food.food_price}"><br>
			음식 소개 : <input type="text" name="food_intro" value="${food.food_intro}"><br>
			음식 품절 여부 : <input type="text" name="food_state" value="${food.food_state}"><br>
			음식점 코드 : <input type="text" name="food_diner_code" value="${food.food_diner_code}" readonly><br>
			
			<button type="submit">등록</button>
		</form>	

<jsp:include page="../include/footer.jsp"></jsp:include>