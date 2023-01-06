<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="/resources/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="/resources/css/header.css">

<script type="text/javascript" src="/resources/js/jquery-3.6.3.min.js"></script>
<script type="text/javascript" src="/resources/js/bootstrap.bundle.js"></script>
</head>

<body>
	<header class="pt-2">
		<a href="/" class="logo">
		  <img src="/resources/source/logo2.png" alt="logo" id="logo_img" class="bi me-2" width="200" height="120">
		</a>
  
		<ul class="nav nav-pills pt-1">
		<c:if test="${ses.user_id == null }">
		  <li class="nav-item"><div class="box"><a href="/member/login" class="nav-link" aria-current="page">로그인</a></div></li>
		</c:if>
		<c:if test="${ses.user_id != null }">
		  <li class="nav-item"><div class="box"><a href="/member/logout" class="nav-link" aria-current="page">로그아웃</a></div></li>
		</c:if>
		  <li class="nav-item"><div class="box2"><a href="" class="nav-link">장바구니</a></div></li>
		</ul>
	  </header>