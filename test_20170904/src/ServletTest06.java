import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


public class ServletTest06 extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		
		String subject = req.getParameter("subject");
		String author = req.getParameter("author");
		String contents = req.getParameter("contents");
		
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.println("<html><body><table border=1><h3 align=left>");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String insertQuery = "INSERT INTO MYBBS VALUES (NULL, ?,?,?)";
		String selectQuery = "SELECT * FROM MYBBS WHERE author = ?";
		
		try{
			Context context = new InitialContext();
			System.out.println("@@@@@@@@@@@@@@@@@");
			
			DataSource source = (DataSource)context.lookup("java:comp/env/jdbc/myconn");
			conn = source.getConnection();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		try{
			
			pstmt = conn.prepareStatement(insertQuery);
			
			pstmt.setString(1, subject);
			pstmt.setString(2, author);
			pstmt.setString(3, contents);
			
			int res = pstmt.executeUpdate();
			
			if(res > 0){
				System.out.println("!#########################");
				
				try{
					
					pstmt = conn.prepareStatement(selectQuery);
					pstmt.setString(1, author);
					ResultSet rs = pstmt.executeQuery();
					
					while(rs.next()){
						out.println("success");
						out.println("<tr><td>제목     </td><td>" + rs.getString(2)+ "</td></tr>");
						out.println("<tr><td>글쓴이  </td><td>" + rs.getString(3)+ "</td></tr>");
						out.println("<tr><td>내용     </td><td>" + rs.getString(4)+ "</td></tr>");
					}
					
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		out.println("</h3></body></table></html>");
		
	}
}
