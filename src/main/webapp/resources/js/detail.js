const swiper = new Swiper('.swiper', {
    // Optional parameters
    direction: 'horizontal',
    loop: false,
  
    // // If we need pagination
    // pagination: {
    //   el: '.swiper-pagination',
    // },
  
    // Navigation arrows
    navigation: {
      nextEl: '.swiper-button-next',
      prevEl: '.swiper-button-prev',
    },
  
    // And if we need scrollbar
    scrollbar: {
      el: '.swiper-scrollbar',
    },
    width : 150
});

//카테고리 버튼 색입히기
$("#cate-"+category).parent().addClass("active");
//카테고리 클릭 시 폼 태그 전송
$(".category").children('ul').children('li').click(function(){

	let cat = $(this).children('div').attr('id');
	cat = cat.substring(5,cat.length);
	console.log(cat);
    $("#category").val(cat);
    $("#addr-form").submit();
})

//셀렉 태그 값 변경 시 졍렬 순서 기준에 따라 새로 리스트 가져오기
$("#search-option").on("change", ()=>{
	let index = $("#search-option option").index($("#search-option option:selected"));
	console.log(index);
	$("#order").val(index);
	$("#addr-form").submit();
})

// 찜하기
document.getElementsByClassName('dib-field')[0].addEventListener('click',()=>{
    
    if(user_id == ''){
        alert("로그인이 필요합니다.");
        return;
    }
    
    $.ajax({
        url: '/dibs/update/'+diner_code,
        success: function(result) {
            if(result=="1"){            
                let div = document.getElementsByClassName('dib-field')[0];
                if(div.innerText=="♡"){
                    div.innerText="♥";

                } else{
                    div.innerText="♡";
                }
            
            }else{
                alert("서버 에러.");
            }
        },
        error: function(){
            alert("서버 에러");
        }
    })
})


//사진 업로드 버튼
document.getElementById('trigger').addEventListener('click', ()=> {
    document.getElementById('review_multiple').click();
});


//리뷰 regist 메소드 클릭 이벤트
$("#regBtn").click(function(){ 
    regist();
});

function regist(){
    let file = $('#review_multiple').val();
   console.log("파일:"+file);
    const regExp = new RegExp("\.(exe|sh|bat|msi|dll|js)$");
    const regExpImg = new RegExp("\.(jpg|jpeg|png|gif)$");
    const maxSize = 1024*1024*20; //20MB

    if(user_id === ''){
        alert('로그인이 필요한 서비스 입니다.');
            return;
    }else{
        const formData = new FormData();
        const data = $('#review_multiple');
        const revText = document.getElementById('review_con').value;
       
        console.log('폼 데이터 : ' + formData);
        console.log('data : ' + data );
        console.log(data[0]); 
        console.log(data[0].files);
        console.log(data[0].files[0]);
        console.log(data[0].files[1]);
        
        for(let i = 0; i < data.length; i++){
            if(data[i].files.length>0){
                for(let j = 0; j < data[i].files.length; j++){
                    console.log(data[i].files[j]);
                    formData.append('file',data[i].files[j]);   
                }
                if(revText == null || revText == ''){
                    alert("리뷰를 입력해주세요.");
                    review_con.focus();
                    return false;
                }else if($("input:radio[name='rating']").is(":checked")==false){
                    alert("별을 선택해주세요.");
                }else if(data[i].files.length > 3){
                    alert("파일 개수가 초과되었습니다.");
                    history.go(-0.5);    
                }else {    
                    const star = document.querySelector('input[name="rating"]:checked').value;    
                    formData.append('diner_code', diner_code);
                    formData.append('review_content', revText);
                    formData.append('review_score', star);
                    formData.append('user_id', user_id);
                    //formData.append('review_order_code',orderCode);
                }
            }else{
                if(revText == null || revText == ''){
                    alert("리뷰를 입력해주세요.");
                    review_con.focus();
                    return false;
                }else if($("input:radio[name='rating']").is(":checked")==false){
                    alert("별을 선택해주세요.");   
                }else {    
                    const star = document.querySelector('input[name="rating"]:checked').value;    
                    formData.append('diner_code', diner_code);
                    formData.append('review_content', revText);
                    formData.append('review_score', star);
                    formData.append('user_id', user_id);
                    //formData.append('review_order_code',orderCode);
                }
            }
        for (var pair of formData.entries()) {
            console.log(pair[0]+ ', ' + pair[1]);
          }
        $.ajax({
            url:'/review/upload',
            type : 'post',
            data : formData,
            contentType:false,
            //필수
            processData:false,
            //필수
            enctype : 'multipart/form-data',
            
            success: function(result){
                if (result === 'Success'){ //=== 타입 변환X
                    $('#review_multiple').val('');                   
                    document.querySelector("div#image_container").innerHTML='';
                    $('#review_con').val('');
                    console.log(result);
                    alert("리뷰를 등록했습니다.");
                    getReviewList(diner_code);
                }else{
                    alert("업로드에 실패했습니다.");
                }
            },
            error : function(){
                console.log("error");
                alert("업로드에 실패했습니다.");
                
            }
        });// end ajax
        }
    }

}

