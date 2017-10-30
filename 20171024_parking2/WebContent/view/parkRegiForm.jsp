<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script language="javascript">
//입고 및 출고 버튼에 따른 분기처리 
function submitBtn(flag){
	
// 	if(document.regiform.carno.value == ''){
// 		alert("차량번호를 입력해 주세요");
// 		return false;
// 	}
	
	if(flag == 1){
		//등록버튼
		document.regiform.action = "parkRegiWrite.do";
	}else{
		//조회버튼
		document.regiform.action = "parkRegiSelect.do";
	}
	document.regiform.submit();
}
</script>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>주차정기권</title>
</head>
<body>
<c:import url="/header.jsp" />
<c:import url="/menu.jsp" />

	<h2>주차 정기권 등록 , 조회</h2>
	<form name="regiform" action="parkRegiWrite.do" method="post">
		<table border="1" width="950px" align="center">
			
			<tr>
				<td >정기권 번호 (자동발생)</td>
				<td align="left"><input type="text" name="tno"  readonly value="${parkingRegiVO.getTno()}"></td>
			</tr>
			<tr>
				<td width="300px">* 차량번호</td>
				<td  align="left" >
					<input type="text" name="carno">
					<c:if test="${errors.carno}">차량번호를 확인해주세요 </c:if>
					<c:if test="${errors.duplicateId}">이미 등록된 차량번호 입니다.</c:if>
				</td>
			</tr>
			<tr>
				<td>차주전화</td>
				<td  align="left"><input type="text" name="phone">
				</td>
			</tr>
			<tr>
				<td>주차등급(M/Y)</td>
				<td  align="left">
					<input type="radio" name="grade" value="M" checked="checked">M
					<input type="radio" name="grade" value="Y">Y
				</td>
			</tr>
			<tr>
				<td>정기권상태(Y/N)</td>
				<td  align="left">
					<input type="radio" name="tstat" value="Y" checked="checked">Y
					<input type="radio" name="tstat" value="N">N
				</td>
			</tr>
			<tr>
				<td>* 시작일(YYYYMMDD)</td>
				<td  align="left">
					<input type="text" name="startdate">
					<c:if test="${errors.startdate}">시작일자를 확인해주세요 (8자리 숫자 YYYYMMDD) </c:if>
				</td>
				
			</tr>
			<tr>
				<td>* 종료일(YYYYMMDD)</td>
				<td  align="left"><input type="text" name="enddate">
				<c:if test="${errors.enddate}">종료일자를 확인해주세요 (8자리 숫자 YYYYMMDD)</c:if>
				</td>
			</tr>
			<tr>
				<td colspan="2">
<!-- 				<input type="submit" value="등록"> -->
				<button type="button" onclick="submitBtn(1)">등록</button>
				<button type="button" onclick="submitBtn(2)">조회</button> 
			</tr>
			
			
		</table>
	</form>
	<c:import url="/footer.jsp" />
	
</body>
</html>