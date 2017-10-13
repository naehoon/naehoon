<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "guestbook.service.DeleteMessageService" %>
<%@ page import = "guestbook.service.InvalidPasswordException" %>
<%
	int messageId = Integer.parseInt(request.getParameter("messageId"));

	String password= request.getParameter("password");
	boolean invalidPassword = false;
	
	System.out.println("messageId : " + messageId);
	System.out.println("password : " + password);
	
	try{
		DeleteMessageService deleteService = DeleteMessageService.getInstance();
		deleteService.deleteMessage(messageId, password);
		
	}catch(InvalidPasswordException ex){
		invalidPassword = true;
	}

%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<% if(!invalidPassword){ %>
메시지를 삭제하였습니다.
<%}else{ %>
입력한 암호가 올바르지 않습니다 . 암호를 확인해 주세요
<%} %>
<br>
<a href="list.jsp">[목록보기]</a>
</body>
</html>