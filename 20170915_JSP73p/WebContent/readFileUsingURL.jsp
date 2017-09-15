<%@page import="java.io.*"%>
<%@page import="java.net.URL"%>

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
	char[] buff = new char[128];
	int len = -1;
	
	URL url = application.getResource(resourcePath);
	
	try(InputStreamReader fr = new InputStreamReader(url.openStream(), "UTF-8")){
		while((len = fr.read(buff))!=-1){
			out.print(new String(buff,0,len));
		}
	}catch(IOException ie){
		out.println("익셉션 발생 : " + ie.getMessage());
	}
%>
</body>
</html>