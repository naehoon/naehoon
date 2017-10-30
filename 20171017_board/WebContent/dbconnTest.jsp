<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jdbc.connection.ConnectionProvider" %>
<%@ page import="java.sql.*" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>데이터베이스 연결 테스트 화면 </title>
</head>
<body>
<%
	try(Connection conn = ConnectionProvider.getConnection()){
		out.println("커넥션 연결 성공함");
	}catch(SQLException ex){
		out.println("커넥션 연결 실패함  : " + ex.getMessage());
		application.log("커넥션 연결 실패 ", ex);
	}
%>
</body>
</html>