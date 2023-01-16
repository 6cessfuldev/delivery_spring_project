function findAddr(){
    new daum.Postcode({
        oncomplete: function(data) {
            console.log(data);
            var roadAddr = data.roadAddress; 
            var jibunAddr = data.jibunAddress;
            document.getElementById('order_post').value = data.zonecode;
            if(roadAddr !== ''){
                document.getElementById("order_addr").value = roadAddr;
            } 
            else if(jibunAddr !== ''){
                document.getElementById("order_addr").value = jibunAddr;
            }
        }
    }).open();
}


// 결제 준비하기
var IMP = window.IMP;
IMP.init("imp88331024");


// 결제 요청하기
 function requestPay() {
      // IMP.request_pay(param, callback) 결제창 호출
      IMP.request_pay({ // param
	        pg : 'nice',
		    pay_method : 'card',
		    merchant_uid: "nictest00m", //상점에서 생성한 고유 주문번호
		    name : '치킨너게에엣',
		    amount : 100,
		    buyer_email : 'myeonk@naver.com',
		    buyer_name : '강미연',
		    buyer_tel : '010-8856-6238',
		    buyer_addr : '서울특별시 강남구 삼성동',
		    buyer_postcode : '123-456',
		    m_redirect_url : '/home', // 예: https://www.my-service.com/payments/complete/mobile
			niceMobileV2 : true // 신규 모바일 버전 적용 시 설정
  
      }, function (rsp) { // callback
      if (rsp.success) { // 결제 성공 시: 결제 승인 또는 가상계좌 발급에 성공한 경우
     	console.log("성공~!");
      } else {
        alert("결제에 실패하였습니다. 에러 내용: " +  rsp.error_msg);
      }
      });
    }
   
// 결제 정보 검증 및 저장


// 주문번호 생성
function createOrderFoodCode(){
	const date = new Date();
	const year = date.getFullYear();
	const month = String(date.getMonth() + 1).padStart(2, "0");
	const day = String(date.getDate()).padStart(2, "0");
	
	let order_food_code = year + month + day;
	for(let i=0;i<10;i++) {
		order_food_code += Math.floor(Math.random() * 8);	
	}
	return order_food_code;
}