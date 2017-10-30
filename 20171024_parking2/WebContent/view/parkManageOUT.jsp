<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

	//출고 확인
	function check() {
		//출차 상태라면 
		if(document.writeparkout.tstat.value == 'O'){
			alert("이미 출차완료된 차량입니다.");
			return false;
		}
		
		if (confirm("[차량출고]하시겠습니까?") == true) { //확인
			
			document.writeparkout.submit();
		} else { //취소
			return false;
		}
	}
</script>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:import url="/header.jsp" />
<title>주차출고</title>
</head>
<body>
	
	<c:import url="/menu.jsp" />
	<h2>주차 차량 출고 관리</h2>

	<div id="manageform">
		<form name="manage" method="post">
			<input type="hidden" name="cmd"> 차량번호 <input type="text" name="carno">
			<button type="button" onclick="parkBtn(1)">주차입고</button>
			<button type="button" onclick="parkBtn(2)">주차출고</button>
		</form>
	</div>
	<form name="writeparkout" action="parkManageOut.do" method="post">
		<table border='1' align="center">

			<c:choose>
				<c:when test="${!empty parkManageVO.item}">
					<c:forEach var="parkingIn" items="${parkManageVO.item}">
						<tr>
							<td>주차 번호</td>
							<td><input type=text name=parkno value="${parkingIn.parkno}"
								readonly /></td>
						</tr>

						<tr>
							<td>차량 번호</td>
							<td><input type=text name=carno value="${parkingIn.carno}"
								readonly /></td>
						</tr>

						<tr>
							<td>등급</td>
							<td><input type=text name=grade value="${parkingIn.grade}" readonly /></td>
						</tr>
						
						<tr>
							<td>상태</td>
							<td><input type=text name=tstat value="${parkingIn.tstat}" readonly /></td>
						</tr>

						<tr>
							<td>입고일시</td>
							<td><input type=text name=indate value="${parkingIn.indate}" readonly /></td>
						</tr>

						<tr>
							<td>출고일시</td>
							<td><input type=text name="enddate" value="${parkingIn.outdate}" readonly /></td>
						</tr>

						<tr>
							<td>비고</td>
							<c:choose>
								<c:when test="${parkingIn.tstat eq 'I'}">
									<td><input type=text name=etc value="출차 준비 차량입니다." readonly /></td>
								</c:when>
								<c:otherwise>
									<td><input type=text name=etc value="출차 완료된 차량입니다." readonly /></td>
								</c:otherwise>
							</c:choose>
							
						</tr>
			</c:forEach>
			
			
			</c:when>

			<c:otherwise>
				<tr>
					<td>차량 번호</td>
					<td><input type=text name=carno /></td>
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
					<td><input type=text name=indate readonly /></td>
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
				<td colspan=2><input type=button value=출고확인  onclick="check()"></td>
			</tr>
		</table>

	</form>

	<div id="tablePanel"></div>
	<c:import url="/footer.jsp" />
</body>
</html>