var code = "";

// 이메일 인증창
let once = 0;

const add_checkBox = () => {

    var email = $(".email-input").val(); // 입력한 이메일

    if (email == "" || email == null) {
        alert("이메일을 입력해주세요.");
        return;
    }


    // 이메일 유효성 검사
    const regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
    if(email.match(regExp) != null){

        // 이메일 전송
        $.ajax({
    
            type: "GET",
            url: "mailCheck?email=" + email,
            success: function (data) {
    
                code = data;
                console.log(code);
            }
    
        });

    }

    else {
        alert("이메일을 확인해주세요.");
        return;
    }


    once++;
    if (once > 1) {
        return;
    }

    const box = document.getElementById("box");
    const newP = document.createElement('p');
    newP.innerHTML = `<div class='mail_check_wrap'>`;
    newP.innerHTML += `<div class='mail_check_input_box'>`;
    newP.innerHTML += `<input type='text' class='checkNum mail_check_input' placeholder='이메일로 전송된 인증번호를 입력해주세요.'></div>`;
    newP.innerHTML += `<div class='mail_check_button'>`;
    newP.innerHTML += `<input type='button' class='cerBtn' value='인증'></div>`;
    newP.innerHTML += `<div class='clearfix'></div>`;
    newP.innerHTML += `<span id="mail_check_input_box_warn"></span>`;
    newP.innerHTML += `<div id='resetPw'></div></div>`;
    box.appendChild(newP);

    
    // 인증번호 비교
    let res = 0;

    $(".cerBtn").click(function () {
        console.log("click cerBtn");
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

        const checkMsg = document.getElementById('mail_check_input_box_warn').innerHTML;
        if (checkMsg == "인증번호가 일치합니다.") {

            res++;
            if (res > 1) {
                return;
            }

            const resetPw = document.getElementById("resetPw");
            const insertP = document.createElement('p');

            insertP.innerHTML = `<div class="reset_box">`;
            insertP.innerHTML += `<button type='button' class='resetBtn' onclick="location.href='/member/update_pw'">비밀번호 재설정</button>`;
            insertP.innerHTML += `</div>`;
            resetPw.appendChild(insertP);

            // 이메일 정보 담기
            localStorage.setItem('email', $(".email-input").val());

        }

    });

}