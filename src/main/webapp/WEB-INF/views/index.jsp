<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<title>먹어요</title>
<jsp:include page="./include/header.jsp"></jsp:include>
<link type="text/css" rel="stylesheet" href="/resources/css/index.css">
<style>
	
	

</style>

	<div class="header-box">
		<h1 >"어디로 배달해 드릴까요?"</h1> <br>
		<div class="search-box">
			<button type="button" class="gps-btn" onclick="getLocation()">
				<svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" fill="red" class="bi bi-geo-alt-fill" viewBox="0 0 16 16">
  					<path d="M8 16s6-5.686 6-10A6 6 0 0 0 2 6c0 4.314 6 10 6 10zm0-7a3 3 0 1 1 0-6 3 3 0 0 1 0 6z"/>
				</svg>
			</button>
			<div class="search-input-box">
				<div>
				<input id="search-input" type="text" placeholder="건물명, 도로명, 지번으로 검색하세요." value="${sessionScope.pvo.jibunAddr}"></input>
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
				<!-- <img alt="" src="/resources/source/event.gif" width="180px"> -->
			</div>
			<div class='card-box all' onclick="clickCategory('all')">전체보기</div>
			<div class='card-box forone' onclick="clickCategory('aa')">1인분 주문</div>
			<div class='card-box franchise' onclick="clickCategory('ff')">프랜차이즈</div>
		</div>
		<div class="d-flex justify-content-center">
			<div class='card-box chicken' onclick="clickCategory('hh')">치킨</div>
			<div class='card-box pizza' onclick="clickCategory('pp')">피자/양식</div>
			<div class='card-box chinese' onclick="clickCategory('cc')">중국집</div>
			<div class='card-box korean' onclick="clickCategory('kk')">한식</div>
		</div>
		<div class="d-flex justify-content-center">
			<div class='card-box japanese' onclick="clickCategory('jj')">일식/돈까스</div>
			<div class='card-box pork' onclick="clickCategory('mm')">족발/보쌈</div>
			<div class='card-box nightfood' onclick="clickCategory('nn')">야식</div>
			<div class='card-box kimbob' onclick="clickCategory('tt')">분식</div>
		</div>
		<div class="d-flex justify-content-center">
			<div class='card-box dessert' onclick="clickCategory('dd')">카페/디저트</div>
			<div class='card-box mart' onclick="clickCategory('ss')">편의점/마트</div>
		</div>
	</main>

	<div class="hidden-form">

		<form id="addr-form" action="/diner/search" method="get">
			<input type="text" id="jibunAddr" name="jibunAddr" value="${sessionScope.pvo.jibunAddr}" hidden>
			<input type="text" id="x" name="lng" value="${sessionScope.pvo.lng}" hidden>
			<input type="text" id="y" name="lat" value="${sessionScope.pvo.lat}" hidden>
			<input type="text" id="category" name="category" value="all" hidden>
			<input type="text" id="id" name="order" value="1" hidden>
		</form>
	</div>

	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=e8b552c46357c215f64b284e4da814a9&libraries=services"></script>
	<script>
	
	let exten = $(".search-extention");
	
	//카테고리 버튼 주소가 입력되었을 때만 활성화
	function clickCategory(category){
		console.log("click");
		let input = exten.find("#jibunAddr")
		if(input.val() == "" || input.val()==null){
			alert("주소를 입력해주세요.");
		}else{
			$("#category").val(category);
			$("#addr-form").submit();
		}
		
	}
	
	//검색어로 주소 검색
	function searchAddress(keyword){
		var places = new kakao.maps.services.Places();
		var callback = function(result, status) {
			if (status === kakao.maps.services.Status.OK) {
				return result;
			}
		};
		places.keywordSearch(keyword, callback, options);
		return null;
	}
	
	/* HTML5 geolocation */
	function getLocation() {
	  if (navigator.geolocation) {
	    navigator.geolocation.getCurrentPosition(showPosition);
	  } else { 
	    alert("Geolocation is not supported by this browser.");
	  }
	}
	/* geolocation에서 가져온 좌표값으로 카카오맵api에서 주소 검색 후 가장 가까운 주소 5개를 검색해 검색창 확장 div에 뿌리기 */
	function showPosition(position) {
	  
	  // 카카오맵에서 주소 검색
		var geocoder = new kakao.maps.services.Geocoder();
		var coord = new kakao.maps.LatLng(position.coords.latitude, position.coords.longitude);
		var callback = function(result, status) {
			if (status === kakao.maps.services.Status.OK) {
				console.log(result[0]);
				
				if(searchAddress(result[0].address.address_name)==null){
					searchAddr(result[0].address.region_1depth_name+" "+result[0].address.region_2depth_name+" "+result[0].address.region_3depth_name);
				}else{
					searchAddr(result[0].address.address_name);
				}
				$("#search-input").val(result[0].address.address_name);
			}
		};
		geocoder.coord2Address(coord.getLng(), coord.getLat(), callback);
	}
	
	var options = {
			size: 5
	}
	
	//가공된 주소로 검색
	function searchAddr(keyword){
		
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
		let keyword = $("#search-input").val();
		searchAddr(keyword);
	})

	$("#search-input").on("click keyup", function(){
		console.log("change");
		let keyword = $("#search-input").val();
		searchAddr(keyword);
	})
	
	function addSearchBox(result){	
		if(result==null) {
			exten.hide();
			return;
		}
		
		if(result.length>0){
			exten.html("");
			exten.show();
			for(let i=0; i<result.length; i++ ){

				
				let addr = result[i];

				if(i==0){
					$("#jibunAddr").val(addr.address_name);
					$("#x").val(addr.x);
					$("#y").val(addr.y);
				}
				
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