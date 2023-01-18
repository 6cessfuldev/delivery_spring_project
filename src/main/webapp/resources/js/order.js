// 결제 param
let param = {
	pg : '',
	pay_method : 'card',
	merchant_uid: '', //상점에서 생성한 고유 주문번호
	name : order_name,
	amount : localStorage.getItem("orderTotalPrice"),
	buyer_email : user_email,
	buyer_name : user_name,
	buyer_tel : user_phone,
	buyer_addr : '',
	buyer_postcode : '',
	m_redirect_url : '/index', // 예: https://www.my-service.com/payments/complete/mobile
	niceMobileV2 : true, // 신규 모바일 버전 적용 시 설정
	IMP_UID : '',
	order_food_code : createOrderFoodCode(),
	diner_code : diner_code
}

function findAddr(){
    new daum.Postcode({
        oncomplete: function(data) {
            console.log(data);
            var roadAddr = data.roadAddress; 
            var jibunAddr = data.jibunAddress;
            document.getElementById('order_post').value = data.zonecode;
            param.buyer_postcode = data.zonecode;
            if(roadAddr !== ''){
                document.getElementById("order_addr").value = roadAddr;
	            param.buyer_addr = roadAddr;
            } 
            else if(jibunAddr !== ''){
                document.getElementById("order_addr").value = jibunAddr;
                param.buyer_addr = jibunAddr;
            }
        }
    }).open();
}

$('#order_addrInput').change(()=>{
	param.buyer_addr = document.getElementById("order_addr").value+ " " + $('#order_addrInput').val();
});


// 결제 준비
var IMP = window.IMP;
IMP.init("imp88331024");


function paycoPay(){
	param.pg = 'payco';
	param.merchant_uid = 'PARTNERTEST';
}

function kakaoPay() {
	param.pg = 'kakaopay';
	param.merchant_uid = 'TC0ONETIME';
}

function tossPay(){
	param.pg = 'uplus';
	param.pay_method = 'trans'
	param.merchant_uid = 'tlgdacomxpay';
}

function creditCardPay(){
	param.pg = 'nice';
	param.merchant_uid = 'nictest00m';
}

function danalPay(){
	param.pg = 'danal';
	param.pay_method = 'phone';
	param.merchant_uid = 'A010002002';
}


// 결제 요청
function requestPay() {
 	console.log(param);

	if(param.buyer_postcode == null || param.buyer_postcode == "" || param.buyer_addr == null || param.buyer_addr == "") {
		alert("배달 받으실 주소를 입력해 주세요.");
		return;
	}

	if($('#order_addrInput').val() == null || $('#order_addrInput').val() == ""){
 		alert("상세 주소를 입력해 주세요.");
		return;
 	} 
	
	if(param.buyer_tel == null || param.buyer_tel == ""){
		alert("전화번호를 입력해주세요");
		return;
	}
 
	// 서버와 화면 totalPrice 비교
	IMP.request_pay({ // param
		pg: param.pg,
	  	pay_method: param.pay_method,
	  	merchant_uid: createOrderFoodCode(),
	  	name: param.name,
	  	amount: param.amount,
	   	buyer_email: param.buyer_email,
	   	buyer_name: param.buyer_name,
	  	buyer_tel: param.buyer_tel,
	  	buyer_addr: param.buyer_addr,
	  	buyer_postcode: param.buyer_postcode,
	  	m_redirect_url: '/index', 
	  	},
		  
		function (rsp) { // callback
		  if (rsp.success) { // 결제 성공 시: 결제 승인 또는 가상계좌 발급에 성공한 경우
			 console.log("결제 성공");
			 param.IMP_UID = rsp.imp_uid;
			 param.merchant_uid = rsp.merchant_uid;
			 paymentComplete(param);
		  } else {
			alert("결제에 실패하였습니다. 에러 내용: " +  rsp.error_msg);
		  }
		
		}
	);
	//imp
};

//결제 성공 시
function paymentComplete(data){

	$.ajax({
		url: "/order/payment/complete",
        method: "POST",
        data: data,
        dataType: 'json',
        
        success: function (result) {
            if (result == "1") {
                alert("서버 요청 성공");
            }
        },
        error: function () {
            alert("서버 요청 실패");
        }
	})
	

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