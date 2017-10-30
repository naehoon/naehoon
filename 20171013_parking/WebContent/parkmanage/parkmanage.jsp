<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="bs.com.service.ViewParkService"%>
<%@ page import="bs.com.service.ParkmanageService"%>
<%@ page import="bs.com.model.ParkingViewVO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script language="javascript">
//입고 및 출고 버튼에 따른 분기처리 
function parkBtn(flag){
	
	if(document.manage.carno.value == ''){
		alert("차량번호를 입력해 주세요");
		return false;
	}
	
	if(flag == 1){
		document.manage.action = "parkin.jsp";
	}else{
		document.manage.action = "parkout.jsp";
	}
	document.manage.submit();
}
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:import url="../header.jsp" />
<title>Insert title here</title>
<body>
	<c:import url="../menu.jsp" />
	<h2>주차 차량 입고, 출고 관리</h2>
	<form name="manage" method="get">
			차량번호 <input type="text" name="carno"> 
			<button type="button" onclick="parkBtn(1)">주차입고</button>
			<button type="button" onclick="parkBtn(2)">주차출고</button> 
	</form>
	
	<div id="tablePanel">
	</div>
	<c:import url="../footer.jsp" />
</body>
</html>