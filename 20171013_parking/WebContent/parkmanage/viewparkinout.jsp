<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="bs.com.service.ViewInoutService"%>
<%@ page import="bs.com.model.ParkManageVO"%>
<%
	ViewInoutService viewInoutService = ViewInoutService.getInstance();
	ParkManageVO parkManageVO = viewInoutService.selectInOutList();
%>
<c:set var="parkManage" value="<%=parkManageVO%>" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:import url="../header.jsp" />
<title>Insert title here</title>
</head>
<body>
	<c:import url="../menu.jsp" />
	<h2>정기권 조회</h2>
	<table border="1" width="auto" align="center">
		<tr>
			<td>주차번호</td>
			<td>차량번호</td>
			<td>주차등급</td>
			<td>상태</td>
			<td>입고일시</td>
			<td>출고일시</td>
		</tr>
		<c:forEach var="parkManage" items="${parkManage.item}">
			<tr>
				<td>${parkManage.parkno}<br>
				</td>
				<td>${parkManage.carno}<br>
				</td>

				<c:choose>
					<c:when test="${parkManage.grade eq 'Y'}">
						<td>연회원<br>
					</c:when>
					<c:when test="${parkManage.grade eq 'M'}">
						<td>월간회원<br>
					</c:when>
					<c:otherwise>
						<td>일일회원<br>
					</c:otherwise>
				</c:choose>

				<c:choose>
					<c:when test="${parkManage.tstat eq 'I'}">
						<td>입고<br>
					</c:when>
					<c:otherwise>
						<td>출고<br>
					</c:otherwise>
				</c:choose>

				<td>${parkManage.indate}<br>
				</td>
				<td>${parkManage.outdate}<br>
				</td>
			</tr>
		</c:forEach>
	</table>
	<c:import url="../footer.jsp" />
</body>
</html>