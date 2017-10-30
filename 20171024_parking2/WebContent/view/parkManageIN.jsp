<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%
	String carno = request.getParameter("carno"); //차량번호
%>

<jsp:useBean id="toDay" class="java.util.Date" />
<fmt:formatDate value="${toDay}" pattern="yyyy-MM-dd HH:mm:ss" var="toDay"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script language="javascript">
	function parkBtn(flag) {
		
		if(document.manage.carno.value == ''){
			alert("차량번호를 입력해 주세요");
			return false;
		}
		
		if (flag == 1) {
			document.manage.action = "parkManageInView.do";
		} else {
			document.manage.action = "parkManageOutView.do";
		}
		document.manage.submit();
	}

	function check() {
		
		if(document.writeparkin.tstat.value == 'I' && document.writeparkin.tstat.value != ''){
			alert("이미 입고된 차량입니다.");
			return false;
		}
		
		if (confirm("[차량입고]하시겠습니까? ") == true) { //확인
			document.writeparkin.action = "parkManageIn.do";
			document.writeparkin.submit();
		} else { //취소
			return false;
		}
	}
	
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:import url="/header.jsp" />
<title>주차입고</title>
</head>
<body>
	<c:import url="/menu.jsp" />
	<h2>주차 차량 입고 관리</h2>

	<div id="manageform">
		<form name="manage" method="post">
			차량번호 <input type="text" name="carno">
			<button type="button" onclick="parkBtn(1)">주차입고</button>
			<button type="button" onclick="parkBtn(2)">주차출고</button>
		</form>
	</div>
	
	<form name="writeparkin" method="post">
		<table border='1' align="center">
			<c:choose>
			<c:when test="${!empty parkManageVO.item}">
					<c:forEach var="parkingIn" items="${parkManageVO.item}">
					
						<input type=hidden name=tstat value="${parkingIn.tstat}" />
						
						<tr>
							<td>정기권 번호</td>
							<td><input type=text name=parkno value="${parkingIn.tno}"
								readonly /></td>
						</tr>

						<tr>
							<td>차량 번호</td>
							<td><input type=text name=carno value="${parkingIn.carno}" readonly /></td>
						</tr>

						<tr>
							<td>차주 전화</td>
							<td><input type=text name=phone value="${parkingIn.phone}"
								readonly /></td>
						</tr>

						<tr>
							<td>등급</td>
							<td><input type=text name=grade value="${parkingIn.grade}" readonly /></td>
						</tr>

						<tr>
							<td>주차시작일</td>
							<td><input type=text name=indate value="${toDay}" readonly /></td>
						</tr>

						<tr>
							<td>주차종료일</td>
							<td><input type=text readonly /></td>
						</tr>

						<tr>

							<td>비고</td>
							<c:choose>
								<c:when test="${parkingIn.grade eq 'Y'}">
									<td><input type=text name=etc value="연회원입니다" readonly /></td>
								</c:when>
								<c:when test="${parkingIn.grade eq 'M'}">
									<td><input type=text name=etc value="월간 회원입니다" readonly /></td>
								</c:when>
								<c:otherwise>
									<td><input type=text name=etc value="일일 주차 차량입니다" readonly /></td>
								</c:otherwise>
							</c:choose>

						</tr>

						<tr>
							<td>다음주차 번호</td>
							<td><input type=text name=nextno value="${maxParkNum}" readonly /></td>
						</tr>
					</c:forEach>
					
				</c:when>
				
				<c:otherwise>
					<input type=hidden name=tstat value="" />
					<tr>
						<td>차량 번호</td>
						<td><input type=text name=carno  value="<%=carno%>" readonly/></td>
					</tr>
					
					<tr>
						<td>차주 전화</td>
						<td><input type=text name=phone /></td>
					</tr>

					<tr>
						<td>등급</td>
						<td><input type=text name=grade value="D" readonly /></td>
					</tr>

					<tr>
						<td>주차시작일</td>
						<td><input type=text name=indate value="${toDay}" readonly /></td>
					</tr>

					<tr>
						<td>주차종료일</td>
						<td><input type=text  readonly /></td>
					</tr>

					<tr>
						<td>비고</td>
						<td><input type=text name=etc value="일일 주차 차량입니다" readonly/></td>
					</tr>

					<tr>
						<td>다음주차 번호</td>
						<td><input type=text name=nextno value="${maxParkNum}" readonly/></td>
					</tr>
				</c:otherwise>
				
			</c:choose>
			<tr>
				<td colspan=2><input type=button value=입고확인 onclick="check()"></td>
			</tr>
		</table>

	</form>

	<div id="tablePanel"></div>
	<c:import url="/footer.jsp" />
</body>
</html>