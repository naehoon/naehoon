import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ServletTest05 extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		
		String subject = req.getParameter("subject");
		String author = req.getParameter("author");
		String contents = req.getParameter("contents");
		
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.println("<html><body><center><h3>");
		try{
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String url = "jdbc:mysql://localhost:3306/naehoon";
		String id  = "root";
		String pw = "";
		
		String query = "INSERT INTO MYBBS VALUES (NULL, ?,?,?)";
		
		try {
			
			conn = DriverManager.getConnection(url, id, pw);
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, subject);
			pstmt.setString(2, author);
			pstmt.setString(3, contents);
			
			int res = pstmt.executeUpdate();
			
			if(res > 0){
				out.println("success");
			}
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		out.println("</center></h3></body></html>");
		
	}
}
