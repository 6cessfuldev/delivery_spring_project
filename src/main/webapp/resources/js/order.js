// 결제 param
let param = {
	order_code: createOrderCode(), //상점에서 생성한 고유 주문번호
	order_pg : '',
	order_pay_method : 'card',
	order_name : order_name,
	order_amount : localStorage.getItem("orderTotalPrice"),
	order_buyer_email : user_email,
	order_buyer_name : user_name,
	order_buyer_tel : user_phone,
	order_buyer_addr : '',
	order_buyer_postcode : '',
	order_m_redirect_url : '/index', // 예: https://www.my-service.com/payments/complete/mobile
	order_niceMobileV2 : true, // 신규 모바일 버전 적용 시 설정
	order_IMP_UID : '',
	diner_code : diner_code
}

function findAddr(){
    new daum.Postcode({
        oncomplete: function(data) {
            console.log(data);
            var roadAddr = data.roadAddress; 
            var jibunAddr = data.jibunAddress;
            document.getElementById('order_post').value = data.zonecode;
            param.order_buyer_postcode = data.zonecode;
            if(roadAddr !== ''){
                document.getElementById("order_addr").value = roadAddr;
	            param.order_buyer_addr = roadAddr;
            } 
            else if(jibunAddr !== ''){
                document.getElementById("order_addr").value = jibunAddr;
                param.order_buyer_addr = jibunAddr;
            }
        }
    }).open();
}

$('#order_addrInput').change(()=>{
	param.order_buyer_addr = document.getElementById("order_addr").value+ " " + $('#order_addrInput').val();
});


// 결제 준비
var IMP = window.IMP;
IMP.init("imp88331024");


function paycoPay(){
	param.order_pg = 'payco';
	param.order_merchant_uid = 'PARTNERTEST';
}

function kakaoPay() {
	param.order_pg = 'kakaopay';
	param.order_merchant_uid = 'TC0ONETIME';
}

function tossPay(){
	param.order_pg = 'uplus';
	param.order_pay_method = 'trans'
	param.order_merchant_uid = 'tlgdacomxpay';
}

function creditCardPay(){
	param.order_pg = 'nice';
	param.order_merchant_uid = 'nictest00m';
}

function danalPay(){
	param.order_pg = 'danal';
	param.order_pay_method = 'phone';
	param.order_merchant_uid = 'A010002002';
}

function cashPay(){
	param.order_pay_method = '현장결제';
}



// 결제 요청
function requestPay() {
 	console.log(param);
 	param.order_buyer_tel = $("#order_phone").val();

	if(param.order_buyer_postcode == null || param.order_buyer_postcode == "" || param.order_buyer_addr == null || param.order_buyer_addr == "") {
		alert("배달 받으실 주소를 입력해 주세요.");
		return;
	}

	if($('#order_addrInput').val() == null || $('#order_addrInput').val() == ""){
 		alert("상세 주소를 입력해 주세요.");
		return;
 	} 
	
	if(param.order_buyer_tel == null || param.order_buyer_tel == ""){
		alert("전화번호를 입력해주세요");
		return;
	}
	
	if(param.order_pay_method == '현장결제'){
		paymentCash(param);
		return;
	}
 	
	// 서버와 화면 totalPrice 비교
	IMP.request_pay({ // param
		pg: param.order_pg,
	  	pay_method: param.order_pay_method,
	  	merchant_uid: param.order_code,
	  	name: param.order_name,
	  	amount: param.order_amount,
	   	buyer_email: param.order_buyer_email,
	   	buyer_name: param.order_buyer_name,
	  	buyer_tel: param.order_buyer_tel,
	  	buyer_addr: param.order_buyer_addr,
	  	buyer_postcode: param.order_buyer_postcode,
	  	m_redirect_url: '/index', 
	  	},
		  
		function (rsp) { // callback
		  if (rsp.success) { // 결제 성공 시: 결제 승인 또는 가상계좌 발급에 성공한 경우
			 param.order_IMP_UID = rsp.imp_uid;
			 param.order_code = rsp.merchant_uid;
			 paymentComplete(param);
			 console.log(rsp);
			 location.href="/order/myOrderList";
		  } else {
			alert("결제에 실패하였습니다. 에러 내용: " +  rsp.error_msg);
		  }
		
		}
	);
	//imp
};


// 현장에서 결제
function paymentCash(data){
	
	$.ajax({
		url: "/order/payment-cash",
        method: "POST",
        data: data,
	})
	.done(function() {
		
		alert("주문이 완료되었습니다.");
		location.replace("/order/myOrderList");
		
		
	}) // done 
    .fail(function() {
		alert("에러");
		location.replace("/");
	}) 
}


// 결제 성공 시
function paymentComplete(data){

	$.ajax({
		url: "/order/payment/complete",
        method: "POST",
        data: data,
        /*dataType: 'json',*/
        
        success: function (data, status, xhr) {
        	console.log(status);
            if (data == "1") {
                alert("서버 요청 성공");
            }
        },
        error: function () {
            alert("서버 요청 실패");
        }
	})
	

}


// 주문번호 생성
function createOrderCode(){
	const date = new Date();
	const year = date.getFullYear();
	const month = String(date.getMonth() + 1).padStart(2, "0");
	const day = String(date.getDate()).padStart(2, "0");
	
	let order_code = year + month + day;
	for(let i=0;i<10;i++) {
		order_code += Math.floor(Math.random() * 8);	
	}
	return order_code;
}