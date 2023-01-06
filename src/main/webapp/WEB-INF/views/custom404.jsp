<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
html, body{
    margin: 0;
    padding: 0;
    height: 100%;
    width: 100%;
}
.container{
    height: 100%;
    width: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 10px;
}

.container>img{
    height: 50%;
    width: 50%;
}
</style>

</head>
<body>
    <div class="container">
        <img src="/resources/source/error.png" alt="">
        ${ex}
    </div>
</body>
</html>