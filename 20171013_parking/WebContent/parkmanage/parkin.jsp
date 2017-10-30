<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="bs.com.service.ViewParkService"%>
<%@ page import="bs.com.service.ParkmanageService"%>
<%@ page import="bs.com.model.ParkManageVO"%>
<%@ page import="bs.com.service.RegistParkService"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>

<jsp:useBean id="parkmanage" class="bs.com.model.ParkManageVO">
	<jsp:setProperty name="parkmanage" property="*" />
</jsp:useBean>

<%
	request.setCharacterEncoding("utf-8");
	String carno = request.getParameter("carno"); //차량번호
	ParkmanageService parkmanageService = ParkmanageService.getInstance(); //주차 입고 관리
	
	ParkManageVO parkManageVO = parkmanageService.selectParkIn(parkmanage); //입출고 차량 조회 
	
	int maxParkNum = parkmanageService.selectMaxParkNum(); //주차 번호 채번

	Date today = new Date();

	SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	System.out.println(" 오늘 날짜는 : " + date.format(today));
	System.out.println(" 현재 시간은 : " + datetime.format(today));
%>
<c:set var="maxParkNum" value="<%=maxParkNum%>" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script language="javascript">
	function parkBtn(flag) {
		
		if(document.manage.carno.value == ''){
			alert("차량번호를 입력해 주세요");
			return false;
		}
		
		if (flag == 1) {
			document.manage.action = "parkin.jsp";
		} else {
			document.manage.action = "parkout.jsp";
		}
		document.manage.submit();
	}

	function check() {
		if (confirm("정말 입고하시겠습니까??") == true) { //확인
			document.writeparkin.action = "writeparkInput.jsp";
			document.writeparkin.submit();
		} else { //취소
			return false;
		}
	}
	
</script>

<!-- viewData 변수지정 -->
<c:set var="parkManage" value="<%=parkManageVO%>" />

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:import url="../header.jsp" />
<title>주차입고</title>
</head>
<body>
	<c:import url="../menu.jsp" />
	<h2>주차 차량 입고 관리</h2>

	<form name="manage" method="post">
		차량번호 <input type="text" name="carno">
		<button type="button" onclick="parkBtn(1)">주차입고</button>
		<button type="button" onclick="parkBtn(2)">주차출고</button>
	</form>

	<form name="writeparkin" method="post">
		<table border='1' align="center">

			<c:choose>
				<c:when test="${!empty parkManage.item}">
					<c:forEach var="parkingIn" items="${parkManage.item}">
						<tr>
							<td>정기권 번호</td>
							<td><input type=text name=parkno value="${parkingIn.tno}"
								readonly /></td>
						</tr>

						<tr>
							<td>차량 번호</td>
							<td><input type=text name=carno value="${parkingIn.carno}"
								readonly /></td>
						</tr>

						<tr>
							<td>차주 전화</td>
							<td><input type=text name=phone value="${parkingIn.phone}"
								readonly /></td>
						</tr>

						<tr>
							<td>등급</td>
							<td><input type=text name=grade value="${parkingIn.grade}"
								readonly /></td>
						</tr>

						<tr>
							<td>주차시작일</td>
							<td><input type=text name=indate
								value="<%=datetime.format(today)%>" readonly /></td>
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
							<td><input type=text name=nextno value="${maxParkNum}"
								readonly /></td>
						</tr>
					</c:forEach>
				</c:when>

				<c:otherwise>
					<tr>
						<td>차량 번호</td>
						<td><input type=text name=carno value='<%=carno %>' /></td>
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
						<td><input type=text name=indate
							value="<%=datetime.format(today)%>" readonly /></td>
					</tr>

					<tr>
						<td>주차종료일</td>
						<td><input type=text name=outdate readonly /></td>
					</tr>

					<tr>
						<td>비고</td>
						<td><input type=text name=etc value="일일 주차 차량입니다" /></td>
					</tr>

					<tr>
						<td>다음주차 번호</td>
						<td><input type=text name=nextno value="${maxParkNum}" /></td>
					</tr>
				</c:otherwise>
			</c:choose>
			<tr>
				<td colspan=2><input type=button value=입고확인 onclick="check()"></td>
			</tr>
		</table>

	</form>

	<div id="tablePanel"></div>
	<c:import url="../footer.jsp" />
</body>
</html>