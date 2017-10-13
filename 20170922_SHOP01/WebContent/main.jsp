<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@page import="java.io.IOException"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.naming.*"%>
<%@ page import="javax.servlet.*"%>
<%@ page import="com.bs.bean.Jangbaguni"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>쇼핑몰 세일</title>

<script language="javascript">

	function jang(name, price) {
		location.href="./jang_process.jsp?name=" + name + "&price=" + price;
	}
	
	function move_jang() {
		location.href="./jang_baguni.jsp";
	}
	
	function move_product() {
		location.href="./product_regi.jsp";
	}
</script>

</head>
<body>
	<center>
		<h2>SHOPPING MALL</h2>
		<table width="800" border="0">
			<tr>
				<td align="right" colspan="3"><input type="button"
					value="내 장바구니" onClick="move_jang()" style="border: none">
					<input type="button" value="상품등록" onClick="move_product()"
					style="border: none"></td>
			</tr>
			<%
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				ArrayList<HashMap<String, Object>> productsArr = new ArrayList<HashMap<String, Object>>();

				try {

					String jdbcDriver = "jdbc:mysql://localhost:3306/naehoon?" + "useUnicode=true&characterEncoding=utf-8";
					String dbUser = "root";
					String dbPass = "";

					String query = "SELECT * FROM PRODUCTS";
					conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);

					pstmt = conn.prepareStatement(query);

					rs = pstmt.executeQuery();

					ResultSetMetaData md = rs.getMetaData();
					int columns = md.getColumnCount();

					if (rs.next()) {
						
						int i=0;
						while (rs.next()) {
// 							System.out.println("result Set  :" + rs.getString("P_NAME"));

							if (i % 3 == 0)
								out.println("<tr>");

							out.println("<td><table border='0'>");
							out.println("<tr height='150'><td align='center'>");

							out.println("<img src='" + rs.getString("P_IMG") + "' width='250' height='130'/>");
							out.println("</td></tr>");
							out.println("<tr height='20'><td align='center'>");
							out.println("<b>" + rs.getString("P_NAME") + "</b></td></tr>");

							out.println("<tr height='20'><td align='center'>");
							out.println("가격 : " + rs.getString("P_PRICE") + "원</td></tr>");
							out.println("<tr height='20'><td align='right'>");

							out.println("<input type='button' value='담기' ");
							out.println("onClick='jang(\"" + rs.getString("P_NAME") + "\",\"" + rs.getString("P_PRICE")
									+ "\")' style='border:none'/>");
							out.println("</td></tr>");
							out.println("</table></td>");

							if (i % 3 == 2)
								out.println("</tr>");
							i++;
						}

					}

					pstmt.close();
					conn.close();

				} catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			%>
		</table>
	</center>
</body>
</html>