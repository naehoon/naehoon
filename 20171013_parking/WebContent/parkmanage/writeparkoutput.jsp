<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="bs.com.service.ListNotFoundException"%>
<%@page import="bs.com.service.InvalidInputException"%>    
<%@ page import="bs.com.service.ParkmanageService"%>
<%@ page import="bs.com.model.ParkManageVO"%>
<%@ page import="bs.com.service.InvalidInputException"%>
<%@ page import="bs.com.service.ListNotFoundException"%>
<%
	request.setCharacterEncoding("utf-8");
%>    
<jsp:useBean id="parkinput" class="bs.com.model.ParkManageVO">
	<jsp:setProperty name="parkinput" property="*"/>
</jsp:useBean>
<%
	boolean invalidInput = false;
	boolean isEmptyFlag = false;
	String inoutFlag = "";
	ParkmanageService parkmanageService = ParkmanageService.getInstance();
	ParkManageVO parkManageVO = parkmanageService.selectParkOut(parkinput); //차량 출고전 차량의 출입고 여부 확인하기
	
	if(!parkManageVO.getItem().isEmpty()){
		inoutFlag =  parkManageVO.getItem().get(0).toString(); //스태이터스 조회값
	}
	
	if(!inoutFlag.equalsIgnoreCase("O")){
		try{
			parkmanageService.updateParkOutput(parkinput); //차량 출고 하기
		}catch(InvalidInputException ex){
			invalidInput = true;
		}catch(ListNotFoundException le){
			isEmptyFlag = true;
		}
	}

%>
<c:set var="parkManage" value="<%=parkManageVO %>" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:forEach var="parkManage" items="${parkManage.item}">
	<c:if test="${parkManage.tstat eq 'O'}">
		<script  language="javascript">
			alert("이미 출고된 차량입니다.");
			history.back(-1);
	</script>
	</c:if>
</c:forEach>

<% if(!invalidInput && inoutFlag.equalsIgnoreCase("I")){ %>
	<script  language="javascript">
		alert("차량출고가 완료되었습니다");
		history.back(-1);
	</script>
	
<%}else if(invalidInput){ %>
	<script  language="javascript">
		alert("출고 과정에서 오류가 발생했습니다.");
		history.back(-1);
	</script>
	
<%}else if(isEmptyFlag){ %>
	<script  language="javascript">
		alert("존재하지 않는 차량입니다");
	</script>
<%}%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
</body>
</html>