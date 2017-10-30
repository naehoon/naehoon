<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>주차권 등록</title>
</head>
<body>
주차권을 등록했습니다.
<br/>

${ctxPath=pageContext.request.contextPath; ''}
<a href="${ctxPath}/parkRegiRead.do">[정기권현황조회]</a>
</body>
</html>