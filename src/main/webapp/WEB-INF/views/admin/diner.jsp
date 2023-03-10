<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="./include/header.jsp"></jsp:include>

<div class="table-responsive">
	<a href="/admin/diner/register">음식점 추가</a>
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
					<td><a
						href="/admin/diner/detail?diner_code=${diner.diner_code}">${diner.diner_name}</a></td>
					<td>${diner.diner_address}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>


	<c:if test="${pgh.prev}">
		<a href="/admin/diner?pageNo=${pgh.startPage-1}&qty=${pgh.pgvo.qty}">prev</a>
	</c:if>
	<c:forEach begin="${pgh.startPage}" end="${pgh.endPage}" var="i">
		<a href="/admin/diner?pageNo=${i}&qty=${pgh.pgvo.qty}">${i}</a>
	</c:forEach>
	<c:if test="${pgh.next}">
		<a href="/admin/diner?pageNo=${pgh.endPage+1}&qty=${pgh.pgvo.qty}">next</a>
	</c:if>
</div>



<jsp:include page="./include/footer.jsp"></jsp:include>