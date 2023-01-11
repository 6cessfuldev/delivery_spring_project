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
    if(rating.rate == 0){
        alert("별점을 입력해주세요.");
        return;
    }else{
        regist();
        // getReviewList(diner_code);
    }
});

//로그인 중인 아이디 얻어오기
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
        //ajax에서 formData를 넘겨줘야함
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
                } else {        
                    formData.append('review_diner_code', diner_code);
                    formData.append('review_content', revText);
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
                // 서버와 통신을 성공했다면 서버가 다시 주는 데이터  
                if (result === 'Success'){ //=== 타입 변환X
                    $('#review_multiple').val('');
                    // 파일 선택지 비우기                     
                    document.querySelector("div#image_container").innerHTML='';
                    $('#review_con').val('');
                    // 글 영역 비우기 
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
    
    // function Rating(){};
    // Rating.prototype.rate = 0;
    // Rating.prototype.setRate = function(newrate){
    //     this.rate = newrate;
    //     let items = document.querySelectorAll('.rate_radio');
    //     items.forEach(function(item, idx){
    //         if(idx < newrate){
    //             item.checked = true;
    //         }else{
    //             item.checked = false;
    //         }
    //     });
    // }
    // let rating = new Rating();
     
    // document.addEventListener('DOMContentLoaded', function(){
    //     //별점선택 이벤트 리스너
    //     document.querySelector('.rating').addEventListener('click',function(e){
    //         let elem = e.target;
    //         if(elem.classList.contains('rate_radio')){
    //             rating.setRate(parseInt(elem.value));
    //         }
    //     })
    // });


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
        if(result.length > 0){ 
                let count = 0;
            for(let reviewDTO of result){ 

                let div = `<div>`;
                div += `<div class="reviewer-id>`;
                div += `<span class="review-time-ago"></span><a href="#">신고</a>`;
                div += `<div class="review-point"></div>`;
                //console.log(reviewDTO.flist);
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
