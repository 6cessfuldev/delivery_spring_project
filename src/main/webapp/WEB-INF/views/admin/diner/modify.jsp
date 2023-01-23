<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../include/header.jsp"></jsp:include>
		
<style>
	#dinerForm>input[type="text"]{
		width:700px;
	}
</style>

<table class="table table-info m-2 p-2" border=1>
<form action="/admin/diner/update" method="post" id="dinerForm" enctype="multipart/form-data">
    <tr>
        <th>음식점 코드</th>
        <td><input type="text" name="diner_code" value="${diner.diner_code}" readonly></td>
    </tr>
    <tr>
        <th>음식점 로고</th>
        
        <td>
	        <img id="file-img" src="/upload/${fn:replace(file.file_save_dir, '\\','/')}/${file.file_uuid}_${file.file_name}" width="100px"> 
	        <input id="file-input" type="file" name="file"><input type="text" name="file_code" value="${diner.file_code}" hidden>
        </td>
    </tr>
    <tr>
        <th>음식점 이름</th>
        <td><input type="text" name="diner_name" value="${diner.diner_name}"></td>
    </tr>
    <tr>
        <th>음식점 주소</th>
        <td><input type="text" id ="addr" name="diner_address" value="${diner.diner_address}"></td>
    </tr>
    <tr>
        <th>음식점 최소 결제 금액</th>
        <td><input type="number" name="diner_min_pay" value="${diner.diner_min_pay}"></td>
    </tr>
    <tr>
        <th>음식점 공지사항</th>
        <td><textarea rows="3" cols="30" name="diner_notice">${diner.diner_notice}</textarea></td>
    </tr>
        <tr>
        <th>영업 시간</th>
        <td><input type="text" name="diner_open_time" value="${diner.diner_open_time}"> ~ <input type="text" name="diner_close_time" value="${diner.diner_close_time}"></td>
    </tr>
    <tr>
        <th>결제수단</th>
        <td><input type="text" name="diner_method_pay" value="${diner.diner_method_pay}"><br></td>
    </tr>
    <tr>
        <th>상호명</th>
        <td><input type="text" name="diner_business_name" value="${diner.diner_business_name}"></td>
    </tr>
    <tr>
        <th>사업자등록번호</th>
        <td><input type="text" name="diner_company_num" value="${diner.diner_company_num}"><br></td>
    </tr>
    <tr>
    	<th>배달비</th>
    	<td><input type="number" name="diner_delivery_fee"></td>	
    </tr>
    <tr>
        <th>카테고리</th>
        <td>
			<input type="checkbox" name="category" id="aa" value="aa">1인분주문
			<input type="checkbox" name="category" id="ff" value="ff">프랜차이즈
			<input type="checkbox" name="category" id="hh" value="hh">치킨
			<input type="checkbox" name="category" id="pp" value="pp">피자/양식
			<input type="checkbox" name="category" id="cc" value="cc">중국집
			<input type="checkbox" name="category" id="kk" value="kk">한식
			<input type="checkbox" name="category" id="jj" value="jj">일식/돈까스
			<input type="checkbox" name="category" id="mm" value="mm">족발/보쌈
			<input type="checkbox" name="category" id="nn" value="nn">야식
			<input type="checkbox" name="category" id="tt" value="tt">분식
			<input type="checkbox" name="category" id="dd" value="dd">카페/디저트
			<input type="checkbox" name="category" id="ss" value="ss">편의점/마트
    	</td>
    </tr>
	<tr>
		<td colspan="2" class="text-end">
			<input type="text" id="diner_addr_lng" name="diner_addr_lng" hidden=true>
			<input type="text" id="diner_addr_lat" name="diner_addr_lat" hidden=true>
			<button type="button" id="dinerInputBtn">수정</button>
		</td>
	</tr>
	</form>	
</table>
	
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=e8b552c46357c215f64b284e4da814a9&libraries=services"></script>

<script>
	const diner_category = '<c:out value="${diner.diner_category}" />';
	if(diner_category.includes("aa")) $('#aa').prop("checked", true);
	if(diner_category.includes("ff")) $('#ff').prop("checked", true);
	if(diner_category.includes("hh")) $('#hh').prop("checked", true);
	if(diner_category.includes("pp")) $('#pp').prop("checked", true);
	if(diner_category.includes("cc")) $('#cc').prop("checked", true);
	if(diner_category.includes("kk")) $('#kk').prop("checked", true);
	if(diner_category.includes("jj")) $('#jj').prop("checked", true);
	if(diner_category.includes("mm")) $('#mm').prop("checked", true);
	if(diner_category.includes("nn")) $('#nn').prop("checked", true);
	if(diner_category.includes("tt")) $('#tt').prop("checked", true);
	if(diner_category.includes("dd")) $('#dd').prop("checked", true);
	if(diner_category.includes("ss")) $('#ss').prop("checked", true);

 	$("#dinerInputBtn").click(function(){
		console.log("clicked");
		
		var geocoder = new kakao.maps.services.Geocoder();
		var callback = function(result, status) {
		    if (status === kakao.maps.services.Status.OK) {
		    	console.log(result[0]);
		        $("#diner_addr_lng").val(result[0].x);
		        $("#diner_addr_lat").val(result[0].y);
		        $("#dinerForm").submit();
		    }else{
		    	alert("주소 입력 에러");
		    }
		};
		var addr = $("#addr").val();
		geocoder.addressSearch(addr, callback);
	})
	
	$("#file-input").change(function(){
		$("#file-file").hide();
	})
	
</script>


<jsp:include page="../include/footer.jsp"></jsp:include>