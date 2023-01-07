<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="./include/header.jsp"></jsp:include>

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
              <td><a href="/admin/diner/detail?diner_code=${diner.diner_code}">${diner.diner_name}</a></td>
              <td>${diner.diner_address}</td>
            </tr>
            </c:forEach>
          </tbody>
       	</table>
     	</div>


<jsp:include page="./include/footer.jsp"></jsp:include>