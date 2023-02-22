<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>먹어요</title>
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
			<form id="logout" action="/logout" method="post"></form>
	  
			<ul class="nav nav-pills pt-1 align-items-center">
				<li>
				</li>
				<sec:authorize access="!isAuthenticated()">
					<li class="nav-item"><div class="box2"><a href="/member/login" class="nav-link" aria-current="page">로그인</a></div></li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<sec:authorize access="hasRole('ROLE_ADMIN')">
					<li class="nav-item"><div class="box2"><a href="/admin/" class="nav-link" aria-current="page">관리자</a></div></li>
					</sec:authorize>
					<li class="nav-item"><div class="box2" id="basket-box"><a href="/basket/diner" class="nav-link" id='basket'>장바구니</a></div></li>
					<li class="nav-item"><div class="box2"><a href="/member/detail_userInfo" class="nav-link">마이페이지</a></div></li>
					<li class="nav-item"><div class="box2" onclick="logout()"><a href="#" class="nav-link" aria-current="page">로그아웃</a></div></li>
				</sec:authorize>
			</ul>
		</div>
	  </header>
	  
	  <script type="text/javascript">
		
	  const basket_count = "<c:out value='${basket_count}' />";
	  if(basket_count > 0 ){
		  let a = document.getElementById("basket");
		  a.innerText = "장바구니("+basket_count+")";
		  a.className +=" text-light";
		  let box = document.getElementById("basket-box");
		  box.className +=" hasBasket";
	  }
	  
	  function logout(){
		  console.log("logout click");
		  $("#logout").submit();
	  }

	  </script>