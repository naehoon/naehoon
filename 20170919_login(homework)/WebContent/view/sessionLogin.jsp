<%@page import="java.io.IOException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="javax.servlet.*" %>

<%

// 	String memberId = (String)session.getAttribute("MEMBERID");
	
    String id = request.getParameter("id");
	String password = request.getParameter("pw");
	
	Class.forName("com.mysql.jdbc.Driver");
	
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	
	try{
		String jdbcDriver = "jdbc:mysql://localhost:3306/naehoon?"+"useUnicode=true&characterEncoding=utf-8";
		String dbUser = "root";
		String dbPass ="";
		
		String query = "SELECT ID, PASSWORD FROM MEMBERS WHERE ID = ?";
		
		conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
		
		stmt  = conn.prepareStatement(query);
		stmt.setString(1, id);
		
		rs = stmt.executeQuery();
		
		if(rs.next()){
			
			if(id.equals(rs.getString("ID")) && password.equals(rs.getString("PASSWORD"))){ 
				//아이디 패스워드가 맞으면 
				session.setAttribute("ID", id);
				session.setAttribute("PASSWORD", password);
%>				
				<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
				<html>
				<head>
				<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
				<title>Insert title here</title>
				</head>
				<body>
					로그인에 성공하였습니다.
				</body>
				</html>
<%

			}else if(!password.equals(rs.getString("PASSWORD"))){
%>				
				<script>
					alert("비밀번호가 일치하지 않습니다.");
					history.back(-1);
				</script>
<%			
			}
			
		}else{
			response.sendRedirect("./joinForm.jsp"); //회원가입 페이지로 이동하기
		}
		
		stmt.close();
		conn.close();
		
	}catch(SQLException se){
		se.printStackTrace();
	}
%>