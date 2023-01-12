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

document.getElementById('trigger').addEventListener('click', ()=> {
    document.getElementById('review_multiple').click();
});


$("#regBtn").click(function(){ 
        regist();
});

function regist(){
    // const user_id = '${sessionScope.login.userId}';
    const user_id = "test";
    let file = $('#review_multiple').val();
   console.log("파일:"+file);
    const regExp = new RegExp("\.(exe|sh|bat|msi|dll|js)$");
    const regExpImg = new RegExp("\.(jpg|jpeg|png|gif)$");
    const maxSize = 1024*1024*20; //20MB


    // if(user_id === ''){
    //     alert('로그인이 필요한 서비스 입니다.');
    //         return;
    // }else{
        // const revText = document.getElementById('review_con').value;
        const formData = new FormData();
        const data = $('#review_multiple');
        const revText = document.getElementById('review_con').value;
        const star = document.querySelector('input[name="rating"]:checked').value;
        
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
                } else {        
                    formData.append('review_diner_code', diner_code);
                    formData.append('review_content', revText);
                    formData.append('review_score', star);
                    formData.append('review_reg_date', data);


                    // 여기에서 review_score_avg를 ,,, append,, 해줘야하나,,? 근데 평균을 계산해서,,
                    // formData.append('review_user_id', user_id);
                }
                
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
                alert("업로드에 실패했습니다.");
                
            }
        });// end ajax
        //}if문
        
    }
    



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
        const star1 = '★☆☆☆☆';
        const star2 = '★★☆☆☆';
        const star3 = '★★★☆☆';
        const star4 = '★★★★☆';
        const star5 = '★★★★★';
        review.innerHTML = ""; 
        if(result.length > 0){ 
                let count = 0;
            for(let reviewDTO of result){ 
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

                let div = `<div>`;
                div += `<div class="reviewer-id>`;
                div += `<span class="review-time-ago">${reviewDTO.rvo.review_reg_date}</span><a href="#">신고</a>`;
                div += `<div class="review-point">${star0} ${reviewDTO.rvo.review_score}</div>`;
                div += `<div class="review-menu"></div>`;
		        div += `<div class="review-content">${reviewDTO.rvo.review_content}</div>`;
		        div += `</div>`;
		        review.innerHTML += div;
                if(reviewDTO.flist.length > 0){      
                    for(let img of reviewDTO.flist){
                        console.log(img);
               		 let save_dir = img.review_img_save_dir.split('\\');
               		 let dir = save_dir[0]+"/"+save_dir[1]+"/"+save_dir[2];
               		 console.log("/upload/"+dir+"/"+img.review_img_uuid+"_"+img.review_img_name);
               		 let real = "/upload/"+dir+"/"+img.review_img_uuid+"_"+img.review_img_name;
                        let imgTag = document.createElement("img");
                        imgTag.id = 'review_img';
                        imgTag.src = real;
                        review.childNodes[count].append(imgTag);
	                }
                    count++;
                }
            }
        } else {
            let div = `<div>첫번째 리뷰를 작성해주세요!</div>`;
            review.innerHTML += div;
        }
        
    })
}


let modalAmount = 1;

function openModal(food_code){
  modalAmount=1;
  $("#modalTrigger").click();
  $.ajax({
    url: '/choice/list/'+food_code,
    type: 'GET',
    dataType: 'json',
    success: function(data, status, xhr){

      spreadChoice(data);
      
    },
    error: function(xhr, status, error){
      console.log(error);
    }



  })
  
}

function spreadChoice(data){

	console.log(data);
    const box = $('.item-list');

    for(let cvo of data){
      console.log(cvo.choice_code);
      const newDiv = $("<div>");
      const input = $('<input class="form-check-input" type="checkbox" value="'+ cvo.choice_code +'" id="flexCheckDefault">');
      const label = $('<label class="form-check-label" for="flexCheckDefault">');
      // const input = $('<input>');
      // const label = $('<label>');
      input.val(cvo.choice_code);
      label.text(cvo.choice_content);
      const div = $("<div>");
      div.text(cvo.choice_price);
      
      newDiv.append(input);
      newDiv.append(label);
      newDiv.append(div);
  
      box.append(newDiv);
    }

    console.log(box.children('div:eq(0)'));
}


function count(p){
  console.log("click");
  if(p == 'plus' && modalAmount<99){
  	modalAmount++;
  }else if(p == 'minus' && modalAmount>1){
    modalAmount--;
  }
  $(".modal-amount").text(modalAmount);
}

$(".add-basket").click(function(){
	console.log("add basket");
	$(".btn-close").click();
})

$(".modal-order").click(function(){
	console.log("order");
	$(".btn-close").click();
})


