<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>�Խñ� ����</title>
</head>

<body>
	<form action="modify.do" method="post">
		<p>��ȣ : <input type="text" name="no" value="${modReq.articleNumber}"></p>
		<p>
			���� : <br />
			<input type="text" name="title" value="${modReq.title}">
			<c:if test="${errors.title}">������ �Է��ϼ���</c:if>
		</p>
		<p>
			���� : <br />
			<textarea name="content" rows="5" cols="30">${modReq.content}</textarea>
		</p>
		<input type="submit" value="�ۼ���">
	</form>
</body>

</html>