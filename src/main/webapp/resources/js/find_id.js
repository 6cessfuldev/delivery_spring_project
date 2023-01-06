var code = "";

// 이메일 전송
$(".sendBtn").click(function () {
    console.log("click");

    var email = $(".email-input").val(); // 입력한 이메일

    if (email == "" || email == null) {

        alert("이메일을 입력해주세요.");
        return;

    }

    // 이메일 유효성 검사
    const regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;

    if (email.match(regExp) != null) {

        $.ajax({

            type: "GET",
            url: "mailCheck?email=" + email,
            success: function (data) {

                code = data;
                console.log(code);
            }

        });

        alert("입력하신 이메일로 인증번호가 전송되었습니다.");

    }

    else {
        alert("이메일을 확인해주세요.");
        return;
    }




});


// 인증번호 비교
$(".cerBtn").click(function () {

    var email = $(".email-input").val(); // 입력한 이메일

    if (email == "" || email == null) {
        alert("이메일을 입력해주세요.");
        return;
    }

    console.log("click findBtn");
    var inputCode = $(".mail_check_input").val();        // 입력코드    
    console.log(inputCode);
    var checkResult = $("#mail_check_input_box_warn");    // 비교 결과 

    if (inputCode == code) {
        checkResult.html("인증번호가 일치합니다.");
        checkResult.attr("class", "correct");
    } else {
        checkResult.html("인증번호를 다시 확인해주세요.");
        checkResult.attr("class", "incorrect");
    }

});

// 아이디 찾기
let once = 0;

$(".findBtn").click(function () {
    const checkMsg = document.getElementById('mail_check_input_box_warn').innerHTML;
    if (checkMsg == "인증번호가 일치합니다.") {

        once++;
        if (once > 1) {
            return;
        }

        let user_email = $('#user_email').val();
        console.log(user_email);

        $.ajax({

            url: '/member/find_id',
            type: 'post',
            data: { "user_email": user_email },

            success: function (result) {
                if (result != null) {
                    console.log(result);
                    const id_hint = document.getElementById("id_hint");
                    const newP = document.createElement('p');

                    newP.innerHTML = `<div class="hint_box">`;
                    newP.innerHTML += `<p class='hintMsg'>회원님의 아이디는 <font id="getId" size="5" color="green"></font> 입니다.</p>`;
                    newP.innerHTML += `</div>`;

                    id_hint.appendChild(newP);

                    $("#getId").html(result);
                }
            },
            error: function () {
                alert("서버요청실패");
            }
        })


    } else {
        alert("이메일 인증을 해주세요.");
    }

})