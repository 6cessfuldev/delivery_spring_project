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
            <th scope="col">네이버로그인</th>
            <th scope="col">가입일</th>
            <th scope="col">주문수</th>
            <th scope="col">리뷰수</th>
            <th scope="col">가입승인</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${list}" var="user">
            <tr>
                <td><a href="/admin/user/detail?user_id=${user.user_id}">${user.user_id}</a></td>
                <td>${user.user_name}</td>
                <td>${user.user_phone}</td>
                <td>${user.user_birth}</td>
                <c:if test="${user.user_naver_id eq null}">
                <td>N</td>
                </c:if>
                <c:if test="${user.user_naver_id ne null}">
                <td>Y</td>
                </c:if>
                <td>${user.user_reg_date}</td>
                <td>0</td>
                <td>0</td>
                <td>drop</td>
            </tr>
        </c:forEach>
    </tbody>
</table>	
</div>



<jsp:include page="./include/footer.jsp"></jsp:include>