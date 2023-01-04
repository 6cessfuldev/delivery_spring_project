<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ErrorPage</title>
</head>
<body>

    <div>
        <img src="/resources/source/error.png" alt="">
        <h4><c:out value="${exception.getMessage()}" /></h4>
    </div>


</body>
</html>