<%@page import="jxl.format.Alignment"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.io.*"%>
<%@ page import="jxl.*"%>
<%@ page import="jxl.write.*"%>

<%
	String path = application.getRealPath("/excel_data");
	File dir = new File(path);
	if (!dir.exists())
		dir.mkdir();
	File excel_file = new File(dir, "sample.xls");

	WritableWorkbook workbook = Workbook.createWorkbook(excel_file);
	WritableSheet sheet = workbook.createSheet("Sample", 0);

	Label name_lb = new Label(1, 0, "Excel 데이터 표현");
	WritableFont name_font = new WritableFont(WritableFont.COURIER, 14, WritableFont.BOLD);
	WritableCellFormat name_format = new WritableCellFormat(name_font);
	name_lb.setCellFormat(name_format);
	sheet.addCell(name_lb);

	String[] header_str = new String[] { "이름", "나이", "연락처", "주소" };
	WritableFont[] header_font = new WritableFont[4];
	WritableCellFormat[] header_format = new WritableCellFormat[header_str.length];

	for (int i = 0; i < header_str.length; i++) {
		header_font[i] = new WritableFont(WritableFont.COURIER, 12, WritableFont.BOLD);
		header_format[i] = new WritableCellFormat(header_font[i]);

		header_format[i].setAlignment(jxl.format.Alignment.CENTRE);
		Label header_lb = new Label(i, 2, header_str[i]);
		header_lb.setCellFormat(header_format[i]);
		sheet.addCell(header_lb);
	}

	Object[][] data_arr = new Object[][] { 
			{ "홍길동", 20, "010-1234-1234", "서울 금천구" },
			{ "오내훈", 20, "010-1234-1234", "서울 금천구" }, 
			{ "홍길동", 20, "010-1234-1234", "서울 금천구" } 
	};

	WritableFont left_font = new WritableFont(WritableFont.COURIER, 10, WritableFont.BOLD);
	WritableCellFormat left_format = new WritableCellFormat(left_font);
	left_format.setAlignment(jxl.format.Alignment.LEFT);

	WritableFont cen_font = new WritableFont(WritableFont.COURIER, 10, WritableFont.BOLD);
	WritableCellFormat cen_format = new WritableCellFormat(left_font);
	cen_format.setAlignment(jxl.format.Alignment.CENTRE);

	for (int i = 0; i < data_arr.length; i++) {
		for (int j = 0; j < data_arr[i].length; j++) {
			WritableCell data = null;

			if (j == 1)
				data = new jxl.write.Number(j, 3 + i, ((Integer) data_arr[i][j]).intValue());
			else
				data = new Label(j, 3 + i, (String) data_arr[i][j]);

			if (j == 3)
				((WritableCell) data).setCellFormat(left_format);
			else
				((WritableCell) data).setCellFormat(cen_format);

			sheet.addCell(((WritableCell) data));
		}
	}

	CellView tel_view = new CellView();
	tel_view.setAutosize(true); //셀사이즈 자동 조절 
	sheet.setColumnView(2, tel_view);

	CellView addr_view = new CellView();
	addr_view.setAutosize(true); //셀사이즈 자동 조절 
	sheet.setColumnView(3, addr_view);

	workbook.write();
	workbook.close();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>Excel file create OK!
</body>
</html>