<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = (String)session.getAttribute("id");
		if(id==null){  //세션 값이 없으면 
			id="";
		}
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>로그인 페이지</h2>
<form action="login_process.jsp" name="login_form" method="post">
<table border="1" width="300" height="=400">
	<tr>
		<td width="100">아이디</td>
		<td><input type="text" name="id" value='<%=id %>'/></td>
	</tr>
	<tr>
		<td width="100">패스워드</td>
		<td><input type="password" name="pw" /></td>
	</tr>
	<tr>
		<td colspan="2" align="center">
		<input type="submit" value="로그인"/>
	</tr>
</table>

</form>
</body>
</html>