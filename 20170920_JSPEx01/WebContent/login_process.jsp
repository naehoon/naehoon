<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>

<%
	request.setCharacterEncoding("utf-8");

	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	String name = "", tel = "";

	Class.forName("com.mysql.jdbc.Driver");

	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;

	try {
		String jdbcDriver = "jdbc:mysql://localhost:3306/naehoon?" + "useUnicode=true&characterEncoding=utf-8";
		String dbUser = "root";
		String dbPass = "";

		String query = "SELECT ID, PASSWORD, NAME, TEL FROM MEMBERS WHERE ID = ?";
// 		System.out.println("1111111111111111111111" + query);
		conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);

		stmt = conn.prepareStatement(query);
		stmt.setString(1, id);
		rs = stmt.executeQuery();

		if (rs.next()) {

			if (id.equals(rs.getString("ID")) && pw.equals(rs.getString("PASSWORD"))) {
				//아이디 패스워드가 맞으면
				name = rs.getString("name");
				tel = rs.getString("tel");
				
				session.setAttribute("name", name);
				session.setAttribute("id", id);
				session.setAttribute("tel", tel);
				pageContext.forward("./main.jsp");

			} else if (!pw.equals(rs.getString("PASSWORD"))) {
%>
				<script>
					alert("비밀번호가 일치하지 않습니다");
					history.back(-1);
				</script>
<%
			}
			
		} else { //존재하지 않는 아이디를 입력했을 경우 
			session.setAttribute("id", id);
			response.sendRedirect("./register.jsp"); //회원가입 페이지로 이동하기
		}

	} catch (Exception se) {
		se.printStackTrace();
	    response.sendRedirect("./register.jsp");
	    return;		
	    
	} finally {
		if (conn != null)
			conn.close();
		conn = null;
	}
%>