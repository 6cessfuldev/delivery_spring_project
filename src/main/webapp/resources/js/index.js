$("#search-btn").click(function(){
    console.log(this);
})

function searchJuso(){
	if (!checkSearchedWord(document.form.keyword)) {
		return ;
	}
}

//특수문자, 특정문자열(sql예약어의 앞뒤공백포함) 제거
function checkSearchedWord(obj){
	if(obj.value.length >0){
		//특수문자 제거
		var expText = /[%=><]/ ;
		if(expText.test(obj.value) == true){
			alert("특수문자를 입력 할수 없습니다.") ;
			obj.value = obj.value.split(expText).join(""); 
			return false;
		}
		
		//특정문자열(sql예약어의 앞뒤공백포함) 제거
		var sqlArray = new Array(
			//sql 예약어
			"OR", "SELECT", "INSERT", "DELETE", "UPDATE", "CREATE", "DROP", "EXEC",
             		 "UNION",  "FETCH", "DECLARE", "TRUNCATE" 
		);
		
		var regex;
		for(var i=0; i<sqlArray.length; i++){
			regex = new RegExp( sqlArray[i] ,"gi") ;
			
			if (regex.test(obj.value) ) {
			    alert("\"" + sqlArray[i]+"\"와(과) 같은 특정문자로 검색할 수 없습니다.");
				obj.value =obj.value.replace(regex, "");
				return false;
			}
		}
	}
	return true ;
}

$.ajax({
    url: '/basket/amount?basket_code='+basket_code+"&basket_order_count="+amount,
    type: 'PUT',
    dataType: 'json',
    async:false, //동기식 처리
    success: function(data, status, xhr){
    	console.log(data);
    	result = 1; 
    },
    error: function(xhr, status, error){
    	alert("서버 오류");
    }
})	