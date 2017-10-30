<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="bs.com.service.ViewParkService"%>
<%@ page import="bs.com.model.ParkingViewVO"%>
<%
	ViewParkService viewParkService = ViewParkService.getInstance();
	ParkingViewVO parkingViewVO = viewParkService.selectParkingList();
%>
<c:set var="parkingView" value="<%=parkingViewVO%>" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
	<c:import url="../header.jsp" />
	<c:import url="../menu.jsp" />
	<h2>정기권 조회</h2>
		<table border="1" width="auto" align="center">
			<tr>
				<td>정기권 번호</td>
				<td>차량 번호</td>
				<td>차주전화</td>
				<td>주차등급</td>
				<td>정기권상태</td>
				<td>정기권시작일</td>
				<td>정기권종료일</td>
			</tr>
			<c:forEach var="parkView" items="${parkingView.item}">
				<tr>
					<td>
						${parkView.tno}<br>
					</td>
					<td>
						${parkView.carno}<br>
					</td>
					<td>
						${parkView.phone}<br>
					</td>
					<td>
						${parkView.grade}<br>
					</td>
					<td>
						${parkView.tstat}<br>
					</td>
					<td>
						${parkView.startdate}<br>
					</td>
					<td>
						${parkView.enddate}<br>
					</td>
				</tr>																					
			</c:forEach>
		</table>
	<c:import url="../footer.jsp" />
</body>
</html>