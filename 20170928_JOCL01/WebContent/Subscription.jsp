<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>

<%
	
	request.setCharacterEncoding("utf-8");

	String name = request.getParameter("name");
	String id = request.getParameter("id");
	String password = request.getParameter("password");

	if (name == null || id == null || password == null) {
		throw new Exception("데이터를 입력하십시요");
	}
	Connection con = null;
	Statement stmt = null;

	try {
		Class.forName("org.apache.commons.dbcp.PoolingDriver");
		con = DriverManager.getConnection("jdbc:apache:commons:dbcp:/wdbpool");

		if (con == null) {
			throw new Exception("데이터베이스에 연결할 수 없습니다");
		}

		stmt = con.createStatement();
		String command = String.format("insert into userinfo " + "(name, id, password) values ('%s', '%s', '%s');", name, id, password);

		int rowNum = stmt.executeUpdate(command);

		if (rowNum < 1) {
			throw new Exception("데이터를 DB 에 입력할 수 없습니다.");
		}

	} finally {
		try {
			stmt.close();
		} catch (Exception ignored) {

		}
		try {
			con.close();
		} catch (Exception ignored) {

		}
	}
	response.sendRedirect("SubscriptionResult.jsp");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>