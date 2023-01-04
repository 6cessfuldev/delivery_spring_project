$("#search-btn").click(function(){

    .$ajax({
        type : 'post',
        url : '/https://business.juso.go.kr/addrlink/addrLinkApi.do',
        headers : {
            "Content-Type" : "appication/json",
            "X-HTTP-Method-Override" : "POST"
        },
        dataType : 'text',
        data : JSON.stringify({
            "no" : no,
            "name" : name,
            "nick" : nick
          }),

    })

})

$("#search-btn").click(function(){
    console.log(this);
})

// 적용예 (api 호출 전에 검색어 체크) 
function searchJuso(){
	if (!checkSearchedWord(document.form.keyword)) {
		return ;
	}
}


/*
모의 해킹 테스트 시 검색API를 호출하시면 IP가 차단 될 수 있습니다. 
주소검색API를 제외하시고 테스트 하시기 바랍니다.
*/
/*도로명주소 API 호출시 검색어에 특수문자 또는 OR, INSERT, UNION 등 SQL예약어가 포함될 경우
보안장비가 SQL INJECTION공격으로 간주하여 해당 IP를 차단시킬 수 있습니다.
사용자분들은 API호출시 검색어 필터링를 적용하여 주시기 바랍니다.*/

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

