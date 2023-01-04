document.querySelector('.changeBtn').addEventListener('click', function(){
    const new_pw = document.getElementById('new_pw').value;
    const new_pwCheck = document.getElementById('new_pwCheck').value;

    if(new_pw == "" || new_pw == null){
        alert("비밀번호를 입력해주세요.");
        return;
    } else if (new_pwCheck == "" || new_pwCheck == null){
        alert("비밀번호 재확인을 입력해주세요.");
        return;
    } else if (new_pw != new_pwCheck){
        alert("비밀번호가 일치하지 않습니다.");
        return;
    }

    $(".form-box").submit();
})