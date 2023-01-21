<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>먹어요</title>
<jsp:include page="../include/header.jsp"></jsp:include>
<link type="text/css" rel="stylesheet" href="/resources/css/order.css">
<!-- iamport.payment.js -->
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

</head>
<body>
   <div id="order_wrapper">
        <div id="order_content">
            <div id="order_img">
                <a href="#" id="order_iconA"><img id="order_icon" src="/resources/source/home.png"></a>
             </div><br><br><br><br><br><br>
            <div id="order">
                <p id="order_p1">주문자 정보</p>
                <input id="order_post"  type="text" placeholder="주소입력(클릭)" readonly onclick="findAddr()">
                <input id="order_addr" type="text" readonly> <br><br>
                <input type="text" id="order_addrInput" placeholder="상세주소를 입력하세요."><br>
                <p id="order_p2">연락처 ${user.user_phone }</p>
                <input type="checkbox" id="order_check">
                <label for="order_check" id="order_checkLabel"><span>안심번호 사용</span></label>
                <button id="order_num">변경</button>
       		</div>
       		<br><br>
	        <div id="order_checkDiv">
	            <br>
	            <input type="checkbox" id="order_checkbox">&nbsp;&nbsp;&nbsp;일회용 수저, 포크 안 주셔도 돼요&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	            <input type="checkbox" id="order_checkbox">&nbsp;&nbsp;&nbsp;문 앞에 놓고, 벨 눌러주세요
	            <br>
	            <p id="order_p3">사장님!&nbsp;&nbsp;
	            <input type="text" id="order_req" placeholder="예)맵게 해주세요"></p>
	            <p id="order_p3">배달원님!&nbsp;&nbsp;
	            <select id="order_reqSel">
	                <option selected="selected">요청사항 없음</option>
	                <option>벨 누르지 말고 노크해주세요</option>
	                <option>도착하기 전에 전화해주세요</option>
	            </select></p>
	        </div>
	        <br><br>
	        <div id="order_pay">
	            <p id="order_payP">바로결제</p>
	                <button onclick="paycoPay()" type="button" id="order_payBut">페이코</button>
	                <button onclick="kakaoPay()" type="button" id="order_payBut">카카오페이</button>
	                <button onclick="tossPay()" type="button" id="order_payBut2">계좌이체</button><br>
	                <button onclick="creditCardPay()" type="button" id="order_payBut2">신용카드</button>
	                <button onclick="danalPay()" type="button" id="order_payBut2">핸드폰결제</button>
	                <br><br>
	                <p id="order_payP">만나서결제</p>
	            <button type="button" onclick="cashPay()" id="order_cash">현금</button>
	            <button type="button" onclick="" id="order_card">카드</button>
	        </div>
	        <div id="order_payInfo">
	            <ul id="order_ul">
	                <li id="order_li">주문금액 ${orderTotalPrice }원</li>
	                <li id="order_li">배달비 ${diner.diner_delivery_fee}원</li>
	                <li id="order_li">총 결제금액 ${orderTotalPrice + diner.diner_delivery_fee}원</li>
	            </ul>
	            <button onclick="requestPay()" type="button" id="order_payAll">결제하기</button>
	        </div>
        </div>
    </div>
    <jsp:include page="../include/footer2.jsp"></jsp:include>
    <script>
        let user_email = '<c:out value="${user.user_email}"/>';
        let user_name = '<c:out value="${user.user_name}"/>';
        let user_phone = '<c:out value="${user.user_phone}"/>';
        let basketList = '<c:out value="${basketList}"/>';
        let order_name = '<c:out value="${order_name}"/>';
        let diner_code = '<c:out value="${diner.diner_code}"/>';
    </script>
<script type="text/javascript" src="/resources/js/order.js"></script>
</body>
