var code = "";

// 이메일 인증창
let once = 0;

const add_checkBox = () => {

    const user_email = document.getElementById('user_email').value;
    if (user_email == "" || user_email == null) {
        alert("이메일을 입력해주세요.");
        return;
    }

    alert("입력하신 이메일로 인증번호가 전송되었습니다.");

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
    newP.innerHTML += `<input type='button' class='cBtn' value='인증'></div>`;
    newP.innerHTML += `<div class='clearfix'></div>`;
    newP.innerHTML += `<span id="mail_check_input_box_warn"></span></div>`;
    box.appendChild(newP);

    // 인증번호 비교
    $(".cBtn").click(function () {
        console.log("click cBtn");
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

}

// 인증번호 이메일 전송
$(".eBtn").click(function () {
    console.log("click");

    var email = $(".email_input").val(); // 입력한 이메일

    $.ajax({

        type: "GET",
        url: "mailCheck?email=" + email,
        success: function (data) {

            // console.log("data : " + data);

            code = data;
            console.log(code);
        }

    });

});

// 유효성 검사
document.querySelector('.signupBtn').addEventListener('click', function () {
    const user_email = document.getElementById('user_email').value;
    const user_pw = document.getElementById('user_pw').value;
    const user_pwCheck = document.getElementById('user_pwCheck').value;
    const user_name = document.getElementById('user_name').value;
    const user_birth = document.getElementById('user_birth').value;
    const user_phone = document.getElementById('user_phone').value;
    const user_nick = document.getElementById('user_nick').value;

    if (user_email == "" || user_email == null) {
        alert("이메일을 입력해주세요.");
        return;
    } else if (user_pw == "" || user_pw == null) {
        alert("비밀번호를 입력해주세요.");
        return;
    } else if (user_pwCheck == "" || user_pwCheck == null) {
        alert("비밀번호 재확인을 입력해주세요.");
        return;
    } else if (user_name == "" || user_name == null) {
        alert("이름을 입력해주세요.");
        return;
    } else if (user_birth == "" || user_birth == null) {
        alert("생년월일을 입력해주세요.");
        return;
    } else if (user_phone == "" || user_phone == null) {
        alert("핸드폰 번호를 입력해주세요.");
        return;
    } else if (user_nick == "" || user_nick == null) {
        alert("닉네임을 입력해주세요.");
        return;
    } else if(user_pw != user_pwCheck){
        alert("비밀번호가 일치하지 않습니다.");
        return;
    }

    $(".wrap-input").submit();
})