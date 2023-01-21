<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<title>먹어요</title>
<jsp:include page="../include/header.jsp"></jsp:include>
<link type="text/css" rel="stylesheet" href="/resources/css/detail.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@8/swiper-bundle.min.css"/>

<main>
	<div class="category">
		<ul class="nav justify-content-center">
			<li class="nav-item">
				<div class="category-btn" id="cate-all" >전체보기</div>
			</li>
			<li class="nav-item">
				<div class="category-btn" id="cate-aa">1인분 주문</div>
			</li>
			<li class="nav-item">
				<div class="category-btn" id="cate-ff">프랜차이즈</div>
			</li>
			<li class="nav-item">
				<div class="category-btn" id="cate-hh">치킨</div>
			</li>
			<li class="nav-item">
				<div class="category-btn" id="cate-pp">피자/양식</div>
			</li>
			<li class="nav-item">
				<div class="category-btn" id="cate-cc">중국집</div>
			</li>
			<li class="nav-item">
				<div class="category-btn" id="cate-kk">한식</div>
			</li>
			<li class="nav-item">
				<div class="category-btn" id="cate-jj">일식/돈까스</div>
			</li>
			<li class="nav-item">
				<div class="category-btn" id="cate-mm">족발/보쌈</div>
			</li>
			<li class="nav-item">
				<div class="category-btn" id="cate-nn">야식</div>
			</li>
			<li class="nav-item">
				<div class="category-btn" id="cate-tt">분식</div>
			</li>
			<li class="nav-item">
				<div class="category-btn" id="cate-dd">카페/디저트</div>
			</li>
			<li class="nav-item">
				<div class="category-btn" id="cate-ss">편의점/마트</div>
			</li>
		</ul>
	</div>

	<div class=" col-sm-8 contents bg-light d-flex justify-content-center pt-3">
		
		<div class="diner-detail">
			<div class=diner-header>
				<div class="diner-name">
					<span class=diner-name-field>${diner.diner_name}</span>
					<span class="dib-field">♡</span>
				</div>
				<div class="diner-info">
					<div class="diner-img">
						<img src="/upload/${fn:replace(fivo.file_save_dir, '\\','/')}/${fivo.file_uuid}_${fivo.file_name}" alt="" width="70" height="70">
					</div>
					<div class="diner-infos">
						<p class="score">★★★★☆</p>
						<fmt:formatNumber value="${diner.diner_min_pay}" var="minPay" pattern="#,###" />
						<p>최소주문금액 ${minPay}원</p>
						<p>결제 <span>${diner.diner_method_pay}</span></p>
						<p>배달시간 <span>38분~48분</span></p>
					</div>
				</div>
				<div class="diner-notice">
					<p>사장님 알림 <span>${diner.diner_notice}</span></p>
				</div>
			</div>

			<div class="diner-menu">
				<ul class="nav nav-tabs justify-content-center" id="myTab" role="tablist">
					<li class="nav-item" role="presentation">
					<button class="active bg-light" id="home-tab" data-bs-toggle="tab" data-bs-target="#home-tab-pane" type="button" role="tab" aria-controls="home-tab-pane" aria-selected="true">메뉴</button>
					</li>
					<li class="nav-item" role="presentation">
					<button class="bg-light" id="profile-tab" data-bs-toggle="tab" data-bs-target="#profile-tab-pane" type="button" role="tab" aria-controls="profile-tab-pane" aria-selected="false">리뷰</button>
					</li>
					<li class="nav-item" role="presentation">
					<button class="clicked bg-light" id="contact-tab" data-bs-toggle="tab" data-bs-target="#contact-tab-pane" type="button" role="tab" aria-controls="contact-tab-pane" aria-selected="false">정보</button>
					</li>
				</ul>
				<div class="tab-content" id="myTabContent">
					<div class="tab-pane fade active show" id="home-tab-pane" role="tabpanel" aria-labelledby="home-tab" tabindex="0">
					
					<!-- Slider main container -->
						<div class="swiper">
							<!-- Additional required wrapper -->
							<div class="swiper-wrapper">
							<!-- Slides -->
							
							<c:forEach items="${foodList}" var="food">
								<div class="swiper-slide" onClick='openModal(${food.foodvo.food_code})' style="cursor:pointer;">
									<img alt="food" src="/upload/${fn:replace(food.filevo.file_save_dir, '\\','/')}/${food.filevo.file_uuid}_${food.filevo.file_name}">
								</div>							
							</c:forEach>
							
							
							</div>
							<!-- If we need pagination -->
							<div class="swiper-pagination"></div>
						
							<!-- If we need navigation buttons -->
							<div class="swiper-button-prev"></div>
							<div class="swiper-button-next"></div>
						
							<!-- If we need scrollbar -->
							<div class="swiper-scrollbar"></div>
						</div>
						<div class="accordion accordion-flush" id="accordionFlushExample">
							<div class="accordion-item">
								<h2 class="accordion-header" id="flush-headingOne">
									<button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseOne" aria-expanded="false" aria-controls="flush-collapseOne">
									전체메뉴
									</button>
								</h2>
								<div id="flush-collapseOne" class="accordion-collapse collapse show" aria-labelledby="flush-headingOne" data-bs-parent="#accordionFlushExample">
									<div class="accordion-body">
									
									<c:forEach items="${foodList}" var="food">
														
										<div class="diner-menu-card d-flex align-items-center justify-content-between" onClick='openModal(${food.foodvo.food_code})' style="cursor:pointer;">
											<div class="diner-menu-table">
												<p class="diner-menu-title">${food.foodvo.food_name}</p>
												<p class="diner-menu-price">${food.foodvo.food_price }</p>
											</div>
											<div class="diner-menu-img">
												<img src="/upload/${fn:replace(food.filevo.file_save_dir, '\\','/')}/${food.filevo.file_uuid}_${food.filevo.file_name}" alt="" width="100px" height="100px">
											</div>
										</div>
					
									</c:forEach>
						
									</div>
								</div>
							</div>
							<div class="accordion-item">
								<h2 class="accordion-header" id="flush-headingTwo">
									<button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseTwo" aria-expanded="false" aria-controls="flush-collapseTwo">
									1인분 주문
									</button>
								</h2>
								<div id="flush-collapseTwo" class="accordion-collapse collapse" aria-labelledby="flush-headingTwo" data-bs-parent="#accordionFlushExample">
									<div class="accordion-body">
										<div class="diner-menu-card d-flex align-items-center justify-content-between" >
											<div class="diner-menu-table">
												<p class="diner-menu-title">삼겹짜글이장교</p>
												<p class="diner-menu-price">10,900원</p>
											</div>
											<div class="diner-menu-img">
												<img src="/resources/source/forone.png" alt="" width="100px" height="100px">
											</div>
										</div>
										<div class="diner-menu-card d-flex align-items-center justify-content-between">
											<div class="diner-menu-table">
												<p class="diner-menu-title">삼겹짜글이장교</p>
												<p class="diner-menu-combo">차돌+삼겹+햄+후라이2개+볶음김치+알단무지+날치알+후리가케밥+김가루</p>
												<p class="diner-menu-price">10,900원</p>
											</div>
											<div class="diner-menu-img">
												<img src="/resources/source/forone.png" alt="" width="100px" height="100px">
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="accordion-item">
								<h2 class="accordion-header" id="flush-headingThree">
									<button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseThree" aria-expanded="false" aria-controls="flush-collapseThree">
									장군덮밥
									</button>
								</h2>
								<div id="flush-collapseThree" class="accordion-collapse collapse" aria-labelledby="flush-headingThree" data-bs-parent="#accordionFlushExample">
									<div class="accordion-body">
										<div class="diner-menu-card d-flex align-items-center justify-content-between">
											<div class="diner-menu-table">
												<p class="diner-menu-title">삼겹짜글이장교</p>
												<p class="diner-menu-price">10,900원</p>
											</div>
											<div class="diner-menu-img">
												<img src="/resources/source/forone.png" alt="" width="100px" height="100px">
											</div>
										</div>
										<div class="diner-menu-card d-flex align-items-center justify-content-between">
											<div class="diner-menu-table">
												<p class="diner-menu-title">삼겹짜글이장교</p>
												<p class="diner-menu-combo">차돌+삼겹+햄+후라이2개+볶음김치+알단무지+날치알+후리가케밥+김가루</p>
												<p class="diner-menu-price">10,900원</p>
											</div>
											<div class="diner-menu-img">
												<img src="/resources/source/forone.png" alt="" width="100px" height="100px">
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="accordion-item">
								<h2 class="accordion-header" id="flush-headingfour">
								<button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapsefour" aria-expanded="false" aria-controls="flush-collapsefour">
									장군덮밥
								</button>
								</h2>
								<div id="flush-collapsefour" class="accordion-collapse collapse" aria-labelledby="flush-headingfour" data-bs-parent="#accordionFlushExample">
									<div class="accordion-body">
										<div class="diner-menu-card d-flex align-items-center justify-content-between">
											<div class="diner-menu-table">
												<p class="diner-menu-title">삼겹짜글이장교</p>
												<p class="diner-menu-price">10,900원</p>
											</div>
											<div class="diner-menu-img">
												<img src="/resources/source/forone.png" alt="" width="100px" height="100px">
											</div>
										</div>
										<div class="diner-menu-card d-flex align-items-center justify-content-between">
											<div class="diner-menu-table">
												<p class="diner-menu-title">삼겹짜글이장교</p>
												<p class="diner-menu-combo">차돌+삼겹+햄+후라이2개+볶음김치+알단무지+날치알+후리가케밥+김가루</p>
												<p class="diner-menu-price">10,900원</p>
											</div>
											<div class="diner-menu-img">
												<img src="/resources/source/forone.png" alt="" width="100px" height="100px">
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- accordion -->
					</div>
					<!-- tab-pane -->


					<div class="tab-pane fade" id="profile-tab-pane" role="tabpanel" aria-labelledby="profile-tab" tabindex="0">
						<div class="diner-score d-flex justify-content-center">
							<div class="diner-score-total">
								<p class="total-score">${diner.diner_score_avg}</p>
								<p class="total-star">★★★★☆</p><!-- 평균 맞춰서 색 채워야함 -->
							</div>					
						</div>
						<!-- diner-score -->
					
						<div class="review-count">
							<p>리뷰 ${diner.diner_review_count} / 사장님댓글 2</p>
						</div>
					
						<!-- 별점 -->
						<form name="starform" id="starform" method="post" action="./save">
						  <div id="star">
						    <fieldset>
						        <legend id="starInfo">별을 꼭 클릭해서 채워주세요</legend>
						        	<div id="starRadio">
						         	<input type="radio" name="rating" value="5" id="rate1"><label for="rate1">⭐</label>
							        <input type="radio" name="rating" value="4" id="rate2"><label for="rate2">⭐</label>
							        <input type="radio" name="rating" value="3" id="rate3"><label for="rate3">⭐</label>
							        <input type="radio" name="rating" value="2" id="rate4"><label for="rate4">⭐</label>
							        <input type="radio" name="rating" value="1" id="rate5"><label for="rate5">⭐</label>
							        </div>
						    </fieldset>
						  </div>
						</form>
							<textarea name="content" class="review_content" rows="7" cols="103" id="review_con"
		                          placeholder="음식과 가게에 대한 솔직한 후기를 적어주세요!"></textarea ><br>
		                    <div class="review_insertBox"></div>
                               
									
						  <!-- 이미지파일 등록 -->
                            <div class="review_multiplebox">
				                 <input type="file" id="review_multiple" name="files[]" accept="image/*" onchange="readFile2('files[]');" style="display: none" multiple="multiple">
				            		<br>
								 <button type="button" id="trigger">사진</button>
				                 <div id="image_container">		
				                 </div>
				         	</div>      
				         	<div class="review_insertBox">
				         		<br>
									<button type="button" id="regBtn" class="review_insert">완료</button>
								<br>
                            </div>
                            
                            	<!-- 등록 후 출력 화면 -->
                         <div class="review">
                            <div id="review-head">
                            	  <div id="bossCommentBox"></div>
                            </div>
                         </div>	
					
				
						<div class="add-review d-flex justify-content-center align-items-center">
							더보기&nbsp; <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-down" viewBox="0 0 16 16">
								<path fill-rule="evenodd" d="M8 1a.5.5 0 0 1 .5.5v11.793l3.146-3.147a.5.5 0 0 1 .708.708l-4 4a.5.5 0 0 1-.708 0l-4-4a.5.5 0 0 1 .708-.708L7.5 13.293V1.5A.5.5 0 0 1 8 1z"/>
							  </svg>
						</div>
					</div>
					
					<!-- tab-pane -->
					<div class="tab-pane fade" id="contact-tab-pane" role="tabpanel" aria-labelledby="contact-tab" tabindex="0">
						<div class="info-list">
							<div class="info-item">
								<div class="notice-title">
									<svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" fill="currentColor" class="bi bi-bell-fill" viewBox="0 0 16 16">
										<path d="M8 16a2 2 0 0 0 2-2H6a2 2 0 0 0 2 2zm.995-14.901a1 1 0 1 0-1.99 0A5.002 5.002 0 0 0 3 6c0 1.098-.5 6-2 7h14c-1.5-1-2-5.902-2-7 0-2.42-1.72-4.44-4.005-4.901z"/>
									</svg>
									<span>사장님알림</span>
								</div>
								<div class="img-list">
	
								</div>
								<div class="info-text-narrow">
									<span>
										${diner.diner_notice}
									</span>	
								</div>
							</div>

							<div class="info-item">
								<div class="notice-title">
									<svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" fill="currentColor" class="bi bi-bell-fill" viewBox="0 0 16 16">
										<path d="M8 16a2 2 0 0 0 2-2H6a2 2 0 0 0 2 2zm.995-14.901a1 1 0 1 0-1.99 0A5.002 5.002 0 0 0 3 6c0 1.098-.5 6-2 7h14c-1.5-1-2-5.902-2-7 0-2.42-1.72-4.44-4.005-4.901z"/>
									</svg>
									<span>업체정보</span>
								</div>
								<div class="info-text">
									<span class="info-list-title">영업시간</span> <span>${diner.diner_open_time} - ${diner.diner_close_time}</span> <br>
									<span class="info-list-title">전화번호</span> <span>050712921952</span> <br>
									<span class="info-list-title">주소</span> <span>${diner.diner_address}</span>
								</div>
							</div>

							<div class="info-item">
								<div class="notice-title">
									<svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" fill="currentColor" class="bi bi-bell-fill" viewBox="0 0 16 16">
										<path d="M8 16a2 2 0 0 0 2-2H6a2 2 0 0 0 2 2zm.995-14.901a1 1 0 1 0-1.99 0A5.002 5.002 0 0 0 3 6c0 1.098-.5 6-2 7h14c-1.5-1-2-5.902-2-7 0-2.42-1.72-4.44-4.005-4.901z"/>
									</svg>
									<span>결제정보</span>
								</div>
								<div class="info-text">
									<span class="info-list-title">최소주문금액</span> <span>11,000원</span> <br>
									<span class="info-list-title">결제수단</span> <span>${diner.diner_method_pay}</span>
								</div>
							</div>

							<div class="info-item">
								<div class="notice-title">
									<svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" fill="currentColor" class="bi bi-bell-fill" viewBox="0 0 16 16">
										<path d="M8 16a2 2 0 0 0 2-2H6a2 2 0 0 0 2 2zm.995-14.901a1 1 0 1 0-1.99 0A5.002 5.002 0 0 0 3 6c0 1.098-.5 6-2 7h14c-1.5-1-2-5.902-2-7 0-2.42-1.72-4.44-4.005-4.901z"/>
									</svg>
									<span>사업자정보</span>
								</div>
								<div class="info-text">
									<span class="info-list-title">상호명</span> <span>${diner.diner_business_name}</span><br>
									<span class="info-list-title">사업자등록번호</span> <span>${diner.diner_company_num}	</span>
								</div>
							</div>

							
							<div class="info-item">
								<div class="notice-title">
									<svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" fill="currentColor" class="bi bi-bell-fill" viewBox="0 0 16 16">
										<path d="M8 16a2 2 0 0 0 2-2H6a2 2 0 0 0 2 2zm.995-14.901a1 1 0 1 0-1.99 0A5.002 5.002 0 0 0 3 6c0 1.098-.5 6-2 7h14c-1.5-1-2-5.902-2-7 0-2.42-1.72-4.44-4.005-4.901z"/>
									</svg>
									<span>원산지정보</span>
								</div>
								<div class="info-text-narrow">
									<span>오리지널치킨 (닭고기: 국내산)</span> <br>
									<span>핫크리스피치킨 (닭고기: 국내산)</span>
								</div>
							</div>
								

						</div>
					</div>
					<!-- tab-pane -->
				</div>
			</div>
			<!-- diner-menu -->
		</div>
		<!-- diner-detail -->


		<div class="col-sm-3 basket">
			<div class="sub-title">
				<span>주문</span> <span><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
					<path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
					<path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
				  </svg></span>
			</div>
			
			<div id="basket-menu-list"></div>
				<div class="basket-deli-price">
					<fmt:formatNumber value="${diner.diner_delivery_fee}" var="deliveryFee" pattern="#,###" />
					<span>배달요금</span> <span>${deliveryFee}원</span> <span>별도</span>
				</div>
				<div class="basket-total-price">
					<span>합계:</span> <span id="total">0원</span>
				</div>
				<div class="order-btn" onclick='location.href="/order/page";'>주문하기</div>
			</div>
		</div>

