<%@ page contentType="text/html; charset=UTF-8" %>
<%
	if(session.getAttribute("id") != null){ // 존재하지 않는 아이디를 입력했을경우  
%>
	<script>
		alert("존재하지 않는 사용자 입니다 회원가입을 해주세요.");
	</script>
<%		
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<h2>회원가입 페이지</h2>
<form action="register_process.jsp" method="post">
	이름 :       <input type="text" name="name" size="10"><br/>
	아이디 :    <input type="text" name="id" size="10"><br/>
	암호 :      <input type="password" name="pw" size="10"><br/>
	전화번호 : <input type="text" name="tel" size="10"><br/>
	<input type="submit" value="회원가입">
	<input type="reset" value="취소">
</form>
</body>
</html>