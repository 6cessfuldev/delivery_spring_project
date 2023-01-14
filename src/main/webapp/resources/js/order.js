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


//결제 api

// 주문번호 만들기
function createOrderNum(){
	const date = new Date();
	const year = date.getFullYear();
	const month = String(date.getMonth() + 1).padStart(2, "0");
	const day = String(date.getDate()).padStart(2, "0");
	
	let orderNum = year + month + day;
	for(let i=0;i<10;i++) {
		orderNum += Math.floor(Math.random() * 8);	
	}
	return orderNum;
}


// 카드 결제
function paymentCard(data) {
	// 모바일로 결제시 이동페이지
	//const pathName = location.pathname;
	//const href = location.href;
	//const m_redirect = href.replaceAll(pathName, "");
		
	IMP.init("imp55477503"); 
		
	// 3.결제 요청하기
	IMP.request_pay({ // param
		pg: "html5_inicis",
	  	pay_method: data.,
	  	merchant_uid: data.,
	  	name: data.,
	  	amount: data.,
	   	buyer_email: "",
	   	buyer_name: "",
	  	buyer_tel: data.,
	  	buyer_addr: data. + " " + data.,
	  	buyer_postcode: data.,
	  	m_redirect_url: m_redirect, 
  	}, 
	function (rsp) { // callback
		if (rsp.success) {
         // 결제 성공 시 로직
		 // 결제고유번호
	        data.impUid = rsp.imp_uid;
		// 주문번호
	        data.merchant_uid = rsp.merchant_uid;
	        paymentComplete(data);  
			
		} else {
          // 결제 실패 시 로직,
		}
	});
}

// 계산 완료
function paymentComplete(data) {
	
	 $.ajax({
		url: "?",
        method: "POST",
        data: data,
	})
	.done(function(result) {
		messageSend();
        swal({
			text: result,
			closeOnClickOutside : false
		})
		.then(function(){
			location.replace("/orderList");
		})
	}) // done 
    .fail(function() {
		alert("에러");
		location.replace("/");
	}) 
}  
