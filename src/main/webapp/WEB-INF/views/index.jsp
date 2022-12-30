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
				<input id="search-input" type="text" placeholder="건물명, 도로명, 지번으로 검색하세요."></input>
				<button id="search-btn" type="button">검색</button>
			</div>
		</div>
		
		
	</div>

<jsp:include page="./include/footer.jsp"></jsp:include>