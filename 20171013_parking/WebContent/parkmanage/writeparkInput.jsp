<%@page import="bs.com.service.InvalidInputException"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="bs.com.service.ParkmanageService"%>
<%@ page import="bs.com.model.ParkManageVO"%>
<%@ page import="bs.com.service.InvalidInputException"%>

<%
	request.setCharacterEncoding("utf-8");
%>    
<jsp:useBean id="parkinput" class="bs.com.model.ParkManageVO">
	<jsp:setProperty name="parkinput" property="*"/>
</jsp:useBean>
<%

	boolean invalidInput = false;

	try{
		ParkmanageService parkmanageService = ParkmanageService.getInstance();
		parkmanageService.insertParkInput(parkinput);
		
	}catch(InvalidInputException ex){
		invalidInput = true;
	}
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% if(!invalidInput){ %>
	<script  language="javascript">
		alert("차량입고가 완료되었습니다");
		history.back(-1);
	</script>
<%}else{ %>
	<script  language="javascript">
		alert("이미 입고된 차량입니다");
		history.back(-1);
	</script>
<%} %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
</body>
</html>