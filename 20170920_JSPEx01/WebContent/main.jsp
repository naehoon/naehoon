<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2> 메인화면 입니다 </h2>
<%
	if(session.getAttribute("id") != null){ // 로그인 성공했을때 
%>
	<script>
		alert("로그인 성공 !!!");
	</script>
		로그인 상태 입니다.
<%		
	}
%>

</body>
</html>