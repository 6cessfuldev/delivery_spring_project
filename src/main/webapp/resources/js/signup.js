var code = "";

// 이메일 중복 검사
// $('#user_email').keyup(function () {

//     let user_email = $('#user_email').val();
//     console.log(user_email);

//     $.ajax({
//         url: "/member/userEmailCheck",
//         type: "post",
//         data: { "user_email": user_email },
//         dataType: 'json',
//         success: function (result) {
//             if (result == 1) {
//                 $("#email_feedback").html('이미 사용중인 이메일입니다.');
//                 $("#email_feedback").attr("class", "incorrect");
//             } else {
//                 $("#email_feedback").html('사용할 수 있는 이메일입니다.');
//                 $("#email_feedback").attr("class", "correct");
//             }
//         },
//         error: function () {
//             alert("서버요청실패");
//         }
//     })
// })

// 이메일 인증
let once = 0;

const add_checkBox = async () => {

    const user_email = $('#user_email').val();

    if (user_email == "" || user_email == null) {
        alert("이메일을 입력해주세요.");
        return;
    }

    // 이메일 중복 검사
    await $.ajax({
        url: "/member/userEmailCheck",
        type: "post",
        data: { "user_email": user_email },
        dataType: 'json',
        success: function (result) {
            if (result == 1) {
                $("#email_feedback").html('이미 사용중인 이메일입니다.');
                $("#email_feedback").attr("class", "incorrect");
            } else {
                $("#email_feedback").html('사용할 수 있는 이메일입니다.');
                $("#email_feedback").attr("class", "correct");
            }
        },
        error: function () {
            alert("서버요청실패");
        }
    })


    const emailCheckMsg = $("#email_feedback").text();

    if (emailCheckMsg == "이미 사용중인 이메일입니다.") {

        alert("이메일을 확인해주세요.");
        return;

    } else {

        // 이메일 유효성 검사
        const regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;

        if(user_email.match(regExp) != null){

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
    
            // 인증번호 이메일 전송
            await $(".eBtn").click(function () {
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

        } else {
            alert('이메일 주소를 확인해주세요.');
        }





    }



}


// 아이디 중복 검사
$('#user_id').keyup(function () {

    let user_id = $('#user_id').val();
    console.log(user_id);

    $.ajax({

        url: "/member/userIdCheck",
        type: "post",
        data: { "user_id": user_id },
        dataType: 'json',
        success: function (result) {
            if (result == 1) {
                $("#id_feedback").html('이미 사용중인 아이디입니다.');
                $("#id_feedback").attr("class", "incorrect");
            } else {
                $("#id_feedback").html('사용할 수 있는 아이디입니다.');
                $("#id_feedback").attr("class", "correct");
            }
        },
        error: function () {
            alert("서버요청실패");
        }

    })

})

// 유효성 검사
document.querySelector('.signupBtn').addEventListener('click', function () {
   
    const user_email = $('#user_email').val();
    const user_id = $('#user_id').val();
    const user_pw = $('#user_pw').val();
    const user_pwCheck = $('#user_pwCheck').val();
    const user_name = $('#user_name').val();
    const user_birth = $('#user_birth').val();
    const user_phone = $('#user_phone').val();

    if (user_email == "" || user_email == null) {
        alert("이메일을 입력해주세요.");
        return;
    } else if (user_id == "" || user_id == null) {
        alert("아이디를 입력해주세요.");
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
    } else if (isNaN(user_birth) || user_birth.length != 6) {
        alert("생년월일 형식에 맞게 입력해주세요.");
        return;
    }
    else if (user_phone == "" || user_phone == null) {
        alert("핸드폰 번호를 입력해주세요.");
        return;
    } else if (isNaN(user_phone) || user_phone.length != 11) {
        alert("핸드폰 번호를 형식에 맞게 입력해주세요.");
        return;
    }
    else if (user_pw != user_pwCheck) {
        alert("비밀번호가 일치하지 않습니다.");
        return;
    } else {
        var pw = user_pw;
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

    $(".wrap-input").submit();
})