<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="bs.com.service.RegistParkService"%>
<%@ page import="bs.com.model.ParkingViewVO"%>
<%
	request.setCharacterEncoding("utf-8");
%>    
<jsp:useBean id="parkregi" class="bs.com.model.ParkingViewVO">
	<jsp:setProperty name="parkregi" property="*"/>
</jsp:useBean>
<%
	RegistParkService registParkService = RegistParkService.getInstance();
	registParkService.insertParkInfo(parkregi);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script  language="javascript">
	alert("정기주차권 신청이 완료되었습니다");
	history.back(-1);
</script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

</body>
</html>