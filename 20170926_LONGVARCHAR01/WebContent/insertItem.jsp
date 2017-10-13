<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.SQLException" %>
<%
	String idValue = request.getParameter("id");

	Connection conn = null;
	PreparedStatement  pstmtItem = null;
	PreparedStatement  pstmtDeteil= null;
	
	String jdbcDriver = "jdbc:mysql://localhost:3306/naehoon?" + "useUnicode=true&characterEncoding=utf8";
	String dbUser = "root";
	String dbPass = "";
	
	Throwable occuredException = null;
	
	try{
		int id = Integer.parseInt(idValue);
		
		conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
		conn.setAutoCommit(false);
		
		pstmtItem = conn.prepareStatement("insert into ITEM values(?,?)");
		pstmtItem.setInt(1, id);
		pstmtItem.setString(2, "상품이름 " +id);
		pstmtItem.executeUpdate();
		
		if(request.getParameter("error")!= null){
			throw new Exception("의도적인 익셉션 발생!!!"); //Throwable 로 익셉션 던짐
		}
		
		pstmtDeteil = conn.prepareStatement("insert into ITEM_DETAIL values(?,?)");
		pstmtDeteil.setInt(1, id);
		pstmtDeteil.setString(2, "상세설명 " +id);
		pstmtDeteil.executeUpdate();
		
		conn.commit();

	}catch(Throwable e){
		if(conn != null){
			try{
				conn.rollback();
			}catch(SQLException ex){}
		}
		
		occuredException = e;
	}finally{
		if(pstmtItem != null){
			try{pstmtItem.close();}catch(SQLException ex){}
		}
		
		if(pstmtDeteil != null){
			try{pstmtDeteil.close();}catch(SQLException ex){}
		}
		
		if(conn != null){
			try{conn.close();}catch(SQLException ex){}
		}
	}

%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<% if (occuredException != null){ %>
에러가 발생했습니다. : <%= occuredException.getMessage() %>
<%}else{ %>
데이터가 성공적으로 들어감
<%} %>
</body>
</html>

