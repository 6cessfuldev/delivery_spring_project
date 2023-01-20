document.querySelector('.delBtn').addEventListener('click', function () {

    const user_id = $('#user_id').val();
    
    // 회원 정보 삭제

    $.ajax({

        url: '/member/remove_userInfo',
        type: 'post',
        data: { "user_id": user_id },

        success: function (result) {
            if (result > 0) {
                alert("회원 탈퇴가 완료되었습니다.");
                location.href = '/';
            }
        },
        error: function () {
            alert("서버 요청 실패");
        }

    });

});