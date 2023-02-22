<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>

.category{
	height: 60px;
	width: 100%;
	border-bottom: 1px solid rgb(209, 203, 203);
	
}

.category>ul{
	height: 60px;
	display: flex;
}

.category>.nav>li{
	height: 60px;
	font-size: 14px;
}

.category>.nav>li:hover{
	background-color: black;
	color: white;
}

.active{
	background-color: black;
	color: white;
}

.category-btn{
	padding-left: 12px;
	padding-right: 12px;
	padding-top: 18px;
	width: 100%;
	height: 100%;
	cursor: pointer;
}
</style>


<div class="category">
	<ul class="nav justify-content-center">
		<li class="nav-item">
			<div class="category-btn" onclick='location.href ="/order/myOrderList";'>주문/결제 조회</div>
		</li>
		<li class="nav-item">
			<div class="category-btn" onclick='location.href ="/dibs/myDibsList";' id="cate-ff">찜목록</div>
		</li>
		<li class="nav-item">
			<div class="category-btn" onclick='location.href ="/member/detail_userInfo";'>회원 정보</div>
		</li>
	</ul>
</div>