</main>
	<form id="addr-form" action="/diner/search" method="get">
		<input type="text" id="jibunAddr" name="jibunAddr" value="${sessionScope.pvo.jibunAddr}" hidden>
		<input type="text" id="x" name="lng" value="${sessionScope.pvo.lng}" hidden>
		<input type="text" id="y" name="lat" value="${sessionScope.pvo.lat}" hidden>
		<input type="text" id="category" name="category" value="${sessionScope.pvo.category}" hidden>
		<input type="text" id="order" name="order" value="${sessionScope.pvo.order}" hidden>
	</form>

<!-- Button trigger modal -->
<button type="button" id="modalTrigger" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal" hidden>
</button>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-scrollable modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-6" id="exampleModalLabel">메뉴상세</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body p-0">
		  <div class="modal-img"></div>
		  <div class="modal-text p-3 text-center">
		  	<div class="food-title fs-5">후라이드치킨</div>
		  	<div class="food-description fs-8">얇게 튀겨 더욱 바삭하고 속은 촉촉한, 진짜 후라이드!</div>
		  </div>
		  <div class="modal-food-price d-flex justify-content-between py-3 px-3"> 
		  	<strong>가격</strong>
		  	<strong id="modal-price">----원</strong>	
		  </div>
       	  
       	  <div class="item-list-wrap p-3">
       	  	<div class="item-list-title py-1">
		  		<strong>옵션 선택</strong>
		  		<span>(필수 선택)</span>
       	  	</div>
       	  	
       	  	<div class="item-list"></div>
       	  </div>
       	  
       	  <div class="modal-food-price d-flex justify-content-between py-2 px-3"> 
		  	<strong class="pt-1">수량</strong>
		  	
		  	<div class="modal-amount-wrap d-flex">	  	
			  	<a href="#" onClick='count("minus")'> ─ </a>
			  	<div class="modal-amount">1</div> 
			  	<a href="#" onClick='count("plus")'> ┼ </a> 
		  	</div>
		  </div>
		  
		  <div class="modal-total-price-wrap d-flex justify-content-between py-2 px-3"> 
       	  	<strong>총 주문금액</strong>
       	  	<div class="modal-total-price">
       	  		<strong id="modal-total">29,800원</strong>
       	  		<span>15,900원 이상 주문 시 할인</span>
       	  		<span>(최소 주문 금액 ${minPay}원)</span>
       	  	</div>
       	  </div>
       	  	
      </div>
      <div class="modal-footer d-flex">
        <div class="add-basket py-3" style="cursor:pointer;">장바구니에 넣기</div>
        <div class="modal-order py-3" style="cursor:pointer;">주문하기</div>
      </div>
    </div>
  </div>
