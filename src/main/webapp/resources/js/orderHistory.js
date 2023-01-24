$('#submit').click( (e)=>{
    e.preventDefault();
    let keyword = $('#keyword').val();
    
    if(keyword == null || keyword == ''){
        alert("검색어를 입력해주세요.");
        return;
    } else {
    	console.log($('#search-form'));
    	$('#submit').unbind('click').click();
    }
})