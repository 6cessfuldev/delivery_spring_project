const swiper = new Swiper('.swiper', {
    // Optional parameters
    direction: 'horizontal',
    loop: false,
  
    // // If we need pagination
    // pagination: {
    //   el: '.swiper-pagination',
    // },
  
    // Navigation arrows
    navigation: {
      nextEl: '.swiper-button-next',
      prevEl: '.swiper-button-prev',
    },
  
    // And if we need scrollbar
    scrollbar: {
      el: '.swiper-scrollbar',
    },
    width : 150
});

$("#cate-"+category).parent().addClass("active");

$(".category").children('ul').children('li').click(function(){

	let cat = $(this).children('div').attr('id');
	cat = cat.substring(5,cat.length);
	console.log(cat);
    $("#category").val(cat);
    $("#addr-form").submit();
})

//셀렉 태그 값 변경 시 졍렬 순서 기준에 따라 새로 리스트 가져오기
$("#search-option").on("change", ()=>{
	let index = $("#search-option option").index($("#search-option option:selected"));
	console.log(index);
	$("#order").val(index);
	$("#addr-form").submit();
})

let modalAmount = 1;

function openModal(food_code){
  modalAmount=1;
  $("#modalTrigger").click();
  $.ajax({
    url: '/choice/list/'+food_code,
    type: 'GET',
    dataType: 'json',
    success: function(data, status, xhr){
      console.log(data);
      spreadChoice(data);
      
    },
    error: function(xhr, status, error){
      console.log(error);
    }
  })
  
}

//총 주문 계산을 변수
let modalFoodPrice = 0; 
let	modalOptionPrice = 0;


function spreadChoice(data){

	console.log(data);

	let save_dir = data.filevo.file_save_dir;
	let splitArr = save_dir.split(`\\`);
    console.log(splitArr);
	save_dir = splitArr[0]+"/"+splitArr[1]+"/"+splitArr[2];
	let src = "/upload/"+save_dir+"/"+data.filevo.file_uuid+"_"+data.filevo.file_name;
	console.log(src);

	$(".modal-img").css("background-image", "url(" + src + ")");

	$(".food-title").text(data.foodvo.food_name);
	$(".food-description").text(data.foodvo.food_intro);
	$("#modal-price").text(data.foodvo.food_price+"원");
	modalFoodPrice = data.foodvo.food_price;
	
    const box = $('.item-list');
	
	let choiceCnt = 0;
    for(let cvo of data.clist){
      
      const newDiv = $('<div class="form-check">');
      const input = $('<input class="form-check-input" type="checkbox" value="">');
      const label = $('<label class="form-check-label" >');
      label.click(()=>{
      	input.click();
      })
      input.click( ()=>{
      	if(input.is(':checked')){
      		modalOptionPrice+=cvo.choice_price;
      		calculate();
      	}else{
      		modalOptionPrice -=cvo.choice_price;
      		calculate();
      	}
      })
     
      input.val(cvo.choice_code);
      label.text(cvo.choice_content);
      const div = $('<div class="choice-price">');
      div.text((cvo.choice_price>0?"+"+cvo.choice_price:cvo.choice_price));
      
      newDiv.append(input);
      newDiv.append(label);
      newDiv.append(div);
  
      box.append(newDiv);
    }
	
	calculate();
}

function count(p){
  console.log("click");
  if(p == 'plus' && modalAmount<99){
  	modalAmount++;
  }else if(p == 'minus' && modalAmount>1){
    modalAmount--;
  }
  $(".modal-amount").text(modalAmount);
  calculate();
}

$(".add-basket").click(function(){
	console.log("add basket");
	$(".btn-close").click();
})

$(".modal-order").click(function(){
	console.log("order");
	$(".btn-close").click();
})

function calculate(){
	let modalTotal = (modalFoodPrice+modalOptionPrice)*modalAmount;
	console.log(modalTotal);
	
	$("#modal-total").text(modalTotal+"원");
}