</div>
<form action="/order" method="post" id="modal-form">
        	<input type="text" name="food_code" id="modal_food_code" value="" hidden>
        	<input type="text" name="order_food_count" id="modal_order_food_count" value="" hidden>       	
        	<input type="text" name="user_id" val="<sec:authorize access='isAuthenticated()'><sec:authentication property='principal.username'/></sec:authorize>" hidden>
        </form>
<script type="text/javascript">
	let diner_code = "<c:out value='${diner.diner_code}' />";
	let user_id = <sec:authorize access='!isAuthenticated()'>""</sec:authorize><sec:authorize access='isAuthenticated()'>"<sec:authentication property='principal.username'/>"</sec:authorize>;
	console.log("id : "+user_id);
	let category = '<c:out value="${sessionScope.pvo.category}" />';
	const diner_codeVal = '<c:out value="${diner.diner_code}" />';
		
	function readFile2(fileNames) {
	    const target = document.getElementsByName(fileNames);
	    const fileLength = target[0].files.length;
	    console.log(fileLength);
	      if (fileLength<1) return;
	
	      $.each(target[0].files, function(index, file){
	          const reader = new FileReader();
	          reader.onload = function(e) {
	             var div = document.createElement("div");
	              var xBtn = document.createElement("button");
	              xBtn.innerText="X";
	              xBtn.addEventListener("click", function(){
	                 div.remove();
	              }) 
	              var img = document.createElement("img"); 
	                img.setAttribute("src", e.target.result);               
	               div.appendChild(xBtn);
	               div.appendChild(img);
	           console.log("index"+index);
	              document.querySelector("div#image_container").appendChild(div);
	              
	           }
	          reader.readAsDataURL(event.target.files[index]);
	      }); 
	 } 

</script>
<script type="text/javascript" src="/resources/js/jquery-3.6.3.min.js"></script>
<script type="text/javascript" src="/resources/js/bootstrap.bundle.js"></script>
<script src="https://cdn.jsdelivr.net/npm/swiper@8/swiper-bundle.min.js"></script>
<script type="text/javascript" src="/resources/js/detail.js"></script>
<script>
getReviewList(diner_code);
basketReload();
</script>
<jsp:include page="../include/footer2.jsp"></jsp:include>