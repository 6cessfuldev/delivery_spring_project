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
        <td>${food.diner_code}</td>
    </tr>
</table>
<br>
<br>

음식 옵션 <a href="/admin/choice/register?food_code=${food.food_code }"><button type="button" class="addChoiceBtn">추가</button></a>
<br>
<br>

<div class="table-responsive">
        <table class="table table-striped table-sm">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">옵션 내용</th>
              <th scope="col">옵션 가격</th>
              <th scope="col"></th>
              <th scope="col"></th>
            </tr>
          </thead>
          <tbody>
         	<c:forEach items="${choiceList}" var="cvo">
            <tr>
              <td>${cvo.choice_code}</td>
              <td>${cvo.choice_content}</td>
              <td>${cvo.choice_price}</td>
              <td><a href="/admin/choice/modify?choice_code=${cvo.choice_code }">수정</a></td>
              <td><a href="/admin/choice/remove?choice_code=${cvo.choice_code }">삭제</a></td>
            </tr>
            </c:forEach>
          </tbody>
       	</table>
     	</div>









<!-- <div id="addChoiceBox"></div> -->


<script>
 /*    $('.addChoiceBtn').click(function(){
    	
        const box = document.getElementById('addChoiceBox');
        const newP = document.createElement('p');
        
        newP.innerHTML = `<table><tr>`;
        newP.innerHTML += `<td><input type="text" id="subchoice_content_" placeholder="옵션 내용"></td>`;
        newP.innerHTML += `<td><input type="number" id="subchoice_price" placeholder="옵션 가격"></td>`;
        newP.innerHTML += `<td></td>`;
        newP.innerHTML += `<td><input type="button" class="addSubBtn" value="추가"></td>`;
        newP.innerHTML += `</tr></table><br>`;

        
        box.appendChild(newP);
        
    });
     */
    
   

</script>



	
		
		
		
	
	
		
		
		
	

<form action="">

</form>
	
<jsp:include page="../include/footer.jsp"></jsp:include>