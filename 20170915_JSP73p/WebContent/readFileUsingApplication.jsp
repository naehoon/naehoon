<%@page import="java.io.IOException"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.InputStreamReader"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String resourcePath = "/message/notice.txt";
%>
자원의 실제 경로 : <br/>
<%= application.getRealPath(resourcePath) %>
</br>
-------------------</br>
<%= resourcePath %> 의 내용<br/>
-------------------</br>
<%
	char[] buff = new char[128];
	int len = -1;
	
	String filePath = "C:\\notice.txt";
	
	try(InputStreamReader fr = new InputStreamReader(new FileInputStream(filePath), "UTF-8")){
		while((len = fr.read(buff))!=-1){
			out.print(new String(buff,0,len));
		}
	}catch(IOException ie){
		out.println("익셉션 발생 : " + ie.getMessage());
	}
%>
</body>
</html>