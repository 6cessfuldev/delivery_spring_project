<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="./include/header.jsp"></jsp:include>
<link type="text/css" rel="stylesheet" href="/resources/css/index.css">
<style>
	.card-box{
		border : 1px solid rgb(181, 190, 193);
		width : 200px;
		height : 200px;
		margin : 6px;
		background-color: white;
		padding : 16px;
		font-size: 13px;
		font-weight: 700;
	}
	
	main{
		padding-top: 20px;
		padding-bottom: 20px;
		background-color : rgb(244, 250, 252);
	}

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
				<input id="search-input" type="text" placeholder="건물명, 도로명, 지번으로 검색하세요."></input>
				<button id="search-btn" type="button">검색</button>
			</div>
		</div>
	</div>
	
	<main>
		<div class="d-flex justify-content-center">
			<div class='card-box'>hi</div>
			<div class='card-box'>전체보기</div>
			<div class='card-box'>1인분 주문</div>
			<div class='card-box'>프랜차이즈</div>
		</div>
		<div class="d-flex justify-content-center">
			<div class='card-box'>치킨</div>
			<div class='card-box'>피자/양식</div>
			<div class='card-box'>중국집</div>
			<div class='card-box'>한식</div>
		</div>
		<div class="d-flex justify-content-center">
			<div class='card-box'>일식/돈까스</div>
			<div class='card-box'>족발/보쌈</div>
			<div class='card-box'>야식</div>
			<div class='card-box'>분식</div>
		</div>
		<div class="d-flex justify-content-center">
			<div class='card-box'>카페/디저트</div>
			<div class='card-box'>편의점/마트</div>
		</div>
	</main>
<jsp:include page="./include/footer.jsp"></jsp:include>