//리뷰 뿌리기
async function spreadReviewServer(diner_code){
    console.log(diner_code);
    try {
        const resp = await fetch('/review/list/'+diner_code);
        const result = await resp.json();
        console.log(result);
        return result;
    } catch (error) {
        console.log(error);
    }
}

function getReviewList(diner_code){
    spreadReviewServer(diner_code).then(result =>{ 
        console.log(result);
        const review = document.getElementById('review-head');
        review.innerHTML = "";
        const star1 = '★☆☆☆☆';
        const star2 = '★★☆☆☆';
        const star3 = '★★★☆☆';
        const star4 = '★★★★☆';
        const star5 = '★★★★★';
       
        if(result.length > 0){ 
                let count = 0;
            for(let reviewDTO of result){ 
                let img_box=document.createElement('div');
                 const star = reviewDTO.rvo.review_score;
                 console.log(star);

                 let star0='';
                 for(let i=0; i<reviewDTO.rvo.review_score; i++){
                    star0 += star1;
                 }

                 switch(reviewDTO.rvo.review_score){
                    case 1 : 
                        star0 = star1;
                        break;
                    case 2 :
                        star0 = star2;
                        break;
                    case 3 :
                        star0 = star3;
                        break;
                    case 4 :
                        star0 = star4;
                        break;
                    case 5 :
                        star0 = star5;
                        break;
                 }   
                 let div = `<div class="reviewBox" data-review_code="${reviewDTO.rvo.review_code}">`;
                 div += `<div class="reviewer-id">${reviewDTO.rvo.user_id}</div>`;
                 div += `<button class="deleteBtn" type="button">X</button>`;
                 div += `<span class="review-time-ago">${reviewDTO.rvo.review_reg_date}<a class="review-a" href="#">신고</a></span>`;
                 div += `<div class="review-point">${star0}<span class="starScore">${reviewDTO.rvo.review_score}</span></div>`;
                 div += `<div class="review-menu"></div>`;
                 div += `<div class="review-content">${reviewDTO.rvo.review_content}</div>`;
                 div += `<br>`;
                 if(reviewDTO.flist.length > 0){      
                    for(let img of reviewDTO.flist){
                      console.log(img);
                      let save_dir = img.review_img_save_dir.split('\\');
                      let dir = save_dir[0]+"/"+save_dir[1]+"/"+save_dir[2];
                      console.log("/upload/"+dir+"/"+img.review_img_uuid+"_"+img.review_img_name);
                      let real = "/upload/"+dir+"/"+img.review_img_uuid+"_"+img.review_img_name;
                      let imgTest = '<img src="'+real+'" id="review_img">';
                      div += imgTest;
                      }
                  }
                 //div += '<button type="button" id="bossComment" class="bossComment">답글달기</button>';
                 div += `<input value='' id='bossCommentText' class='bossCommentText' placeholder="사장님 답글을 남겨주세요!"></input>`;
                 div += '<button type="button" id="bossCommentBtn" class="bossCommentBtn">답글달기</button>';
                 div += `<br><br><br><br><br></div>`             
                 let bossDiv = `<div class="bossCommentPrint"><span class="bosstext" >사장님 💬&nbsp&nbsp&nbsp&nbsp</span>`;
                 bossDiv += `${reviewDTO.rvo.review_boss_comment}</div>`;
                 review.innerHTML += div;
                 if(reviewDTO.rvo.review_boss_comment != null){
                    review.innerHTML += bossDiv;  
                 }
                 
                

                 count++;        
                 
 
            }
           } else {
               let div = `<div>첫번째 리뷰를 작성해주세요!</div>`;
               review.innerHTML += div;
           }

       })

}


