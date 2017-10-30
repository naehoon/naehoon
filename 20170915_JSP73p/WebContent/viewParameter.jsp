<%@page import="org.apache.catalina.util.ParameterMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Enumeration"%>
<%@ page import="java.util.Map"%>
<%
	request.setCharacterEncoding("utf-8");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<b>request.getParmeter() 메서드 사용</b>
	</br> name 파라미터 =
	<%=request.getParameter("name")%>
	address 파라미터 =
	<%=request.getParameter("address")%>
	<p>
		<b>request.getParmeterValues() 메서드 사용</b></br>
		<%
			String[] values = request.getParameterValues("pet");
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
		%>
			<%= values[i]%>
		<%
				}
			}
		%>
	<p>
		<b>request.getParmeterNames() 메서드 사용</b></br>
		<%
			Enumeration paramEnum = request.getParameterNames();
			while (paramEnum.hasMoreElements()) {
				String name = (String) paramEnum.nextElement();
		%>
		<%=name%>
		<%
			}
		%>
	<p>
		<b>request.getParmeterNames() 메서드 사용</b></br>
		<%
			Map parameterMap = request.getParameterMap();
			String[] nameParam = (String[]) parameterMap.get("name");
			if (nameParam != null) {
		%>
		name =
		<%=nameParam[0]%>
		<%
			}
		%>
	
</body>
</html>