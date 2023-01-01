<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="./include/header.jsp"></jsp:include>
<link type="text/css" rel="stylesheet" href="/resources/css/index.css">
<style>
	.card-box{
		border : 1px solid rgb(181, 190, 193);
		width : 215px;
		height : 200px;
		margin : 6px;
		background-color: white;
		padding : 10px;
		font-size: 13px;
		font-weight: 700;
		cursor:pointer;
	}
	
	main{
		padding-top: 10px;
		padding-bottom: 20px;
		background-color : rgb(244, 250, 252);
	}
	
	.event{
		background-image: url("/resources/source/yuk.png"); 
		background-size: 170px;
		background-repeat: no-repeat;
		background-position: 0px 35px;
	}
	
	.event:hover{
		background-size : 230px;
	}
	
	.all{
		background-image: url("/resources/source/knife.png"); 
		background-size: 100px;
		background-repeat: no-repeat;
		background-position: 100px 60px;
	}
	
	.forone{
		background-image: url("/resources/source/forone.png"); 
		background-size: 215px;
		background-position:0px 40px;
		background-repeat: no-repeat;
	}
	
	.franchise{
		background-image: url("/resources/source/franchise.png"); 
		background-size: 180px;
		background-position:30px 43px;
		background-repeat: no-repeat;
	}
	
	.chicken{
		background-image: url("/resources/source/chicken.png"); 
		background-size: 300px;
		background-position:0px 30px;
		background-repeat: no-repeat;
	}
	
	.pizza{
		background-image: url("/resources/source/pizza.png"); 
		background-size: 300px;
		background-position:10px 70px;
		background-repeat: no-repeat;
	}

	.chinese{
		background-image: url("/resources/source/chinese.png"); 
		background-size: 250px;
		background-position:0px 40px;
		background-repeat: no-repeat;
	}

	.korean{
		background-image: url("/resources/source/korean.png"); 
		background-size: 300px;
		background-position:0px 20px;
		background-repeat: no-repeat;
	}
	
	.japanese{
		background-image: url("/resources/source/cutlet.png"); 
		background-size: 220px;
		background-position:0px 50px;
		background-repeat: no-repeat;
	}

	.pork{
		background-image: url("/resources/source/pork.png"); 
		background-size: 260px;
		background-position:40px 60px;
		background-repeat: no-repeat;
	}
	
	.nightfood{
		background-image: url("/resources/source/nightfood.png"); 
		background-size: 250px;
		background-position:0px 40px;
		background-repeat: no-repeat;
	}
	
	.kimbob{
		background-image: url("/resources/source/kimbob.png"); 
		background-size: 340px;
		background-position:-20px 40px;
		background-repeat: no-repeat;
	}
	
	.mart{
		background-image: url("/resources/source/lunchbox.png"); 
		background-size: 200px;
		background-position:20px 100px;
		background-repeat: no-repeat;
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
			<div class='card-box event'>
				<p>event</p>
			</div>
			<div class='card-box all'>
				<p>전체보기</p>
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
<jsp:include page="./include/footer.jsp"></jsp:include>