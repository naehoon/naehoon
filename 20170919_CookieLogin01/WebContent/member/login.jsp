<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "util.Cookies" %>
<%@ page import="java.net.URLEncoder" %>

<%
	String id = request.getParameter("id");
	String password = request.getParameter("password");
	
	if(id.equals(password)){
		response.addCookie(Cookies.createCookie("AUTH", id, "/", -1));
%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
로그인에 성공했습니다.
</body>
</html>

<%
	}else{
%>
<script>
alert("로그인에 실패했습니니다");
history.go(-1);
</script>
<%
	}
%>
