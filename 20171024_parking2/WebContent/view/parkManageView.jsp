<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:import url="/header.jsp" />
<title>주차입출고조회</title>
</head>
<body>
	<c:import url="/menu.jsp" />
	<h2>주차 입출고 조회</h2>
	<table border="1" width="auto" align="center">
		<tr>
			<th>주차번호</th>
			<th>차량번호</th>
			<th>주차등급</th>
			<th>상태</th>
			<th>입고일시</th>
			<th>출고일시</th>
		</tr>

		<c:if test="${parkManageVO.hasNoArticles()}">
			<tr>
				<td colspan="6">주차권 등록차량이 없습니다</td>
			</tr>
		</c:if>
		
		<c:forEach var="parkManage" items="${parkManageVO.item}">
			<tr>
				<td>${parkManage.parkno}<br>
				</td>
				<td>${parkManage.carno}<br>
				</td>

				<c:choose>
					<c:when test="${parkManage.grade eq 'Y'}">
						<td>연회원<br>
					</c:when>
					<c:when test="${parkManage.grade eq 'M'}">
						<td>월간회원<br>
					</c:when>
					<c:otherwise>
						<td>일일회원<br>
					</c:otherwise>
				</c:choose>

				<c:choose>
					<c:when test="${parkManage.tstat eq 'I'}">
						<td>입고<br>
					</c:when>
					<c:otherwise>
						<td>출고<br>
					</c:otherwise>
				</c:choose>

				<td>${parkManage.indate}<br>
				</td>
				<td>${parkManage.outdate}<br>
				</td>
			</tr>
		</c:forEach>
		
		<c:if test="${parkManageVO.hasArticles()}">
			<tr>
				<td colspan="7">
					<c:if test ="${parkManageVO.startPage > 5 }">
						<a href="parkManageRead.do?pageNo=${parkManageVO.startPage-5 }">[이전]</a>
					</c:if>
					
					<c:forEach var="pNo" 
									begin="${parkManageVO.startPage}" 
									end ="${parkManageVO.endPage}">
						<a href="parkManageRead.do?pageNo=${pNo}">[${pNo}]</a>							
					</c:forEach>
					
					<c:if test="${parkManageVO.endPage < parkManageVO.totalPages}">
						<a href="parkManageRead.do?pageNo=${parkManageVO.startPage+5 }">[다음]</a>			
					</c:if>
					
			</tr>
		</c:if>
		
	</table>
	<c:import url="/footer.jsp" />
</body>
</html>