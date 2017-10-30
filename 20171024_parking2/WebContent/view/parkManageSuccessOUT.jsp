<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>출고성공</title>
</head>

<body>
차량을 출고했습니다.
${ctxPath=pageContext.request.contextPath ; '' }

<a href="${ctxPath}/parkManageRead.do">[주차현황조회]</a>
</body>
</html>