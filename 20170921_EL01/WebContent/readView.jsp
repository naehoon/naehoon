<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.*"%>
<%@ page import="jxl.*"%>
<%
	String path = application.getRealPath("/excel_data");
	File dir = new File(path);
	if (!dir.exists()) {
		dir.mkdir();
	}

	File excel_file = new File(dir, "sample.xls");

	Workbook book = Workbook.getWorkbook(excel_file);
	Sheet sheet = book.getSheet(0);

	int rows = sheet.getRows();
	int cols = sheet.getColumns();

	String title = sheet.getCell(1, 0).getContents();

	String[] header = new String[cols];

	for (int i = 0; i < header.length; i++) {
		header[i] = sheet.getCell(i, 2).getContents();
	}

	String[][] data = new String[rows - 3][cols];
	for (int i = 0; i < data.length; i++) {
		for (int j = 0; j < data[i].length; j++) {
			data[i][j] = sheet.getCell(j, i + 3).getContents();
		}
	}

	if (book != null) {
		book.close();
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<h2>엑셀 파일 출력 하기</h2>
		<hr width="80%" />
		<table width="800" border="1">
			<tr>
				<%
					String[] width = new String[] { "80", "70", "150", "200" };
					for (int i = 0; i < header.length; i++) {
						out.println("<td width=''" + width[i] + "'>" + header[i] + "</td>");
					}
				%>
			</tr>
			<tbody>
			<col align="center" />
			<col align="center" />
			<col align="center" />
			<col align="left" />
			<%
				for (int i = 0; i < data.length; i++) {
					out.println("<tr>");
					for (int j = 0; j < data[i].length; j++) {
						out.println("<td>" + data[i][j] + "</td>");
					}
					out.println("</tr>");
				}
			%>
			</tbody>
		</table>
	</center>
</body>
</html>