//사장님댓글
function commentPost(bossData) {
   // const commentData = $('.bossCommentText').val();
    const commentData = bossData.review_boss_comment;
   //console.log(commentData);
    console.log(bossData);
   //console.log(bossData.review_code);
    if(commentData == null || commentData == ''){
        alert("댓글 등록을 실패했습니다.");
        return;
    }
    $.ajax({
        url: '/review/bossComment/'+bossData.review_code,
        type: 'POST',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(bossData),
        success: function(data, status, xhr){
        if(data == 1){
            alert("사장님 댓글을 등록했어요!");
            getReviewList(diner_code);    
        }
    },
        error: function(error, status, xhr){
        console.log(error);
        }
      
    });

}
$(document).on("click",".bossCommentBtn",function (e){
	if(e.target.classList.contains('bossCommentBtn')){
	    let div = e.target.closest('div');
	    console.log(div.classList);
        let review_codeVal = div.dataset.review_code;
        const input = e.target.previousSibling;
        console.log(input.classList);
        const commentData = input.value;
        console.log(commentData);
        const bossData = {
             review_boss_comment : commentData,
             review_code : review_codeVal
        }
     console.log(bossData);
     commentPost(bossData);
    
    }
	
 });





//삭제
async function removeReviewServer(review_code){
    try {
        const url ='/review/delete/'+review_code;
        const config = {
            method : 'delete'
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;

    } catch (error) {
        console.log("removeReviewServer error");
        console.log(error);
    }
}


document.addEventListener('click', (e)=>{
  if(e.target.classList.contains('deleteBtn')){
      let div = e.target.closest('div');
      console.log(div.classList);
      let review_codeVal = div.dataset.review_code;
      console.log(review_codeVal);
         removeReviewServer(review_codeVal).then(result => {
            if(result>0){
                if(user_id == user_id){
                alert("리뷰를 삭제했어요!");
                }
            }
            getReviewList(diner_code);
         })  
    }
})


//모달창 총 주문 계산을 위한 변수
let modalAmount = 1;
let modalFoodPrice = 0; 
let	modalOptionPrice = 0;

function openModal(food_code){
  modalAmount=1;
  modalFoodPrice = 0;
  modalPotionPrice = 0;
  $(".modal-amount").text(modalAmount);
  $("#modalTrigger").click();
  $.ajax({
    url: '/choice/list/'+food_code,
    type: 'GET',
    dataType: 'json',
    success: function(data, status, xhr){
      console.log(data);
      spreadChoice(data);
      addBasketEvent(data);
      addModalForm(data);   
    },
    error: function(xhr, status, error){
      console.log(error);
    }
  })
}

//모달창에서 주문하기 버튼 폼태그 데이터 입력
function addModalForm(data){
	
	let modalForm = $("#modal-form");
	
	$("#modal_food_code").val(data.foodvo.food_code);

}

//모달창 오픈 시 장바구니 추가하기 버튼 이벤트 
function addBasketEvent(data){	
	$(".add-basket").off("click");
	//모달창 장바구니 추가하기 버튼 클릭 이벤트
	$(".add-basket").on("click",()=> {
		
		let options = $(".form-check-input");
	    console.log(options);
	    let optionList = [];
	    for(let option of options){
	    	if(option.checked){
                let choiceObj = {
                    choice_code : option.value
                }
	    		optionList.push(choiceObj);
			}        	
	    }
	    
	    const basketData = {
	    	user_id : user_id,
	      	food_code : data.foodvo.food_code,
	      	basket_order_count : modalAmount,
	      	choiceList : optionList
	    };
	    console.log(basketData);
	    
	    postBasketToServer(basketData);
	    $(".btn-close").click();	    
	})
}

// 장바구니에 등록할 데이터
function postBasketToServer(basketData){
	
	if(basketData.user_id == null || basketData.user_id == ""){
		location.href="/member/login";
	}
	
	$.ajax({
        url: '/basket/add/',
        type: 'POST',
        traditional: true,
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(basketData),
        success: function(data, status, xhr){
	        if(data == 1){
		        basketReload();
	        }else if(data == 2){
	        	alert("이미 등록된 메뉴입니다.");
	        }else if(data == 3){
	        	alert("다른 음식점에서 이미 담은 메뉴가 있습니다. 담긴 메뉴를 취소하고 다시 추가하세요.");
	        }else{
	        	location.href='/member/login'; 
	        }
	    },
        error: function(xhr, status, error){
        console.log(error);
        }
    })	
}
//모달창 주문하기 버튼 클릭 이벤트
$(".modal-order").click(function(){
	
	let modalForm = $("#modal-form");
	let choices = $(".form-check-input");
	let cnt = 0;
	for(let choice of choices){
		if(choice.checked){
			console.log("choice :"+choice.value);
			let choiceForm = $('<input type="text" name="choiceList['+ cnt++ +'].choice_code" value="'+choice.value+'" hidden>');
			modalForm.append(choiceForm);
		}
	}
	$("#modal_order_food_count").val($(".modal-amount").text());
	console.log($("#modal-form"));
	modalForm.submit();
	
})

// 모달창 내용 채우기
function spreadChoice(data){

	let save_dir = data.filevo.file_save_dir;
	let splitArr = save_dir.split(`\\`);

	save_dir = splitArr[0]+"/"+splitArr[1]+"/"+splitArr[2];
	let src = "/upload/"+save_dir+"/"+data.filevo.file_uuid+"_"+data.filevo.file_name;
	
	$(".modal-img").css("background-image", "url('" + src + "')");

	$(".food-title").text(data.foodvo.food_name);
	$(".food-description").text(data.foodvo.food_intro);
	$("#modal-price").text(data.foodvo.food_price+"원");
	modalFoodPrice = data.foodvo.food_price;
	
    const box = $('.item-list');
    box.html(" ");
	
	let choiceCnt = 0;
    for(let cvo of data.clist){
      
      const newDiv = $('<div class="form-check">');
      const input = $('<input class="form-check-input" type="checkbox" value="">');
      const label = $('<label class="form-check-label" >');
      label.click(()=>{
      	input.click();
      })
      input.click( ()=>{
      	if(input.is(':checked')){
      		modalOptionPrice+=cvo.choice_price;
      		calculate();
      	}else{
      		modalOptionPrice -=cvo.choice_price;
      		calculate();
      	}
      })
     

      input.val(cvo.choice_code);
      label.text(cvo.choice_content);
      const div = $('<div class="choice-price">');
      div.text((cvo.choice_price>0?"+"+cvo.choice_price:cvo.choice_price));
      
      newDiv.append(input);
      newDiv.append(label);
      newDiv.append(div);
  
      box.append(newDiv);
    }

	
	calculate();

}

function count(p){

  if(p == 'plus' && modalAmount<99){
  	modalAmount++;
  }else if(p == 'minus' && modalAmount>1){
    modalAmount--;
  }
  $(".modal-amount").text(modalAmount);
  calculate();
}


// 모달창 총 금액 계산
function calculate(){
	let modalTotal = (modalFoodPrice+modalOptionPrice)*modalAmount;

	$("#modal-total").text(modalTotal+"원");

}

$(".order-btn").hide();
//장바구니 데이터 요청과 장바구니 새로고침 메서드 호출
function basketReload(){
	console.log("Reload");
	if(user_id == null || user_id == "") return;
    console.log(user_id);
    $.ajax({
        url: '/basket/list/',
        type: 'POST',
        data: {user_id : user_id},
        dataType: 'json',
        success: function(data, status, xhr){
        	console.log(data);
        	
			refreshBasket(data);
        
        },
        error: function(xhr, status, error){
        console.log(error);
        }
    })	
}

function refreshBasket(data){
	let basket = $("#basket-menu-list");
	basket.html(" ");
	console.log(data.length);
	//장바구니 값이 없을 경우 주문 버튼 사라짐
	if(data.length<1 || data == null){
		$(".order-btn").hide();
	}else{
		$(".order-btn").show();
	}

	let form = $("#order-form");
	
	for(let bdto of data){
	
		let basketItem = $('<div class="basket-item">');
		
		let basketMenu = $('<div class="basket-menu">');
		basketMenu.text(bdto.basket_content);
		basketItem.append(basketMenu);
		let basketMenuPrice = $('<div class="basket-menu-price">');
		let div1 = $('<div class="col-xs-6">');
		let xBtn = $('<a>');
		xBtn.css("cursor", "pointer");
		xBtn.click(()=>{
			let basket_code = bdto.basket_code;
			removeBasket(basket_code, basketItem);
		});
		xBtn.text('X');
		let basketPrice = $('<span class="px-2 fs-7 basket_price">');
		basketPrice.text(bdto.total_price*bdto.basket_order_count+"원");
		div1.append(xBtn);
		div1.append(basketPrice);
		basketMenuPrice.append(div1);
		let div2 = $('<div class="col-xs-6">');
		let minusBtn = $('<a>');
        minusBtn.css("cursor", "pointer");
		minusBtn.text(' ─ ');
		minusBtn.click(()=>{
			if(Number(amount.text())>1){
				if(changeAmount(bdto.basket_code,Number(amount.text())-1, basketItem)!=1) {
					console.log("1");
					return;
				} 
				amount.text(Number(amount.text())-1);
				basketPrice.text(bdto.total_price*Number(amount.text())+"원");
			}
		});
		div2.append(minusBtn);
		let amount = $('<span class="px-2 fs-7">');
		amount.text(bdto.basket_order_count);
		div2.append(amount);
		let plusBtn = $('<a>');
        plusBtn.css("cursor", "pointer");
		plusBtn.text(' ┼ ');
		plusBtn.click(()=>{
			if(Number(amount.text())<99){
				if(changeAmount(bdto.basket_code,Number(amount.text())+1, basketItem)!=1) {
					return; 
				}
				amount.text(Number(amount.text())+1);
				basketPrice.text(bdto.total_price*Number(amount.text())+"원");
				
			}
		});
		div2.append(plusBtn);
		basketMenuPrice.append(div2);
		basketItem.append(basketMenuPrice);
		basket.append(basketItem);
	}

}

function changeAmount(basket_code, amount, basketItem){
	let result = 0;
	$.ajax({
	    url: '/basket/amount?basket_code='+basket_code+"&basket_order_count="+amount,
	    type: 'PUT',
	    dataType: 'json',
	    async:false, //동기식 처리
	    success: function(data, status, xhr){
	    	console.log(data);
	    	result = 1; 
	    },
	    error: function(xhr, status, error){
	    	alert("서버 오류");
	    }
	})	
	return result;

}

function removeBasket(basket_code, basketItem){
	 $.ajax({
        url: '/basket/'+basket_code,
        type: 'delete',
        dataType: 'json',
        async: false,
        success: function(data, status, xhr){
        	console.log(data);
        	basketItem.remove();
        	basketReload();    
        },
        error: function(xhr, status, error){
        	alert("서버 오류");
        }
    })	
}

var target = document.getElementById('basket-menu-list');

// 변경을 감지했을 때 실행할 부분
var observer = new MutationObserver(mutations => {
  let priceList = document.getElementsByClassName('basket_price');
  let sum = 0;
  for(let price of priceList){
    let text = price.innerText;
    console.log(text.substring(0, text.length-1));
  	sum += Number(text.substring(0, text.length-1));
  }
  localStorage.setItem("orderTotalPrice", sum);
  document.getElementById("total").innerText = sum+"원";
	
  let count = priceList.length;
  document.getElementById("basket").innerText = "장바구니("+count+")";
  

});

// 감지 설정
var config = {
  childList: true,	// 타겟의 하위 요소 추가 및 제거 감지
  attributes: true,	// 타켓의 속성 변경를 감지
  characterData: true,	// 타겟의 데이터 변경 감지
  subtree: true,	// 타겟의 자식 노드 아래로도 모두 감지
  attributeOldValue: false,	// 타겟의 속성 변경 전 속성 기록
  characterDataOldValue: false	// 타겟의 데이터 변경 전 데이터 기록
};

observer.observe(target, config);

function order(){
	const orderPrice = Number(localStorage.getItem("orderTotalPrice"));
	if(orderPrice < Number(diner_min_pay)){
		alert("최소 주문 금액은 "+diner_min_pay+"원입니다.");
		return;
	}
	location.href="/order/page";	
}