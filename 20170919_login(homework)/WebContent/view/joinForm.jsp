<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script>
	alert("로그인에 실패하였습니다");
</script>
<body>
<%-- <form action="<%= request.getContextPath() %>/member/login.jsp" method="post"> --%>
<form action="/ServletJoin" method="post">
	이름 : <input type="text" name="name" size="10"><br/>
	아이디 : <input type="text" name="id" size="10"><br/>
	암호 : <input type="password" name="pw" size="10"><br/>
	전화번호 : <input type="text" name="tel" size="10"><br/>
	<input type="submit" value="회원가입">
	<input type="reset" value="취소">
</form>
</body>
</html>