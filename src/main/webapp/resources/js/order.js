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
