<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="../include/header.jsp"></jsp:include>

	<form action="/admin/insert/food" id="foodForm">
		음식 코드 : 자동 입력<br>
		음식 파일 코드 : 1로 자동 입력<br>
		음식점 코드 : <input type="text" name="diner_open_time"><br>
		음식 가격 : <input type="text" name="diner_name"> <br>
		음식 소개 : <input type="text" id ="addr" name="diner_address"><br>
		음식 등록일 : <input type="text" name="diner_min_pay" value="1000"><br>
		음식 품절 여부 : <textarea rows="3" cols="30" name="diner_notice"></textarea><br>

		<button type="button" id="dinerInputBtn">등록</button>
	</form>	
	
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=e8b552c46357c215f64b284e4da814a9&libraries=services"></script>

<script>

 	$("#dinerInputBtn").click(function(){
		console.log("clicked");
		
		
		
		var geocoder = new kakao.maps.services.Geocoder();
		var callback = function(result, status) {
		    if (status === kakao.maps.services.Status.OK) {
		    	console.log(result[0]);
		        $("#diner_addr_lng").val(result[0].x);
		        $("#diner_addr_lat").val(result[0].y);
		        $("#dinerForm").submit();
		    }else{
		    	alert("주소 입력 에러");
		    }
		};
		var addr = $("#addr").val();
		geocoder.addressSearch(addr, callback);
	})
</script>


<jsp:include page="../include/footer2.jsp"></jsp:include>