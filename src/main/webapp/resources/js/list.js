//주소 검색
function searchAddress(keyword){
	var places = new kakao.maps.services.Places();
	var callback = function(result, status) {
		if (status === kakao.maps.services.Status.OK) {
			return result;
		}
	};
	places.keywordSearch(keyword, callback, options);
	return null;
}

/* HTML5 geolocation */
function getLocation() {
  if (navigator.geolocation) {
	navigator.geolocation.getCurrentPosition(showPosition);
  } else { 
	alert("Geolocation is not supported by this browser.");
  }
}
/* geolocation에서 가져온 좌표값으로 카카오맵api에서 주소 검색 후 가장 가까운 주소 5개를 검색해 검색창 확장 div에 뿌리기 */
function showPosition(position) {
  
  // 카카오맵에서 주소 검색
	var geocoder = new kakao.maps.services.Geocoder();
	var coord = new kakao.maps.LatLng(position.coords.latitude, position.coords.longitude);
	var callback = function(result, status) {
		if (status === kakao.maps.services.Status.OK) {
			console.log(result[0]);
			
			if(searchAddress(result[0].address.address_name)==null){
				searchAddr(result[0].address.region_1depth_name+" "+result[0].address.region_2depth_name+" "+result[0].address.region_3depth_name);
			}else{
				searchAddr(result[0].address.address_name);
			}
			$("#search-input").val(result[0].address.address_name);
		}
	};
	geocoder.coord2Address(coord.getLng(), coord.getLat(), callback);
}

let exten = $(".search-extention");

var options = {
    size: 5
}

function searchAddr(keyword){
 

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
		let keyword = $("#search-input").val();
		searchAddr(keyword);
	})

	$("#search-input").on("click keyup", function(){
		let keyword = $("#search-input").val();
		searchAddr(keyword);
	})
	
	function addSearchBox(result){	
		if(result==null) {
			console.log("data null");
			return;
		}
		
		if(result.length>0){
			exten.html("");
			exten.show();
			for(let i=0; i<result.length; i++ ){

				let addr = result[i];
				
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

$("#cate-"+category).parent().addClass("active");

$(".category").children('ul').children('li').click(function(){

	let cat = $(this).children('div').attr('id');
	cat = cat.substring(5,cat.length);
	console.log(cat);
    $("#category").val(cat);
    $("#addr-form").submit();
})

console.log($("#category-option"));
$("#category-option option[value="+category+"]").attr('selected', true); 

//좁은 화면에서 나타나는 카테고리 셀렉 태그
//선택 시 해당 카테고리 값으로 리스트 재요청
$("#category-option").on("change", ()=>{

	let optionVal = $("#category-option").val();
	$("#category").val(optionVal);
	$("#addr-form").submit();
})

//셀렉 태그 값 변경 시 졍렬 순서 기준에 따라 새로 리스트 가져오기
$("#search-option").on("change", ()=>{
	let index = $("#search-option option").index($("#search-option option:selected"));
	console.log(index);
	$("#order").val(index);
	$("#addr-form").submit();
})

//infinte scolling
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

let listCnt = 1;
function addList(){
	 

    const list = document.querySelector(".registered");

    let add = "";
	if(listCnt<0) return;

    getListMoreFromServer(listCnt).then(result => {
        console.log(result);
        if(result!=null && result.length>0){
            for(let i=0; i<result.length; i++){
                if(i%2==0){
                    add+=`<div class="one-row justify-content-center">`;
                }
                
                //일단 이미지 없으면 띄우지 않기
                if(result[i].fivo == null) continue;
                
                let save_dir = result[i].fivo.file_save_dir;
				let splitArr = save_dir.split(`\\`);
                console.log(splitArr);
				save_dir = splitArr[0]+"/"+splitArr[1]+"/"+splitArr[2];
				let src = "/upload/"+save_dir+"/"+result[i].fivo.file_uuid+"_"+result[i].fivo.file_name;
				
                add+=`<div class="diner-card bg-white" id="diner-card" style="cursor:pointer;" onclick='location.href="/diner/detail?diner_code=${result[i].dvo.diner_code}"'>`;
                add+=`<div class="diner-img">`;
                add+=`<img src="${src}"	 alt="" width="80px" height="80px"></div>`;
                add+=`<div class="diner-body"><h5 class="diner-title">${result[i].dvo.diner_name}</h5>`;
                add+=`<p class="diner-text"><span class="score">★3.8</span> | 리뷰 1902 | 사장님댓글 791 </p>`;
                add+=`<p class="diner-text"><span class="del-option">${result[i].dvo.diner_method_pay}</span> |`;
                add+=`<span>${result[i].dvo.diner_min_pay}원 이상 배달</span></p>`;
                add+=`<p class="delivery-time"> 22분 </p></div></div>`;

                if(i%2==1 || i+1 == result.length){
                    add+=`</div>`;
                }
            }
            list.innerHTML+=add;
			listCnt++;	
        }
		listCnt=-1;
    })

}

async function getListMoreFromServer(listCnt){

    try {
        let resp = await fetch('/diner/moreList/'+listCnt);
        let result = resp.json();
        return result;

    } catch (error) {
        console.log(error);
    }
}