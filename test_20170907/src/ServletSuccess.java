import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.SizeLimitExceededException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class ServletSuccess extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String subject = "";
		String author =  "";
		String pw = "";
		String contents ="";
		String filepath = "";
		String filename = "";
		resp.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = resp.getWriter();
		
		boolean check = ServletFileUpload.isMultipartContent(req);

		if (check) {
			ServletContext context = this.getServletContext();
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
				
				ArrayList items = (ArrayList) upload.parseRequest(req);
				
				for (int i = 0; i < items.size(); i++) {
					
					FileItem item = (FileItem)items.get(i);
					String value = item.getString("utf-8");
					
					if(item.isFormField()){
						if(item.getFieldName().equals("subject")){
							subject = value;
						}else if(item.getFieldName().equals("author")){
							author = value;
						}else if(item.getFieldName().equals("pw")){
							pw = value;
						}else if(item.getFieldName().equals("contents")){
							contents = value;
						}
					}else{
						if(item.getFieldName().equals("upfiles")){
							filename = item.getName();
							if(filename == null || filename.trim().length() == 0){
								continue;
							}
							filename = filename.substring(filename.lastIndexOf("\\")+1);
							
							File file = new File(dir, filename);
							item.write(file);
						}
					}
				}
			} catch (FileUploadException fe) {
				// TODO Auto-generated catch block
				fe.printStackTrace();
			}catch (SizeLimitExceededException se) {
				// TODO: handle exception
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			resp.setContentType("text/html;charset=utf-8");
			
			out.println("<html><body><table border=1><h3 align=left>");
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			String insertQuery = "INSERT INTO MYBBS VALUES (NULL, ?,?,?,?,?,?)";
			String selectQuery = "SELECT "
					+ " SUBJECT"
					+ ",AUTHOR"
					+ ",CONTENS"
					+ ",FILEPATH "
					+ ",FILENAME "
					+ " FROM MYBBS "
					+ " WHERE AUTHOR = ? ORDER BY NO DESC LIMIT 1";
			
			try{
				Context contxt = new InitialContext();
				
				DataSource source = (DataSource)contxt.lookup("java:comp/env/jdbc/myconn");
				conn = source.getConnection();
				
			}catch (Exception e) {
			}

			try{
				
				pstmt = conn.prepareStatement(insertQuery);
				
				pstmt.setString(1, subject);
				pstmt.setString(2, author);
				pstmt.setString(3, contents);
				pstmt.setString(4, pw);
				pstmt.setString(5, filepath);
				pstmt.setString(6, filename);
				
				int res = pstmt.executeUpdate();
				
				if(res > 0){
					
					try{
						
						pstmt = conn.prepareStatement(selectQuery);
						pstmt.setString(1, author);
						ResultSet rs = pstmt.executeQuery();
						
						while(rs.next()){
							out.println("success");
							out.println("<tr><td>제목     </td><td>" + rs.getString(1)+ "</td></tr>");
							out.println("<tr><td>글쓴이  </td><td>" + rs.getString(2)+ "</td></tr>");
							out.println("<tr><td>내용     </td><td>" + rs.getString(3)+ "</td></tr>");
							out.println("<tr><td>파일경로     </td><td>" + rs.getString(4)+ "</td></tr>");
							out.println("<tr><td>파일명     </td><td>" + rs.getString(5)+ "</td></tr>");
						}
						
					}catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				pstmt.close();
				conn.close();
				
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			out.println("</h3></body></table></html>");

		}
		
	}

}
