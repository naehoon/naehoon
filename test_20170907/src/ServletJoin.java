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

public class ServletJoin extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	req.setCharacterEncoding("utf-8");
		
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String insertQuery = "INSERT INTO MEMBER (ID, PASSWORD) VALUES (?,?)";
		
		try{
			Context context = new InitialContext();
			
			DataSource source = (DataSource)context.lookup("java:comp/env/jdbc/myconn");
			conn = source.getConnection();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		try{
			pstmt = conn.prepareStatement(insertQuery);
			
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			int res = pstmt.executeUpdate();
			
			if(res > 0){ 
				out.println("<script type='text/javascript'>");
				out.println("alert('회원가입 성공.');");
				out.println("location.href=\"./login.html\"");
				out.println("</script>");
				out.flush();
			}
			
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			out.println("<script type='text/javascript'>");
			out.println("alert('회원가입 실패.');");
			out.println("history.back();");
			out.println("</script>");
			out.flush();

		}
		
	}
	
}
