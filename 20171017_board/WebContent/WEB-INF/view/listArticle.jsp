<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>�Խñ� ���</title>
</head>
<body>

	<table border="1">
		<tr>
			<td colspan="4"><a href="write.do">[�Խ��Ǳ۾���]</a> <a
				href="../logout.do">[�α׾ƿ��ϱ�]</a></td>
		</tr>
		<tr>
			<td>��ȣ</td>
			<td>����</td>
			<td>�ۼ���</td>
			<td>��ȸ��</td>
		</tr>

		<c:if test="${articlePage.hasNoArticles()}">
			<tr>
				<td colspan="4">�Խñ��� �����ϴ�</td>
			</tr>
		</c:if>

		<c:forEach var="article" items="${articlePage.content}">
			<tr>
				<td>${article.number}</td>
				<td><a
					href="read.do?no=${article.number}&pageNo=${articlePage.currentPage}">
						<c:out value="${article.title}" />
				</a></td>
				<td>${article.writer.name}</td>
				<td>${article.readCount}</td>
			</tr>
		</c:forEach>

		<c:if test="${articlePage.hasArticles()}">
			<tr>
				<td colspan="4"><c:if test="${articlePage.startPage > 5 }">
						<a href="list.do?pageNo=${articlePage.startPage-5 }">[����]</a>
					</c:if> <c:forEach var="pNo" begin="${articlePage.startPage}"
						end="${articlePage.endPage}">
						<a href="list.do?pageNo=${pNo}">[${pNo}]</a>
					</c:forEach> <c:if test="${articlePage.endPage < articlePage.totalPages}">

						<a href="list.do?pageNo=${articlePage.startPage+5 }">[����]</a>
					</c:if>
			</tr>
		</c:if>
	</table>

</body>
</html>
