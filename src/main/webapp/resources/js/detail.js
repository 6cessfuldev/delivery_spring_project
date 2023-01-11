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
    url: '/food/option/'+food_code,
    type: 'GET',
    dataType: 'json',
    success: function(data, status, xhr){
      console.log(data);
      console.log(status);
      
    },
    error: function(xhr, status, error){
      console.log(error);
    }
  })
}

function count(p){
  console.log("click");
  if(p == 'plus' && modalAmount<99){
  	modalAmount++;
  }else if(p == 'minus' && modalAmount>1){
    modalAmount--;
  }
  $(".modal-amount").text(modalAmount);
}

$(".add-basket").click(function(){
	console.log("add basket");
	$(".btn-close").click();
})

$(".modal-order").click(function(){
	console.log("order");
	$(".btn-close").click();
})