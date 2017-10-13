<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="bs.com.service.ViewParkService"%>
<%@ page import="bs.com.service.ParkmanageService"%>
<%@ page import="bs.com.model.ParkingViewVO"%>

<%
	ViewParkService viewParkService = ViewParkService.getInstance();
	ParkmanageService parkmanageService = ParkmanageService.getInstance();

// 	ParkingViewVO parkingViewVO = viewParkService.selectParkingList();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script language="javascript">
function CreateInTable(){
	
		var txt ="";
        document.getElementById("tablePanel").innerHTML = "";
        txt = "<form name=form01 action='#' method=post>"	
        + "<table border='1'>"
        + "<tr>"
        + "<td>"
        + "정기권 번호"
        + "</td>"
        + "<td>"
        + "<input type=text name=parkno/>"
        + "</td>"        
        + "</tr>"
        
        + "<tr>"
        + "<td>"
        + "차량  번호"
        + "</td>"
        + "<td>"
        + "<input type=text name=carno/>"
        + "</td>"        
        + "</tr>"
        
        + "<tr>"
        + "<td>"
        + "차주 전화"
        + "</td>"
        + "<td>"
        + "<input type=text name=phone />"
        + "</td>"        
        + "</tr>"
        
        + "<tr>"
        + "<td>"
        + "등급"
        + "</td>"
        + "<td>"
        + "<input type=text name=grade />"
        + "</td>"        
        + "</tr>"
        
        + "<tr>"
        + "<td>"
        + "주차시작일"
        + "</td>"
        + "<td>"
        + "<input type=text name=indate />"
        + "</td>"        
        + "</tr>"
        
        + "<tr>"
        + "<td>"
        + "주차종료일"
        + "</td>"
        + "<td>"
        + "<input type=text name=outdate />"
        + "</td>"        
        + "</tr>"
        
        + "<tr>"
        + "<td>"
        + "비고"
        + "</td>"
        + "<td>"
        + "<input type=text name=etc />"
        + "</td>"        
        + "</tr>"
        
        + "<tr>"
        + "<td>"
        + "다음주차 번호"
        + "</td>"
        + "<td>"
        + "<input type=text name=nextno />"
        + "</td>"        
        + "</tr>"
        
        + "<tr>"
        + "<td colspan=2>"
        +" <input type=submit value=입고확인 >"
        + "</td>"        
        + "</tr>"       
        
        +"</table>"
        +"</form>";
        document.getElementById("tablePanel").innerHTML += txt;
        
   }
   
function CreateOutTable(){
	
	var txt ="";
    document.getElementById("tablePanel").innerHTML = "";
    txt = "<form name=form02 action='#' method=post>"	
    + "<table border='1'>"
    + "<tr>"
    + "<td>"
    + "정기권 번호"
    + "</td>"
    + "<td>"
    + "<input type=text name=parkno/>"
    + "</td>"        
    + "</tr>"
    
    + "<tr>"
    + "<td>"
    + "차량  번호"
    + "</td>"
    + "<td>"
    + "<input type=text name=carno/>"
    + "</td>"        
    + "</tr>"
    
    + "<tr>"
    + "<td>"
    + "차주 전화"
    + "</td>"
    + "<td>"
    + "<input type=text name=phone />"
    + "</td>"        
    + "</tr>"
    
    + "<tr>"
    + "<td>"
    + "등급"
    + "</td>"
    + "<td>"
    + "<input type=text name=grade />"
    + "</td>"        
    + "</tr>"
    
    + "<tr>"
    + "<td>"
    + "주차시작일"
    + "</td>"
    + "<td>"
    + "<input type=text name=indate />"
    + "</td>"        
    + "</tr>"
    
    + "<tr>"
    + "<td>"
    + "주차종료일"
    + "</td>"
    + "<td>"
    + "<input type=text name=outdate />"
    + "</td>"        
    + "</tr>"
    
    + "<tr>"
    + "<td>"
    + "비고"
    + "</td>"
    + "<td>"
    + "<input type=text name=etc />"
    + "</td>"        
    + "</tr>"
    
    + "<tr>"
    + "<td>"
    + "다음주차 번호"
    + "</td>"
    + "<td>"
    + "<input type=text name=nextno />"
    + "</td>"        
    + "</tr>"
    
    + "<tr>"
    + "<td colspan=2>"
    +" <input type=submit value=출고확인 >"
    + "</td>"        
    + "</tr>"       
    
    +"</table>"
    +"</form>";
    document.getElementById("tablePanel").innerHTML += txt;
    
}

function parkBtn(flag){
	
	alert("주차입고 버튼 클릭 :플래그 : " +flag);
	
	if(flag == 1){
		document.manage.cmd.value ="parkIn";
		<%
			ParkingViewVO parkingViewVO = parkmanageService.selectManageList();
		%>
		
	}else{
		document.manage.cmd.value ="parkOut";
	}
}
</script>
<!-- viewData 변수지정 -->
<c:set var="parkingView" value="<%=parkingViewVO%>" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<c:forEach var="message" items="${parkingView.list}">
		<h1>${message.carno}</h1>
	</c:forEach>

	<c:import url="../header.jsp" />
	<c:import url="../menu.jsp" />
	<h2>주차 차량 입고, 출고 관리</h2>
	
	<form name="manage" action="" method="post">
			<input type="hidden" name="cmd">
			차량번호 <input type="text" name="carno"> 
			<input type="button" value="주차입고" onclick="parkBtn(1)" />
			<input type="button" value="주차출고" onclick="parkBtn(2)"/>
	</form>
	
	<div id="tablePanel">
	</div>
	<c:import url="../footer.jsp" />
</body>
</html>