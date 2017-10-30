<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.* , java.text.* "%>
<%!

	private String title;
	private String time;

	public void init() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		time = format.format(new Date());
	}
%>
<%
	title = "JSP page";
	int start_num = 1;
	int end_num = 5;
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%=title%>(<%=time%>)</title>
</head>
<body>
	<center>
		<h2>INPUT BOX LIST</h2>
		<!-- 스크립틀릿 서비스나 dopost, doget 안쪽에 적는 기호가 스크립틀릿이다. -->
		<%
			for (int i = start_num; i <= end_num; i++) {
		%>
		<%=i%> : <input type='text' name='box <%=i%>' /><br/><br/>
		<%
			}
		%>
	</center>
</body>
</html>