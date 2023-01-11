document.querySelector('.changeBtn').addEventListener('click', function () {

    const new_pw = $('#new_pw').val();
    const new_pwCheck = $('#new_pwCheck').val();

    if (new_pw == "" || new_pw == null) {
        alert("비밀번호를 입력해주세요.");
        return;
    } else if (new_pwCheck == "" || new_pwCheck == null) {
        alert("비밀번호 재확인을 입력해주세요.");
        return;
    } else if (new_pw != new_pwCheck) {
        alert("비밀번호가 일치하지 않습니다.");
        return;
    } else {
        var pw = new_pw;
        var num = pw.search(/[0-9]/g);
        var eng = pw.search(/[a-z]/ig);
        var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

        if (pw.length < 8 || pw.length > 20) {
            alert("8자리 ~ 20자리 이내로 입력해주세요.");
            return;
        } else if (pw.search(/\s/) != -1) {
            alert("비밀번호는 공백 없이 입력해주세요.");
            return;
        } else if (num < 0 || eng < 0 || spe < 0) {
            alert("영문, 숫자, 특수문자를 혼합하여 입력해주세요.");
            return;
        }
    }

    // 이메일 정보 받아오기
    var getEmail = localStorage.getItem('email');
    console.log(getEmail);

    // 비밀번호 변경
    $.ajax({

        url : '/member/update_pw',
        type : 'post',
        data : {"getEmail" : getEmail , "new_pw" : new_pw },

        success: function(result){
            if(result > 0){
                $("#updateMsg").html('비밀번호가 변경되었습니다 :-)');
                $('#new_pw').hide();
                $('#new_pwCheck').hide();
                $('.changeBtn').hide(); 
            } else {
                alert("기존 비밀번호와 동일합니다.");
            }
        },
        error: function(){
            alert("서버 요청 실패");
        }

    })
    

})