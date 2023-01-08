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
//확장자 체크
// function fileSizeValidation(fileName, fileSize){
//     if(regExp.test(fileName)){
//         return 0;
//     }else if(!regExpImg.test(fileName)){
//         return 0;
//     }else if(fileSize > maxSize){
//         return 0;
//     }else{
//         return 1;
//     }
// }


$("#regBtn").click(function(){
    regist();
})

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

                    formData.append('file',$(review_multiple)[i].files[j]);
                }
            }
        }

        const revText = document.getElementById('review_con').value;
        if(revText == null || revText == ''){
            alert("리뷰를 입력해주세요.");
            review_con.focus();
            return false;
        } else {
            formData.append('revText', revText);
            alert("리뷰를 등록했습니다");
        }

        console.log(revText);

        // document.addEventListener("change", (e)=>{
        //     console.log("test");
        //     if(e.target.id == "review_multiple"){
        //         console.log("changed");
        //         document.getElementById('regBtn').disabled = false;
        //         const fileObject = document.getElementById('review_multiple').files;
        //         console.log(fileObject);

        //         let div = document.getElementById("image_container");

        //         if(isOk == 0){
        //         document.getElementById('regBtn').disabled = true;
        //         }
        //     }
        // })

        //이미지파일 비동기 추가
        $.ajax({
            url:'/review/upload',
            // url: '/review/upload',
            type : 'post',
            data : formData,
            contentType:false,
            //필수
            processData:false,
            //필수
            enctype : 'multipart/form-data',
            
            success: function(result){
                // 서버와 통신을 성공했다면 서버가 다시 주는 데이터  
                if (result === 'Success'){
                    $('#review_multiple').val('');
                    // 파일 선택지 비우기							
                    $('#review_con').val('');
                    // 글 영역 비우기 
                }
                else{
                    alert("업로드에 실패했습니다.");
                }
            },
            error : function(){
                alert("업로드에 실패했습니다.");
                
            }
        });// end ajax
                    
    }
// }
        
function setThumbnail(event) {
    var reader = new FileReader();

    reader.onload = function(event) {
    	var img = document.createElement("img");
     	img.setAttribute("src", event.target.result);
        console.log(event.target.result[0]);
        console.log(event.target.result[1]);
        document.querySelector("div#image_container").appendChild(img);
    };

    reader.readAsDataURL(event.target.files[0]);
   
}

