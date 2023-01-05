<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="./include/header.jsp"></jsp:include>
<link type="text/css" rel="stylesheet" href="/resources/css/index.css">
<style>
	
	

</style>

	<div class="header-box">
		<h1 >"어디로 배달해 드릴까요?"</h1> <br>
		<div class="search-box">
			<button type="button" class="gps-btn">
				<svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" fill="red" class="bi bi-geo-alt-fill" viewBox="0 0 16 16">
  					<path d="M8 16s6-5.686 6-10A6 6 0 0 0 2 6c0 4.314 6 10 6 10zm0-7a3 3 0 1 1 0-6 3 3 0 0 1 0 6z"/>
				</svg>
			</button>
			<div class="search-input-box">
				<div>
				<input id="search-input" type="text" placeholder="건물명, 도로명, 지번으로 검색하세요."></input>
					<div class="search-extention">
					
					</div>
				</div>
				<button id="search-btn" type="button">검색</button>
			</div>
		</div>
		
	</div>
	
	<main>
		<div class="d-flex justify-content-center">
			<div class='card-box event'>
				<p>event</p>
			</div>
			<div class='card-box all'>

				<a href="/diner/list"><p>전체보기</p></a>

			</div>
			<div class='card-box forone'>
				<p>1인분 주문</p>
			</div>
			<div class='card-box franchise'>프랜차이즈</div>
		</div>
		<div class="d-flex justify-content-center">
			<div class='card-box chicken'>치킨</div>
			<div class='card-box pizza'>피자/양식</div>
			<div class='card-box chinese'>중국집</div>
			<div class='card-box korean'>한식</div>
		</div>
		<div class="d-flex justify-content-center">
			<div class='card-box japanese'>일식/돈까스</div>
			<div class='card-box pork'>족발/보쌈</div>
			<div class='card-box nightfood'>야식</div>
			<div class='card-box kimbob'>분식</div>
		</div>
		<div class="d-flex justify-content-center">
			<div class='card-box dessert'>카페/디저트</div>
			<div class='card-box mart'>편의점/마트</div>
		</div>
	</main>

	<div class="hidden-form">

		<form id="addr-form" action="/diner/search" method="get">
			<input type="text" id="jibunAddr" name="jibunAddr" value="" hidden>
			<input type="text" id="x" name="lng" value="" hidden>
			<input type="text" id="y" name="lat" value="" hidden>
			<input type="text" name="category" value="0" hidden>
		</form>
	</div>

	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=e8b552c46357c215f64b284e4da814a9&libraries=services"></script>
	<script>
	
	let exten = $(".search-extention");
	exten.hide();
	
	var options = {
			size: 5
	}

	function searchAdrr(){
		let keyword = $("#search-input").val();
		
		if(keyword=="") {
			exten.hide();
			exten.html("");
		}
			
			
		console.log(keyword);
		if (!checkSearchedWord(keyword)) {
			return ;
		}
		
		if(keyword.length<3) return;
		
		$("#keyword").val(keyword);
		
		var places = new kakao.maps.services.Places();
		
		var callback = function(result, status) {
			if (status === kakao.maps.services.Status.OK) {
				addSearchBox(result);
			}
		};
		places.keywordSearch(keyword, callback, options);
	}
	
	$("#search-btn").click(function(){
		searchAdrr();
	})

	$("#search-input").on("click keyup", function(){
		searchAdrr();
	})
	
	function addSearchBox(result){	
		if(result==null) {
			console.log("data null");
			return;
		}
		
		if(result.length>0){
			exten.html("");
			exten.show();
			for(let i=0; i<result.length; i++ ){

				let addr = result[i];
				let classname = "addr"+i;
				
				let div = $('<div>').prop({className: classname});
				div.addClass('addr');
				div.click(function(){
					console.log(addr);
					exten.hide();
					$("#search-input").val(addr.address_name);	
					$("#jibunAddr").val(addr.address_name);
					$("#x").val(addr.x);
					$("#y").val(addr.y);
					$("#addr-form").submit();	
				})
				
				exten.append(div);
				div.append(
					$('<div>').prop({
						className: 'jibun-addr',
						innerHTML: addr.address_name
					})
				);
				div.append(
					$('<div>').prop({
						className: 'doro-addr',
						innerHTML: (addr.road_address_name!="" ? "[도로명]"+addr.road_address_name:" ")
					})
				);
			}
		}
	}

	function checkSearchedWord(keyword){
	if(keyword.length >0){
		//특수문자 제거
		var expText = /[%=><]/ ;
		if(expText.test(keyword) == true){
			alert("특수문자를 입력 할수 없습니다.") ;
			return false;
		}
		
		//특정문자열(sql예약어의 앞뒤공백포함) 제거
		var sqlArray = new Array(
			//sql 예약어
			"OR", "SELECT", "INSERT", "DELETE", "UPDATE", "CREATE", "DROP", "EXEC",
					"UNION",  "FETCH", "DECLARE", "TRUNCATE" 
		);
		
		var regex;
		for(var i=0; i<sqlArray.length; i++){
			regex = new RegExp( sqlArray[i] ,"gi") ;
			
			if (regex.test(keyword) ) {
				alert("\"" + sqlArray[i]+"\"와(과) 같은 특정문자로 검색할 수 없습니다.");
				return false;
			}
		}
	}
	return true ;
	}

	</script>

	<!-- <script src="/resources/js/index.js"></script> -->
<jsp:include page="./include/footer2.jsp"></jsp:include>