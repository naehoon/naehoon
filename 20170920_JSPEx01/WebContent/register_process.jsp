<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>

<%
	request.setCharacterEncoding("utf-8");

	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	String name = request.getParameter("name");
	String tel = request.getParameter("tel");

	Class.forName("com.mysql.jdbc.Driver");
	
	System.out.println("회원가입 프로세스 ");

	Connection conn = null;
	PreparedStatement stmt = null;

	try {
		String jdbcDriver = "jdbc:mysql://localhost:3306/naehoon?" + "useUnicode=true&characterEncoding=utf-8";
		String dbUser = "root";
		String dbPass = "";

		String query = "INSERT INTO MEMBERS VALUE(null, ?, ?, ?, ?)";
		conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);

		stmt = conn.prepareStatement(query);
		stmt.setString(1, id);
		stmt.setString(2, pw);
		stmt.setString(3, name);
		stmt.setString(4, tel);
		int result = stmt.executeUpdate();
		
		if(result >= 1){  //회원가입 성공
			pageContext.forward("./login_page.jsp");
		}
	}catch (Exception e){
		e.printStackTrace();
	}
%>