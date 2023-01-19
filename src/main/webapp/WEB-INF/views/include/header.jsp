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
	<header class="d-flex justify-content-center">
		<div class="d-flex justify-content-between">
			<a href="/" class="logo">
			  <img src="/resources/source/logo1.png" alt="logo" id="logo_img" class="bi me-2">
			</a>
	  
			<ul class="nav nav-pills pt-1 align-items-center">
				<li>
				</li>
				<c:if test="${user.user_id == null }">
					<li class="nav-item"><div class="box"><a href="/member/login" class="nav-link" aria-current="page">로그인</a></div></li>
				</c:if>
				<c:if test="${user.user_id != null }">
					<li class="nav-item"><div class="box"><a href="/member/logout" class="nav-link" aria-current="page">로그아웃</a></div></li>
					<li class="nav-item"><div class="box2"><a href="/basket/diner" class="nav-link">장바구니</a></div></li>
					<li class="nav-item"><div class="box2"><a href="/member/detail_userInfo" class="nav-link">마이페이지</a></div></li>
				</c:if>
			</ul>
		</div>
	  </header>