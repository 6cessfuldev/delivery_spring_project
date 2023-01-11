<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="../include/header.jsp"></jsp:include>
<link type="text/css" rel="stylesheet" href="/resources/css/detail.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@8/swiper-bundle.min.css"/>

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

	<div class=" col-sm-8 contents bg-light d-flex justify-content-center pt-3">
		
		<div class="diner-detail">
			<div class=diner-header>
				<div class="diner-name">
					인천상륙덮글이
				</div>
				<div class="diner-info">
					<div class="diner-img">
						<img src="/resources/source/kakao.jpg" alt="" width="70" height="70">
					</div>
					<div class="diner-infos">
						<p class="score">★★★★☆</p>
						<p>최소주문금액 9,000원</p>
						<p>결제 <span>신용카드, 현금, 요기서결제</span></p>
						<p>배달시간 <span>38분~48분</span></p>
					</div>
				</div>
				<div class="diner-notice">
					<p>사장님 알림 <span>9월 7일부터 신규오픈 "인천상륙 덮글이" 인천 상륙 덮글이 우리나라 월드컵 16강 기원 리...</span></p>
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
							<div class="swiper-slide"></div>
							<div class="swiper-slide"></div>
							<div class="swiper-slide"></div>
							<div class="swiper-slide"></div>
							<div class="swiper-slide"></div>
							<div class="swiper-slide"></div>
							<div class="swiper-slide"></div>
							<div class="swiper-slide"></div>
							<div class="swiper-slide"></div>
							<div class="swiper-slide"></div>
							<div class="swiper-slide"></div>
							<div class="swiper-slide"></div>
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
									인기메뉴
									</button>
								</h2>
								<div id="flush-collapseOne" class="accordion-collapse collapse show" aria-labelledby="flush-headingOne" data-bs-parent="#accordionFlushExample">
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
								<h2 class="accordion-header" id="flush-headingTwo">
									<button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseTwo" aria-expanded="false" aria-controls="flush-collapseTwo">
									1인분 주문
									</button>
								</h2>
								<div id="flush-collapseTwo" class="accordion-collapse collapse" aria-labelledby="flush-headingTwo" data-bs-parent="#accordionFlushExample">
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
								<p class="total-score">4.9</p>
								<p class="total-star">★★★★☆</p>
							</div>
							<div class="diner-score-each">
								<ul>
									<li>
										<span>맛&nbsp;&nbsp;&nbsp;</span> <span>★★★★☆</span> <span>4.9</span>
									</li>
									<li>
										<span>양&nbsp;&nbsp;&nbsp;</span> <span>★★★★☆</span> <span>4.9</span>
									</li>
									<li>
										<span>배달</span> <span>★★★★☆</span> <span>4.9</span>
									</li>
								</ul>
							</div>
						</div>
						<!-- diner-score -->

						<div class="review-count">
							<p>리뷰 472개 / 사장님댓글 452개</p>
						</div>

						
					    <div class="review_box">
                              <div class="review_rating">
                                 <div class="review_star">얼마나 만족하셨나요?</div>
                                 <div class="rating">
                                 	<input type="checkbox" name="rating" id="rating1" value="1" class="rate_radio" title="1점">
					                <label for="rating1"></label>
					                <input type="checkbox" name="rating" id="rating2" value="2" class="rate_radio" title="2점">
					                <label for="rating2"></label>
					                <input type="checkbox" name="rating" id="rating3" value="3" class="rate_radio" title="3점" >
					                <label for="rating3"></label>
					                <input type="checkbox" name="rating" id="rating4" value="4" class="rate_radio" title="4점">
					                <label for="rating4"></label>
					                <input type="checkbox" name="rating" id="rating5" value="5" class="rate_radio" title="5점">
					                <label for="rating5"></label>
                                 </div>
                              </div>
                                
                                
                                
		                     <%-- <span id="revWriter">${ses.user_id}</span> --%>
		                    	<textarea name="content" class="review_content" rows="6" cols="100" id="review_con"
		                          placeholder="음식과 가게에 대한 솔직한 후기를 적어주세요!"></textarea><br>
		                          <div class="review_insertBox">
		                          </div>
                                
                               <%-- <input type="text" name="review_diner_code" value="${diner.diner_code}" hidden> --%>
                              <!--   <input type="text" name="review_taste_score" value="0" hidden>
                                <input type="text" name="review_amount_score" value="0" hidden>
                                <input type="text" name="review_delivery_score" value="0" hidden> -->
                                
                                <!-- 이미지파일 등록 -->
                            <div class="review_multiplebox">
				                 <input type="file" id="review_multiple" name="files[]" accept="image/*" onchange="readFile2('files[]');" style="display: none" multiple="multiple">
				            
								 <button type="button" id="trigger">사진</button>
				                 <div id="image_container">		
				                 </div>
				         <!--          <div id="fileDelet">
								      <a href="#" id="delBtn">X</a>
								    </div> -->
				         	</div>      
									<button type="button" id="regBtn" class="review_insert">완료</button>
                            <!-- </form> --> 
                            	
                        </div>
                            
                            	<!-- 등록 후 출력 화면 -->
                         <div class="review">
                      
                            <div id="review-head">
                      
	                            	<span class="reviewer-id"></span><span class="review-time-ago"></span><a href="#">신고</a>
	                            	<div class="review-point"></div>
									<div class="review-img"></div>
									<div class="review-menu"></div>
									<div class="review-content"></div>
									
							</div>
						
                            </div>	
                        
                        
                        <!-- <div class="review">
							<div class="review-head">
								<span class="reviewer-id">gg**님</span> <span class="review-time-ago">14시간 전</span><a href="#">신고</a>
							</div>
							<div class="review-point">
								<span class="review-star-point">★★★★★</span> |&nbsp;&nbsp; 맛 <span class="star">★</span> <span class="point-taste star">5</span> &nbsp;&nbsp;양 <span class="star">★</span> <span class="point-qty star">3</span> &nbsp;&nbsp;배달 <span class="star">★</span> <span class="point-deli star">5</span>
							</div>
							<div class="review-img"></div>
							<div class="review-menu">
								<span>김치삼겹장군/1(부추 선택(（추천）부추 넣어주세요)),돈부리치킨장군/2,매콤제육장군/1(부추 선택(（추천）부추 넣어주세요))</span>
							</div>
							<div class="review-content">
								<p>맛있었어요~ 양이 좀 아쉽긴했지만 잘먹었습니다~</p>
							</div>
						</div>


						<div class="review">
							<div class="review-head">
								<span class="reviewer-id">gg**님</span> <span class="review-time-ago">14시간 전</span><a href="#">신고</a>
							</div>
							<div class="review-point">
								<span class="review-star-point">★★★★★</span> |&nbsp;&nbsp; 맛 <span class="star">★</span> <span class="point-taste star">5</span> &nbsp;&nbsp;양 <span class="star">★</span> <span class="point-qty star">3</span> &nbsp;&nbsp;배달 <span class="star">★</span> <span class="point-deli star">5</span>
							</div>
							<div class="review-img"></div>
							<div class="review-menu">
								<span>김치삼겹장군/1(부추 선택(（추천）부추 넣어주세요)),돈부리치킨장군/2,매콤제육장군/1(부추 선택(（추천）부추 넣어주세요))</span>
							</div>
							<div class="review-content">
								<p>맛있었어요~ 양이 좀 아쉽긴했지만 잘먹었습니다~</p>
							</div>
						</div> -->
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
										🔥9월7일부터 신규오픈 ''인천상륙 덮글이"🔥<br>
										🎉인천 상륙 덮글이 우리나라월드컵 16강 기원 리뷰이벤트🎉<br>
										<br>
										😍맛있고 푸짐한 한끼 식사가 되기 위해 열심히 하겠습니다<br>
										남겨 주시는 소중한 리뷰는 매장운영에 있어 큰 힘과 큰 도움이 됩니다😍<br>
										💯요청사항에 닉네임 꼭 남겨주세요 닉네임 없을경우 누락될수있어요 ㅠㅠ💯<br>
										<br>
										🎈🍳리뷰 이벤트 품목🍳🎈<br>
										1.비법양념 김말이 2P<br>
										2.고구마치즈스틱 2P<br>
										3.피카츄1P<br>
										4.스팸후라이<br>
										5.시즈닝 감자튀김<br>
										6.탄산음료 (선택) 1개<br>
										<br>
										🧡찜 꼭꼭!! 눌러 주시고 별점 5점 부탁드립니다!! 🧡<br>
										사진이 없어도 괜찮아요 😊 고객님들 께서 남겨주시는 리뷰는 항상 큰 힘이 됩니다 😆<br>
										💥개선할 점이나 아쉬운 부분이 있으셨다면 😂리뷰보다는 가게로 전화주시면 즉각 조치 하겠습니다 💥<br>
										<br>
										후식 겸 간식으로 리뷰이벤트 다양한 음식을 준비했습니다 🍨<br>
										만족하는 한끼를 위해 열심히 노력 하겠습니다.🍖<br>
										<br>
										💖인천상륙 덮글이는💖 고객님들 께서 주문해주시는 한끼 한끼 제 입으로 들어간다 생각하고 깨끗한 식재료와 위생 상태, 올바른 조리방법으로 정직하고 정성껏 조리 할 것을 약속 드립니다😄🤙<br>
										<br>
										※❤한끼 식사 든든하고 맛있게 드시고 오늘 하루도 행복한 하루되시길 진심으로 바랍니다❤<br>
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
									<span class="info-list-title">영업시간</span> <span>10:30 - 23:45</span> <br>
									<span class="info-list-title">전화번호</span> <span>050712921952</span> <br>
									<span class="info-list-title">주소</span> <span>10:30 - 23:45</span>
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
									<span class="info-list-title">결제수단</span> <span>신용가크, 현금, 요기서결제</span>
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
									<span class="info-list-title">상호명</span> <span>주식회사케이에프씨코리아</span><br>
									<span class="info-list-title">사업자등록번호</span> <span>201-81-89723</span>
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
			<div class="basket-item">
				<div class="basket-menu">핫크리스치킨 8조각</div>
				<div class="basket-menu-price">
					<div class="col-xs-6">
						<a href="">X</a> <span>21,200원</span>
					</div>
					<div class="col-xs-6">
						<a href="">-</a>&nbsp; <span>1 </span>&nbsp;<a href="">+</a>
					</div>
				</div>
			</div>
			<div class="basket-item">
				<div class="basket-menu">핫크리스치킨 8조각</div>
				<div class="basket-menu-price">
					<div class="col-xs-6">
						<a href="">X</a> <span>21,200원</span>
					</div>
					<div class="col-xs-6">
						<a href="">-</a> &nbsp;<span>1 </span>&nbsp;<a href="">+</a>
					</div>
				</div>
			</div>
			<div class="basket-item">
				<div class="basket-menu">핫크리스치킨 8조각</div>
				<div class="basket-menu-price">
					<div class="col-xs-6">
						<a href="">X</a> <span>21,200원</span>
					</div>
					<div class="col-xs-6">
						<a href="">-</a> &nbsp;<span>1 </span>&nbsp;<a href="">+</a>
					</div>
				</div>
			</div>
			<div class="basket-deli-price">
				<span>배달요금</span> <span>2,000원</span> <span>별도</span>
			</div>
			<div class="basket-total-price">
				<span>합계:</span> <span>21,200원</span>
			</div>
		
		
		</div>

	</div>

</main>
<script type="text/javascript">
let diner_code = "<c:out value='${diner.diner_code}' />";
let user_id = "<c:out value='${sessionScope.user.user_id}' />";


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
</script>
<jsp:include page="../include/footer2.jsp"></jsp:include>