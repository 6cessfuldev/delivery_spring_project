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

<button type="button" class="addChoiceBtn">옵션추가</button>
<br>
<br>

<div id="addChoiceBox"></div>


<script>
    $('.addChoiceBtn').click(function(){
        const box = document.getElementById('addChoiceBox');
        const newP = document.createElement('p');
        newP.innerHTML = `<table>`;
        newP.innerHTML += `<tr>`;
        newP.innerHTML += `<th colspan="3">choice_title</th>`;
        newP.innerHTML += `</tr>`;
        newP.innerHTML += `<tr>`;
        newP.innerHTML += `<td><input type="text" id="choice_title" placeholder="옵션 제목"></td>`;
        newP.innerHTML += `<td><input type="number" placeholder="옵션 개수 입력"></td>`;
        newP.innerHTML += `<td><input type="button" class="addSubBtn" value="생성"></td>`;
        newP.innerHTML += `</tr>`;
        newP.innerHTML += `</table>`;


        newP.innerHTML += `<table>`;
        newP.innerHTML += `<tr>`;
        newP.innerHTML += `<th hidden>choice_code</th>`;
        newP.innerHTML += `<th>subchoice_content</th>`;
        newP.innerHTML += `<th>subchoice_price</th>`;
        newP.innerHTML += `</tr>`;
        newP.innerHTML += `<tr>`;
        newP.innerHTML += `<td hidden><input type="text"></td>`;
        newP.innerHTML += `<td><input type="text" placeholder="옵션 내용"></td>`;
        newP.innerHTML += `<td><input type="text" placeholder="옵션 가격"></td>`;
        newP.innerHTML += `</tr>`;
        newP.innerHTML += `</table>`;



    
     
        box.appendChild(newP);
    })

    

</script>



	
		
		
		
	
	
		
		
		
	

<form action="">

</form>
	
<jsp:include page="../include/footer.jsp"></jsp:include>