let once = 0;

	const add_textbox = () => {
		once++;
		if(once > 1) {
			sendEmailCheck();
			return;
		}

		const box = document.getElementById("box");
		const newP = document.createElement('p');
		newP.innerHTML = 
		"<input type='text' class='checkNum' placeholder='이메일로 전송된 인증번호를 입력해주세요.'><input type='button' class='cBtn' value='check' onclick='emailConfirm(this)'>";
		box.appendChild(newP);
		sendEmailCheck();
	}

	const emailConfirm = (obj) => {
	
	}

	// 내용 미입력 시 경고창
	document.querySelector('.signupBtn').addEventListener('click', function(){
		const user_email = document.getElementById('user_email').value;
		const user_pw = document.getElementById('user_pw').value;
		const user_pwCheck = document.getElementById('user_pwCheck').value;
		const user_name = document.getElementById('user_name').value;
		const user_birth = document.getElementById('user_birth').value;
		const user_phone = document.getElementById('user_phone').value;
		const user_nick = document.getElementById('user_nick').value;

		if(user_email == ""){
			alert("이메일을 입력해주세요.");
			return;
		} else if(user_pw == ""){
			alert("비밀번호를 입력해주세요.");
			return;
		} else if(user_pwCheck == ""){
			alert("비밀번호 재확인을 입력해주세요.");
			return;
		} else if(user_name == ""){
			alert("이름을 입력해주세요.");
			return;
		} else if(user_birth == ""){
			alert("생년월일을 입력해주세요.");
			return;
		} else if(user_phone == ""){
			alert("핸드폰번호를 입력해주세요.");
			return;
		} else if(user_nick == ""){
			alert("닉네임을 입력해주세요.");
			return;
		}
	})