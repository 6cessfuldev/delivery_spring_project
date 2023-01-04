var code = "";

// 이메일 인증창
let once = 0;

const add_textbox = () => {
    once++;
    if (once > 1) {
        return;
    }

    const box = document.getElementById("box");
    const newP = document.createElement('p');
    newP.innerHTML = `<div class='mail_check_wrap'>`;
    newP.innerHTML += `<div class='mail_check_input_box' id='mail_check_input_box_false'>`;
    newP.innerHTML += `<input type='text' class='checkNum mail_check_input' placeholder='이메일로 전송된 인증번호를 입력해주세요.' disabled='disabled'></div>`;
    newP.innerHTML += `<div class='mail_check_button'>`;
    newP.innerHTML += `<input type='button' class='cBtn' value='check'></div>`;
    newP.innerHTML += `<div class='clearfix'></div>`;
    newP.innerHTML += `<span id='mail_check_input_box_warn'></span></div>`;
    // newP.innerHTML = "<div class='mail_check_wrap'><div class='mail_check_input_box' id='mail_check_input_box_false'><input type='text' class='checkNum mail_check_input' placeholder='이메일로 전송된 인증번호를 입력해주세요.' dis></div><div class='mail_check_button'><input type='button' class='cBtn' value='check'></div><div class='clearfix'></div><span id='mail_check_input_box_warn'></span></div>";
    box.appendChild(newP);

}

// 인증번호 이메일 전송
$(".eBtn").click(function () {
    console.log("click");

    var email = $(".email_input").val(); // 입력한 이메일
    var checkBox = $(".mail_check_input");        // 인증번호 입력란
    var boxWrap = $(".mail_check_input_box");  // 인증번호 입력란 박스

    $.ajax({

        type: "GET",
        url: "mailCheck?email=" + email,
        success: function (data) {

            // console.log("data : " + data);
            checkBox.attr("disabled", false);
            boxWrap.attr("id", "mail_check_input_box_true");
            code = data;

        }

    });

});

// 인증번호 비교
$(".mail_check_input").blur(function () {

    var inputCode = $(".mail_check_input").val();        // 입력코드    
    var checkResult = $("#mail_check_input_box_warn");    // 비교 결과 

    if (inputCode == code) {
        checkResult.html("인증번호가 일치합니다.");
        checkResult.attr("class", "correct");
    } else {
        checkResult.html("인증번호를 다시 확인해주세요.");
        checkResult.attr("class", "incorrect");
    }

});


// 내용 미입력 시 경고창
document.querySelector('.signupBtn').addEventListener('click', function () {
    const user_email = document.getElementById('user_email').value;
    const user_pw = document.getElementById('user_pw').value;
    const user_pwCheck = document.getElementById('user_pwCheck').value;
    const user_name = document.getElementById('user_name').value;
    const user_birth = document.getElementById('user_birth').value;
    const user_phone = document.getElementById('user_phone').value;
    const user_nick = document.getElementById('user_nick').value;

    if (user_email == "") {
        alert("이메일을 입력해주세요.");
        return;
    } else if (user_pw == "") {
        alert("비밀번호를 입력해주세요.");
        return;
    } else if (user_pwCheck == "") {
        alert("비밀번호 재확인을 입력해주세요.");
        return;
    } else if (user_name == "") {
        alert("이름을 입력해주세요.");
        return;
    } else if (user_birth == "") {
        alert("생년월일을 입력해주세요.");
        return;
    } else if (user_phone == "") {
        alert("핸드폰번호를 입력해주세요.");
        return;
    } else if (user_nick == "") {
        alert("닉네임을 입력해주세요.");
        return;
    }
})