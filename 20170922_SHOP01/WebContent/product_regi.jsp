<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script language="javascript">
	function move_main() {
		location.href="./main.jsp";
	}
	
	function check() {
// 		alert("regist_form.pimage.value : " + regist_form.pimage.value);
// 	    if(isNaN(document.getElementsByName("pprice")[0].value) || isNaN(document.getElementsByName("pcnt")[0].value)){
// 		var img = new Image();
// 		img.dynsrc = regist_form.pimage.value;
// 		var filesize = img.fileSize;
// 		alert("fileSize : " + filesize);
		
		if(isNaN(regist_form.pprice.value) || isNaN(regist_form.pcnt.value)){
	          alert("가격 및 수량은 숫자만 입력 가능합니다.");
	          return false;
	          
	    }else if(regist_form.pname.value == "" || regist_form.pcnt.value == "" || regist_form.pprice.value == "" || regist_form.pimage.value == "" ){
	    	alert("모든 항목을 입력해주세요 ");
	    	return false;
	    	
	    }else{
	    	regist_form.submit();
	    }
	}

</script>

</head>
<body>
	<form name="regist_form" action="product_process.jsp" method="post" enctype="multipart/form-data">
		<table border="1">
			<tr>
				<td><b>상품명</b></td>
				<td><input type="text" name="pname" align="right" /></td>
			</tr>
			<tr>
				<td><b>가격</b></td>
				<td><input type="text" name="pprice" align="right"/></td>
			</tr>
			<tr>
				<td><b>보유수량</b></td>
				<td><input type="text" name="pcnt" align="right"/></td>
			</tr>
			<tr>
				<td><b>이미지</b></td>
				<td><input type="file" name="pimage" align="right" /></td>
			</tr>
			<tr>
				<td colspan="2" align="right">
				    <input type="button" value="저장"  onclick="check()"/>
					<input type="button" onclick="move_main()"  value="메인으로" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>