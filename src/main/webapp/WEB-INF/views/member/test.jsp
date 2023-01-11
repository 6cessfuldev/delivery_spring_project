<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NaverTest</title>
</head>
<body>

<h1>${accessToken}</h1>
<h1>${refreshToken}</h1>
<script type="text/javascript">
   window.opener.gotoMain();
   window.close();
</script>
</body>
</html>