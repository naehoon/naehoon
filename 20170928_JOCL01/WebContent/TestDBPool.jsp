<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<H3>데이터 베이스 커넥션 풀 테스트</H3>
	<%
		Class.forName("org.apache.commons.dbcp.PoolingDriver"	);
		Connection conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:/wdbpool");
		if(conn != null){
			out.println("연결 취득 완료 <br>");
			conn.close();
			out.println("연결 반환 완료 <br>");
		}else{
			out.println("연결 취득 실패 <br>");
		}
	%>
</body>
</html>