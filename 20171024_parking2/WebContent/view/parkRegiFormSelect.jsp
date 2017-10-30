<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="parkregi.model.ParkingRegiVO"%>
<%@ page import="java.util.ArrayList"%>

<%
	ParkingRegiVO radioVal = (ParkingRegiVO) request.getAttribute("parkingRegiVO"); 
%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script language="javascript">
	
function checkProc(){
	
	var item = '<%= radioVal.getItem().get(0) %>'
// 	alert("grade : " + item[0] + " tstat  :" + item[1]);
	
	if(item[0] =='M'){
		document.regiform.grade[0].checked = true;	
	}else{
		document.regiform.grade[1].checked = true;
	}
	
	if(item[1] =='Y'){
		document.regiform.tstat[0].checked = true;	
	}else{
		document.regiform.tstat[1].checked = true;
	}
	
}


</script>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>주차정기권조회</title>
</head>

<body onload = "checkProc()">
<c:import url="/header.jsp" />
<c:import url="/menu.jsp" />
	<h2>주차 정기권 등록 , 조회</h2>
	
	<form name="regiform" method="post">
		<table border="1" width="auto" align="center">
		
			<c:forEach var="parkingRegi" items="${parkingRegiVO.item}">
					
			<tr>
				<td>정기권 번호</td>
				<td><input type="text" name="tno"  value="${parkingRegi.getTno()}" readonly></td>
			</tr>
			<tr>
				<td>* 차량번호</td>
					<td><input type="text" name="carno" value="${parkingRegi.getCarno()}" readonly>
				</td>
			</tr>
			<tr>
				<td>차주전화</td>
				<td><input type="text" name="phone" value="${parkingRegi.getPhone()}" readonly>
				</td>
			</tr>
			<tr>
				<td>주차등급(M/Y)</td>
				<td>
					<input type="radio" name="grade" value="M"  disabled>M
					<input type="radio" name="grade" value="Y" disabled>Y
				</td>
			</tr>
			<tr>
				<td>정기권상태(Y/N)</td>
				<td>
					<input type="radio" name="tstat" value="Y" disabled>Y
					<input type="radio" name="tstat" value="N" disabled>N
				</td>
			</tr>
			<tr>
				<td>시작일(YYYYMMDD)</td>
				<td><input type="text" name="startdate"  value="${parkingRegi.getStartdate()}" readonly>
				</td>
				
			</tr>
			<tr>
				<td>종료일(YYYYMMDD)</td>
				<td><input type="text" name="enddate" value="${parkingRegi.getEnddate()}"  readonly>
				</td>
			</tr>
			</c:forEach>
			
		</table>
	</form>
	<c:import url="/footer.jsp" />
	
</body>
</html>