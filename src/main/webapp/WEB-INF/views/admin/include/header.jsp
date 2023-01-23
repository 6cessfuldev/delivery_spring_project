<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<link type="text/css" rel="stylesheet" href="/resources/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="/resources/css/header.css">

<script type="text/javascript" src="/resources/js/jquery-3.6.3.min.js"></script>
<script type="text/javascript" src="/resources/js/bootstrap.bundle.js"></script>
</head>

<c:set var="URI" value="${requestScope['javax.servlet.forward.request_uri']}" />
<c:set var="domain" value="${fn:split(URI, '/')[1]}" />
<body>
	<header class="pt-2">
		<a href="/" class="logo">
		  <img src="/resources/source/logo1.png" alt="logo" id="logo_img" class="bi me-2" height="60">
		</a>
  
		<ul class="nav nav-pills pt-1">
		<sec:authorize access="!isAuthenticated()">
		  <li class="nav-item"><div class="box"><a href="/member/login" class="nav-link" aria-current="page">로그인</a></div></li>
		</sec:authorize>
		<sec:authorize access="isAuthenticated()">
		<form id="logout" action="/logout" method="post"></form>
		  <li class="nav-item"><div class="box" onclick="logout()"><a href="#" class="nav-link" aria-current="page">로그아웃</a></div></li>
		</sec:authorize>
		</ul>
	  </header>
	  
	  <div class="container-fluid">
  <div class="row">
    <nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
      <div class="position-sticky pt-3 sidebar-sticky">
        <ul class="nav flex-column">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="/admin/diner">
              <span data-feather="home" class="align-text-bottom"></span>
              음식점/음식/옵션
            </a>
          </li>
          
          <li class="nav-item">
            <a class="nav-link" href="#">
              <span data-feather="shopping-cart" class="align-text-bottom"></span>
              리뷰/사장님댓글
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/admin/user">
              <span data-feather="bar-chart-2" class="align-text-bottom"></span>
              회원/장바구니
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/admin/loginHistory">
              <span data-feather="layers" class="align-text-bottom"></span>
              로그인기록
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">
              <span data-feather="layers" class="align-text-bottom"></span>
              주문내역
            </a>
          </li>
        </ul>
      </div>
    </nav>
    
    <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">