<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="../include/header.jsp"></jsp:include>
<link type="text/css" rel="stylesheet" href="/resources/css/list.css">

<div class="header-box">
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
	<div class="category">
		<ul class="nav justify-content-center">
			<li class="nav-item">
				<div class="category-btn">전체보기</div>
			</li>
			<li class="nav-item">
				<div class="category-btn">1인분 주문</div>
			</li>
			<li class="nav-item">
				<div class="category-btn">프랜차이즈</div>
			</li>
			<li class="nav-item">
				<div class="category-btn">치킨</div>
			</li>
			<li class="nav-item">
				<div class="category-btn">피자/양식</div>
			</li>
			<li class="nav-item">
				<div class="category-btn">중국집</div>
			</li>
			<li class="nav-item">
				<div class="category-btn">한식</div>
			</li>
			<li class="nav-item">
				<div class="category-btn">일식/돈까스</div>
			</li>
			<li class="nav-item">
				<div class="category-btn">족발/보쌈</div>
			</li>
			<li class="nav-item">
				<div class="category-btn">야식</div>
			</li>
			<li class="nav-item">
				<div class="category-btn">분식</div>
			</li>
			<li class="nav-item">
				<div class="category-btn">카페/디저트</div>
			</li>
			<li class="nav-item">
				<div class="category-btn">편의점/마트</div>
			</li>
		</ul>
	</div>

	<div class="contents bg-light">
		
		<div class="selection-wrap">
			<div class="row justify-content-end">
				<div class="col-6 ">
					<select name="" id="category-option">
						<option value="">기본 정렬순</option>
						<option value="">거리순</option>
						<option value="">별점순</option>
						<option value="">리뷰 많은 순</option>
					</select>
				</div>
				<div class="col-6">
					<select name="" id="search-option">
						<option value="">기본 정렬순</option>
						<option value="">거리순</option>
						<option value="">별점순</option>
						<option value="">리뷰 많은 순</option>
					</select>
				</div>
			</div>
		</div>
		<div class="registered py-5 bg-light">
			<div class="one-row justify-content-center">
				<div class="diner-card bg-white" id="diner-card">
					<div class="diner-img">
						<img src="/resources/source/dinerimg.PNG" alt="" width="80px" height="80px">
					</div>
					<div class="diner-body">
						<h5 class="diner-title">탐나라종합어시장-모래내시장점</h5>
						<p class="diner-text">
						<span class="score">★3.8</span>
						| 리뷰 1902 | 사장님댓글 791 
						</p>
						<p class="diner-text">
						<span class="del-option">요기서결제</span> |
						<span>20000원 이상 배달</span>
						</p>
						<p class="delivery-time">
						22분
						</p>
					</div>
				</div>
				<div class="diner-card bg-white">
					<div class="diner-img">
						<img src="/resources/source/dinerimg.PNG" alt="" width="80px" height="80px">
					</div>
					<div class="diner-body">
						<h5 class="diner-title">탐나라종합어시장-모래내시장점</h5>
						<p class="diner-text">
						<span class="score">★3.8</span>
						| 리뷰 1902 | 사장님댓글 791 
						</p>
						<p class="diner-text">
						<span>요기서결제</span> |
						<span>20000원 이상 배달</span>
						</p>
						<p class="delivery-time">
						22분
						</p>
					</div>
				</div>
				
			</div>
			<!-- row -->
			<div class="one-row justify-content-center">
				<div class="diner-card bg-white" id="diner-card">
					<div class="diner-img">
						<img src="/resources/source/dinerimg.PNG" alt="" width="80px" height="80px">
					</div>
					<div class="diner-body">
						<h5 class="diner-title">탐나라종합어시장-모래내시장점</h5>
						<p class="diner-text">
						<span class="score">★3.8</span>
						| 리뷰 1902 | 사장님댓글 791 
						</p>
						<p class="diner-text">
						<span class="del-option">요기서결제</span> |
						<span>20000원 이상 배달</span>
						</p>
						<p class="delivery-time">
						22분
						</p>
					</div>
				</div>
				<div class="diner-card bg-white">
					<div class="diner-img">
						<img src="/resources/source/dinerimg.PNG" alt="" width="80px" height="80px">
					</div>
					<div class="diner-body">
						<h5 class="diner-title">탐나라종합어시장-모래내시장점</h5>
						<p class="diner-text">
						<span class="score">★3.8</span>
						| 리뷰 1902 | 사장님댓글 791 
						</p>
						<p class="diner-text">
						<span>요기서결제</span> |
						<span>20000원 이상 배달</span>
						</p>
						<p class="delivery-time">
						22분
						</p>
					</div>
				</div>
				
			</div>
			<!-- row -->
			<div class="one-row justify-content-center">
				<div class="diner-card bg-white" id="diner-card">
					<div class="diner-img">
						<img src="/resources/source/dinerimg.PNG" alt="" width="80px" height="80px">
					</div>
					<div class="diner-body">
						<h5 class="diner-title">탐나라종합어시장-모래내시장점</h5>
						<p class="diner-text">
						<span class="score">★3.8</span>
						| 리뷰 1902 | 사장님댓글 791 
						</p>
						<p class="diner-text">
						<span class="del-option">요기서결제</span> |
						<span>20000원 이상 배달</span>
						</p>
						<p class="delivery-time">
						22분
						</p>
					</div>
				</div>
				<div class="diner-card bg-white">
					<div class="diner-img">
						<img src="/resources/source/dinerimg.PNG" alt="" width="80px" height="80px">
					</div>
					<div class="diner-body">
						<h5 class="diner-title">탐나라종합어시장-모래내시장점</h5>
						<p class="diner-text">
						<span class="score">★3.8</span>
						| 리뷰 1902 | 사장님댓글 791 
						</p>
						<p class="diner-text">
						<span>요기서결제</span> |
						<span>20000원 이상 배달</span>
						</p>
						<p class="delivery-time">
						22분
						</p>
					</div>
				</div>
				
			</div>
			<!-- row -->
			<div class="one-row justify-content-center">
				<div class="diner-card bg-white" id="diner-card">
					<div class="diner-img">
						<img src="/resources/source/dinerimg.PNG" alt="" width="80px" height="80px">
					</div>
					<div class="diner-body">
						<h5 class="diner-title">탐나라종합어시장-모래내시장점</h5>
						<p class="diner-text">
						<span class="score">★3.8</span>
						| 리뷰 1902 | 사장님댓글 791 
						</p>
						<p class="diner-text">
						<span class="del-option">요기서결제</span> |
						<span>20000원 이상 배달</span>
						</p>
						<p class="delivery-time">
						22분
						</p>
					</div>
				</div>
				<div class="diner-card bg-white">
					<div class="diner-img">
						<img src="/resources/source/dinerimg.PNG" alt="" width="80px" height="80px">
					</div>
					<div class="diner-body">
						<h5 class="diner-title">탐나라종합어시장-모래내시장점</h5>
						<p class="diner-text">
						<span class="score">★3.8</span>
						| 리뷰 1902 | 사장님댓글 791 
						</p>
						<p class="diner-text">
						<span>요기서결제</span> |
						<span>20000원 이상 배달</span>
						</p>
						<p class="delivery-time">
						22분
						</p>
					</div>
				</div>
				
			</div>
			<!-- row -->
			<div class="one-row justify-content-center">
				<div class="diner-card bg-white" id="diner-card">
					<div class="diner-img">
						<img src="/resources/source/dinerimg.PNG" alt="" width="80px" height="80px">
					</div>
					<div class="diner-body">
						<h5 class="diner-title">탐나라종합어시장-모래내시장점</h5>
						<p class="diner-text">
						<span class="score">★3.8</span>
						| 리뷰 1902 | 사장님댓글 791 
						</p>
						<p class="diner-text">
						<span class="del-option">요기서결제</span> |
						<span>20000원 이상 배달</span>
						</p>
						<p class="delivery-time">
						22분
						</p>
					</div>
				</div>
				<div class="diner-card bg-white">
					<div class="diner-img">
						<img src="/resources/source/dinerimg.PNG" alt="" width="80px" height="80px">
					</div>
					<div class="diner-body">
						<h5 class="diner-title">탐나라종합어시장-모래내시장점</h5>
						<p class="diner-text">
						<span class="score">★3.8</span>
						| 리뷰 1902 | 사장님댓글 791 
						</p>
						<p class="diner-text">
						<span>요기서결제</span> |
						<span>20000원 이상 배달</span>
						</p>
						<p class="delivery-time">
						22분
						</p>
					</div>
				</div>
				
			</div>
			<!-- row -->
			<div class="one-row justify-content-center">
				<div class="diner-card bg-white" id="diner-card">
					<div class="diner-img">
						<img src="/resources/source/dinerimg.PNG" alt="" width="80px" height="80px">
					</div>
					<div class="diner-body">
						<h5 class="diner-title">탐나라종합어시장-모래내시장점</h5>
						<p class="diner-text">
						<span class="score">★3.8</span>
						| 리뷰 1902 | 사장님댓글 791 
						</p>
						<p class="diner-text">
						<span class="del-option">요기서결제</span> |
						<span>20000원 이상 배달</span>
						</p>
						<p class="delivery-time">
						22분
						</p>
					</div>
				</div>
				<div class="diner-card bg-white">
					<div class="diner-img">
						<img src="/resources/source/dinerimg.PNG" alt="" width="80px" height="80px">
					</div>
					<div class="diner-body">
						<h5 class="diner-title">탐나라종합어시장-모래내시장점</h5>
						<p class="diner-text">
						<span class="score">★3.8</span>
						| 리뷰 1902 | 사장님댓글 791 
						</p>
						<p class="diner-text">
						<span>요기서결제</span> |
						<span>20000원 이상 배달</span>
						</p>
						<p class="delivery-time">
						22분
						</p>
					</div>
				</div>
				
			</div>
			<!-- row -->
		</div>
	</div>

</main>

<jsp:include page="../include/footer2.jsp"></jsp:include>