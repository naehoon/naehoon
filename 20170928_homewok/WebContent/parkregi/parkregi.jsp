<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="bs.com.service.ViewParkService"%>
<%@ page import="bs.com.service.RegistParkService"%>
<%@ page import="bs.com.model.ParkingViewVO"%>
<%
	ViewParkService viewParkService = ViewParkService.getInstance();
	int maxNum = viewParkService.getMaxNum();
%>
<!-- viewData 변수지정 -->
<c:set var="maxNum" value="<%=maxNum%>" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script language="javascript">

function select_click() {
	alert("조회 버튼을 누르셨습니다.");
	document.regiform.cmd.value="select";
	document.regiform.submit();
}

</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:import url="../header.jsp" />
	<c:import url="../menu.jsp" />
	<h2>주차 정기권 등록</h2>
	<form name="regiform" action="RegistParkService" method="post">
		<table border="1" width="auto">
			<input type="hidden" name="cmd" />
			<tr>
				<td>정기권 번호 (자동발생)</td>
				<td><input type="text" name="tno" value="${maxNum}"
					readonly="readonly"></td>
			</tr>
			<tr>
				<td>차량번호</td>
				<td><input type="text" name="carno"></td>
			</tr>
			<tr>
				<td>차주전화</td>
				<td><input type="text" name="phone"></td>
			</tr>
			<tr>
				<td>주차등급(M/Y)</td>
				<td><input type="text" name="grade"></td>
			</tr>
			<tr>
				<td>정기권상태(Y/N)</td>
				<td><input type="text" name="tstat"></td>
			</tr>
			<tr>
				<td>시작일(YYYYMMDD)</td>
				<td><input type="text" name="startDate"></td>
			</tr>
			<tr>
				<td>종료일(YYYYMMDD)</td>
				<td><input type="text" name="endDate"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="button" value="등록"> <input
					type="button" value="조회" onclick="select_click()">
			</tr>
		</table>
	</form>
	<c:import url="../footer.jsp" />
</body>
</html>