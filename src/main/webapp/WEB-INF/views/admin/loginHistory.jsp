<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="./include/header.jsp"></jsp:include>

<div class="table-responsive">
        <table class="table table-striped table-sm">
    <thead>
        <tr>
            <th scope="col">아이디</th>
            <th scope="col">이름</th>
            <th scope="col">연락처</th>
            <th scope="col">생년월일</th>
            <th scope="col">로그인</th>
            <th scope="col">로그아웃</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${loginHistory}" var="login">
            <tr>
                <td>${login.lvo.user_id}</td>
                <td>${login.uvo.user_name }</td>
                <td>${login.uvo.user_phone}</td>
                <td>${login.uvo.user_birth}</td>
                <td>${login.lvo.log_date }</td>
                <td>${login.lvo.log_out_date }</td>
            </tr>
        </c:forEach>
    </tbody>
</table>	
</div>



<jsp:include page="./include/footer.jsp"></jsp:include>