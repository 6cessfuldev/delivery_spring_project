let exten = $(".search-extention");

var options = {
    size: 5
}

function searchAdrr(){
    let keyword = $("#search-input").val();

    if(keyword=="") {
        exten.hide();
        exten.html("");
    }
        
        
    console.log(keyword);
    if (!checkSearchedWord(keyword)) {
        return ;
    }

    if(keyword.length<3) return;

    $("#keyword").val(keyword);

    var places = new kakao.maps.services.Places();

    var callback = function(result, status) {
        if (status === kakao.maps.services.Status.OK) {
            addSearchBox(result);
        }
    };
    places.keywordSearch(keyword, callback, options);
}

	$("#search-btn").click(function(){
		searchAdrr();
	})

	$("#search-input").on("click keyup", function(){
		searchAdrr();
	})
	
	function addSearchBox(jsonStr){	
		if(jsonStr.results.juso==null) {
			console.log("data null");
			return;
		}
		
		if(jsonStr.results.juso.length>0){
			exten.html("");
			exten.show();
			for(let i=0; i<jsonStr.results.juso.length; i++ ){

				let addr = jsonStr.results.juso[i];
				
				let classname = "addr"+i;
				
				let div = $('<div>').prop({className: classname});
				div.addClass('addr');
				div.click(function(){
					console.log(addr);
					exten.hide();
					$("#search-input").val(addr.address_name);	
					$("#jibunAddr").val(addr.address_name);
					$("#x").val(addr.x);
					$("#y").val(addr.y);
					$("#addr-form").submit();
					
					
				})
				
				
				exten.append(div);

				div.append(
					$('<div>').prop({
						className: 'jibun-addr',
						innerHTML: addr.address_name
					})
				);
				div.append(
					$('<div>').prop({
						className: 'doro-addr',
						innerHTML: (addr.road_address_name!="" ? "[도로명]"+addr.road_address_name:" ")
					})
				);
			}
		}
	}

	function checkSearchedWord(keyword){
	if(keyword.length >0){
		//특수문자 제거
		var expText = /[%=><]/ ;
		if(expText.test(keyword) == true){
			alert("특수문자를 입력 할수 없습니다.") ;
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
			
			if (regex.test(keyword) ) {
				alert("\"" + sqlArray[i]+"\"와(과) 같은 특정문자로 검색할 수 없습니다.");
				return false;
			}
		}
	}
	return true ;
	}


$(".category").children('ul').children('li:eq('+category+')').addClass("active");

$(".category").children('ul').children('li').click(function(){

	let cat = $(this).children('div').attr("data-cat");
	console.log(cat);
    $("#category").val(cat);
    $("#addr-form").submit();
})


const listEnd = document.querySelector("#endList");
const option = {
    root: null,
    rootMargin: "0px 0px 0px 0px",
    thredhold: 0,
}

const onIntersect = (entries, observer) => { 
    // entries는 IntersectionObserverEntry 객체의 리스트로 배열 형식을 반환합니다.
    entries.forEach(entry => {
        if(entry.isIntersecting){
            addList();
        }
    });
};

const observer = new IntersectionObserver(onIntersect, option);
observer.observe(listEnd);

let listCnt = 0;
function addList(){

    const list = document.querySelector(".registered");

    let add = "";
    getListMoreFromServer().then(result => {
        console.log(result.length);
        if(result!=null && result.length>0){
            console.log("add case");
            for(let i=0; i<result.length; i++){
                console.log(i);
                if(i%2==0){
                    add+=`<div class="one-row justify-content-center">`;
                }

                add+=`<div class="diner-card bg-white" id="diner-card">`;
                add+=`<div class="diner-img">`;
                add+=`<img src="/resources/source/dinerimg.PNG" alt="" width="80px" height="80px"></div>`;
                add+=`<div class="diner-body"><h5 class="diner-title">${result[i].diner_name}</h5>`;
                add+=`<p class="diner-text"><span class="score">★3.8</span> | 리뷰 1902 | 사장님댓글 791 </p>`;
                add+=`<p class="diner-text"><span class="del-option">${result[i].diner_method_pay}</span> |`;
                add+=`<span>${result[i].diner_min_pay}원 이상 배달</span></p>`;
                add+=`<p class="delivery-time"> 22분 </p></div></div>`;

                if(i%2==1 || i+1 == result.length){
                    add+=`</div>`;
                }
            }
            console.log(list.innerHTML);
            list.innerHTML+=add;
            console.log(list.innerHTML);
        }
    })

}

async function getListMoreFromServer(){

    try {
        let resp = await fetch("/diner/moreList");
        let result = resp.json();
        return result;

    } catch (error) {
        console.log(error);
    }
}