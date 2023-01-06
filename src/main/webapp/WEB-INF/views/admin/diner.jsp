<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="./include/header.jsp"></jsp:include>

    <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">

	<div class="table-responsive">
        <table class="table table-striped table-sm">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">음식점 이름</th>
              <th scope="col">음식점 주소</th>
            </tr>
          </thead>
          <tbody>
         	<c:forEach items="${list}" var="diner">
            <tr>
              <td>${diner.diner_code}</td>
              <td>${diner.diner_name}</td>
              <td>${diner.diner_address}</td>
            </tr>
            </c:forEach>
          </tbody>
       	</table>
     	</div>
    </main>
	

<jsp:include page="./include/footer.jsp"></jsp:include>