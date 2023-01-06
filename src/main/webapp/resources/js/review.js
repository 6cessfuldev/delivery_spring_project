//이미지파일 비동기 추가
	$.ajax({
					url:'<c:url value="/delivery/upload" />',
					type : 'post',
					data : formData,
					contentType:false,
					//필수
					processData:false,
					//필수
					
					success: function(result){
						// 서버와 통신을 성공했다면 서버가 다시 주는 데이터  
						if (result === 'Success'){
							$('#file').val('');
							// 파일 선택지 비우기							
							$('#content').val('');
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
				

async function postReviewToServer(revData){
    try {
        const url = '/review/post'

        const config = {
            method : 'post',
            headers : {
                'content-type' : 'application/json; charset=utf-8'
            },
            body : JSON.stringify(revData)
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

document.getElementById('regBtn').addEventListener('click',()=>{
    const revText = document.getElementById('review_con').value;
    console.log(revText);
    if(revText == null || revText == ''){
        alert("리뷰를 입력해주세요.");
        review_con.focus();
        return false;
    } else {
        let revData = {
            diner_code : diner_codeVal,
            review_user_email : document.getElementById('revWriter').innerText,
            review_content : revText
        };
        console.log(revData);
        postReviewToServer(revData).then(result =>{
            if(result > 0){
                alert("리뷰를 등록했습니다");
            }
            getReviewList(revData.diner_code);
        });
    }

});
//화면 새로고침 하고 뿌려주는거
async function spreadReviewFromServer(diner_code){
    console.log(diner_code);
    try {
        const resp = await fetch('/review/'+diner_code); 
        const result = await resp.json(); 
        console.log("문자"+result);
        return result;
    } catch (error) {
    	 console.log("콘솔 에러");
        console.log(error);
    }
}

function getReviewList(diner_code){
    spreadReviewFromServer(diner_code).then(result =>{ // bno 주고 result 받아옴
        console.log("결과"+result);
    })
}

