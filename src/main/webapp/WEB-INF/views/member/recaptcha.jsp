<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

    <script src="https://www.google.com/recaptcha/api.js?render=6LfBdecjAAAAAO1IqVWyJJ_nyrwRBKKMA1dzNlXc"></script>
    grecaptcha.ready(function() { 
        grecaptcha.execute('6LfBdecjAAAAAO1IqVWyJJ_nyrwRBKKMA1dzNlXc', {action:'submit'}).then(function(token) { 
            
            $.ajax({ 
                type : "POST", 
                url : "reCAPTCHA_URL", 
                data : {"token" : token}, 

            success : function(data) { // 성공 처리 }, 
            error : function(request, status, msg) { // 실패 처리 } 
            }); 
        });
    });

</body>
</html>