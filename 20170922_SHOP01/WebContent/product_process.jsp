<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@page import="java.io.IOException"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.naming.*"%>
<%@ page import="javax.servlet.*"%>
<%@ page import="org.apache.commons.fileupload.*"%>
<%@ page import="org.apache.commons.fileupload.disk.*"%>
<%@ page import="org.apache.commons.fileupload.servlet.*"%>
<%
	String pName = "";
	int pPrice = 0;
	int pCnt = 0;
	String filepath = "";
	String filename = "";
	response.setContentType("text/html;charset=utf-8");

	boolean check = ServletFileUpload.isMultipartContent(request); 

	if (check) {
		ServletContext context = this.getServletContext();
// 		String path = context.getRealPath("/upload");
		String path = context.getRealPath("/upload");
		File dir = new File(path);
		filepath = dir.toString(); //파일경로 가져오기

		if (!dir.exists()) {
			dir.mkdir();
		}
		try {

			DiskFileItemFactory factoty = new DiskFileItemFactory();
			factoty.setSizeThreshold(10 * 1024);
			factoty.setRepository(dir);

			ServletFileUpload upload = new ServletFileUpload(factoty);
			upload.setSizeMax(10 * 1024 * 1024);

			ArrayList items = (ArrayList) upload.parseRequest(request);

			for (int i = 0; i < items.size(); i++) {

				FileItem item = (FileItem) items.get(i);
				
				String value = item.getString("utf-8");
				
				System.out.println("@@@@@@@@@@@@  " + item.getFieldName());

				if (item.isFormField()) {
					if (item.getFieldName().equalsIgnoreCase("pname")) {
						pName = value;
					} else if (item.getFieldName().equalsIgnoreCase("pprice")) {
						pPrice = Integer.parseInt(value);
					} else if (item.getFieldName().equalsIgnoreCase("pcnt")) {
						pCnt = Integer.parseInt(value);
					}
					
				} else {
					if (item.getFieldName().equalsIgnoreCase("pimage")) {
						filename = item.getName();
						if (filename == null || filename.trim().length() == 0) {
							continue;
						}
						filename = filename.substring(filename.lastIndexOf("\\") + 1);

						File file = new File(dir, filename);
						item.write(file);
					}
				}
			}
		} catch (FileUploadException fe) {
			// TODO Auto-generated catch block
			fe.printStackTrace();
		} catch (SizeLimitExceededException se) {
			// TODO: handle exception
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		response.setContentType("text/html;charset=utf-8");

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			
			String jdbcDriver = "jdbc:mysql://localhost:3306/naehoon?" + "useUnicode=true&characterEncoding=utf-8";
			String dbUser = "root";
			String dbPass = "";

			String query = "INSERT INTO PRODUCTS VALUES(null, ?,?,?,?)";
			conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, pName);
			pstmt.setInt(2, pPrice);
			pstmt.setInt(3, pCnt);
			pstmt.setString(4, filepath+"\\"+filename);

			int res = pstmt.executeUpdate();
			
			if(res >= 1){
%>
				<script>
					alert("물품 등록 성공.");
					history.go(-1);
				</script>
<%				
			}else{
%>
				<script>
					alert("물품 등록 실패.");
				</script>
<%				
			}
		
			pstmt.close();
			conn.close();

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
%>