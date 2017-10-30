<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>정기권 조회</title>

</head>
<body>
	<c:import url="/header.jsp" />
	<c:import url="/menu.jsp" />
	
	<h2>정기권 조회</h2>
		<table border="1" width="auto" align="center">
			<tr>
				<th>정기권 번호</th>
				<th>차량 번호</th>
				<th>차주전화</th>
				<th>주차등급</th>
				<th>정기권상태</th>
				<th>정기권시작일</th>
				<th>정기권종료일</th>
			</tr>
			
			<c:if test="${parkingRegiVO.hasNoArticles()}">
				<tr>
					<td colspan="7">주차권 등록차량이 없습니다</td>
				</tr>
			</c:if>
			
			<c:forEach var="parkView" items="${parkingRegiVO.item}">
				<tr>
					<td>
						${parkView.tno}<br>
					</td>
					<td>
						${parkView.carno}<br>
					</td>
					<td>
						${parkView.phone}<br>
					</td>
					<td>
						${parkView.grade}<br>
					</td>
					<td>
						${parkView.tstat}<br>
					</td>
					<td>
						${parkView.startdate}<br>
					</td>
					<td>
						${parkView.enddate}<br>
					</td>
				</tr>
			</c:forEach>
			
			<c:if test="${parkingRegiVO.hasArticles()}">
				<tr>
					<td colspan="7">
						<c:if test ="${parkingRegiVO.startPage > 5 }">
							<a href="parkRegiRead.do?pageNo=${parkingRegiVO.startPage-5 }">[이전]</a>
						</c:if>
						
						<c:forEach var="pNo" 
										begin="${parkingRegiVO.startPage}" 
										end ="${parkingRegiVO.endPage}">
							<a href="parkRegiRead.do?pageNo=${pNo}">[${pNo}]</a>							
						</c:forEach>
						
						<c:if test="${parkingRegiVO.endPage < parkingRegiVO.totalPages}">
							<a href="parkRegiRead.do?pageNo=${parkingRegiVO.startPage+5 }">[다음]</a>			
						</c:if>
						
				</tr>
			</c:if>

			
		</table>
	<c:import url="/footer.jsp" />
</body>
</html>