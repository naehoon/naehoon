import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletTest02 extends HttpServlet{

	private int cnt = 0;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		resp.setContentType("text/html;charset=utf-8");
		resp.setHeader("refresh", "3");
		
		PrintWriter out = resp.getWriter();
		out.println("<html><body>");
		out.println("cnt = " + cnt++);
		out.println("</body></html>");
		out.close();
		
	}

}
