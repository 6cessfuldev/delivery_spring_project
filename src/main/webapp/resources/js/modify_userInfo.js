document.querySelector('.updateBtn').addEventListener('click', function () {
 
    const user_id = $('#user_id').val();
    const new_pw = $('#user_pw').val();
    const new_pwCheck = $('#user_pwCheck').val();
    const new_phone = $('#user_phone').val();

    if (new_pw == "" || new_pw == null) {
        alert("비밀번호를 입력해주세요.");
        return;
    } else if (new_pwCheck == "" || new_pwCheck == null) {
        alert("비밀번호 재확인을 입력해주세요.");
        return;
    } else if (new_pw != new_pwCheck) {
        alert("비밀번호가 일치하지 않습니다.");
        return;
    } else if (new_phone == "" || new_phone == null) {
        alert("핸드폰번호를 입력해주세요.");
        return;
    }

    // 비밀번호
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

    // 핸드폰번호
     var phoneReg = /^01(?:0|1|[6-9])-(?:\d{3}|\d{4})-\d{4}$/;
     if (!phoneReg.test(new_phone)) {
         alert("핸드폰 번호를 형식에 맞게 입력해주세요.");
         return;
     }

    // 회원 정보 변경
    $.ajax({

        url: '/member/modify_userInfo',
        type: 'post',
        data: { "user_id": user_id, "new_pw": new_pw, "new_phone": new_phone },

        success: function (result) {
            if (result > 0) {
                alert("회원 정보가 수정되었습니다 :-)");
                $(".wrap-input").submit();
            }
        },
        error: function () {
            alert("서버 요청 실패");
        }
        
    })
    

})