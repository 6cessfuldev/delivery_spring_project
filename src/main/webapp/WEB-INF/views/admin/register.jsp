<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="../include/header.jsp"></jsp:include>

	<form action="/admin/diner/insert" id="dinerForm">
		음식점 코드 : 자동 입력<br>
		음식점 파일 코드 : 1로 자동 입력<br>
		음식점 이름 : <input type="text" name="diner_name"> <br>
		음식점 주소 : <input type="text" id ="addr" name="diner_address"><br>
		최소 결제 금액 : <input type="text" name="diner_min_pay" value="1000"><br>
		공지사항 : <textarea rows="3" cols="30" name="diner_notice"></textarea><br>
		오픈 시간 : <input type="text" name="diner_open_time"><br>
		닫는 시간 : <input type="text" name="diner_close_time"><br>
		결제 수단 : <input type="text" name="diner_method_pay"><br>
		상호명 : <input type="text" name="diner_business_name"><br>
		사업자등록번호 : <input type="text" name="diner_company_num"><br>
		카테고리 : 
		<input type="checkbox" name="category" value="aa">1인분주문
		<input type="checkbox" name="category" value="ff">프랜차이즈
		<input type="checkbox" name="category" value="hh">치킨
		<input type="checkbox" name="category" value="pp">피자/양식
		<input type="checkbox" name="category" value="cc">중국집
		<input type="checkbox" name="category" value="kk">한식
		<input type="checkbox" name="category" value="jj">일식/돈까스
		<input type="checkbox" name="category" value="mm">족발/보쌈
		<input type="checkbox" name="category" value="nn">야식
		<input type="checkbox" name="category" value="tt">분식
		<input type="checkbox" name="category" value="dd">카페/디저트
		<input type="checkbox" name="category" value="ss">편의점/마트
				
		<br><br>
		(음식점 위도 & 경도는 자동 입력)
		<input type="text" id="diner_addr_lng" name="diner_addr_lng" hidden><br>
		<input type="text" id="diner_addr_lat" name="diner_addr_lat" hidden><br>
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