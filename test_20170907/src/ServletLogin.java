import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class ServletLogin extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getMethod();

		if (method.equals("POST")) { //로그인 버튼 눌렀을때
			
			HttpSession session = req.getSession(true);
			req.setCharacterEncoding("utf-8");
			String id = req.getParameter("id");
			String pw = req.getParameter("pw");

			resp.setContentType("text/html;charset=utf-8");
			PrintWriter out = resp.getWriter();

			Connection conn = null;
			PreparedStatement pstmt = null;
			String selectQuery = "SELECT ID, PASSWORD FROM member WHERE ID = ?";

			try {

				Context context = new InitialContext();
				DataSource source = (DataSource) context.lookup("java:comp/env/jdbc/myconn");
				conn = source.getConnection();

			} catch (Exception e) {
				// TODO: handle exception
			}

			try {
				pstmt = conn.prepareStatement(selectQuery);
				pstmt.setString(1, id);
				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) { // rs 널체크 (아이디가 존재 할경우)

					if (!pw.equalsIgnoreCase(rs.getString(2))) { // 패스워드 검사
						out.println("<script type='text/javascript'>");
						out.println("alert('비밀번호가 정확하지 않습니다.');");
						out.println("history.back();");
						out.println("</script>");
						out.flush();
					} else {
  				        session.setAttribute("author", rs.getString(1));
//						req.setAttribute("author", rs.getString(1));
						ServletContext context = this.getServletContext();
						RequestDispatcher dispatcher = context.getRequestDispatcher("/ServletWrite");
						dispatcher.forward(req, resp);
					}

				} else if (!rs.next()) { // rs 널체크 (아이디가 존재 하지 않을경우)
					out.println("<script type='text/javascript'>");
					out.println("alert('해당 아이디가 존재하지 않습니다.');");
					out.println("history.back();");
					out.println("</script>");
					out.flush();
				}

				pstmt.close();
				conn.close();

			} catch (Exception e) {
				// TODO: handle exception
			}
		}else if(method.equals("GET")){ //회원가입 버튼 눌렀을때
			resp.sendRedirect("./join.html");
		}
	}
}
