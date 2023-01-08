<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="../include/header.jsp"></jsp:include>
	
	<h1>${diner.diner_name} 메뉴 추가</h1>
	
		<form action="/admin/food/insert" id="Form" enctype="multipart/form-data">
			음식 코드 : 자동 입력<br>
			음식 이미지 파일 : 1로 자동 입력<input type="file" name="file"><br>
			음식 이름 : <input type="text" name="food_name"> <br>
			음식 가격 : <input type="number" name="food_price"><br>
			음식 소개 : <input type="text" name="food_intro"><br>
			음식 품절 여부 : <input type="text" name="food_state" value="N"><br>
			음식점 코드 : <input type="text" name="food_diner_code" value="${diner.diner_code}" readonly><br>
			
			<button type="submit">등록</button>
		</form>	


<jsp:include page="../include/footer.jsp"></jsp:include>