<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "guestbook.model.Message" %>
<%@ page import = "guestbook.service.WriteMessageService" %>
<%
	request.setCharacterEncoding("utf-8");
%>

<!-- message 객체를 생성한다 -->
<jsp:useBean id="message" class="guestbook.model.Message">
<!-- 	모든 property 를 저장한다 -->
	<jsp:setProperty name="message" property="*" />
</jsp:useBean>


<%
	WriteMessageService writeService = WriteMessageService.getInstance();
	//사용자가 입력한 값 저장
	writeService.write(message);
%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
방명록에 메시지를 남겼습니다.
<br>
<a href="list.jsp">[목록보기]</a>
</body>
</html>