

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletEx01 extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = resp.getWriter(); //응답객체가 제공하는 getWriter  를 얻어라
		
		out.println("<html>");
		out.println("<head><title>sertver Test</title></head>");
		out.println("<body>");
		out.println("hello servlet");
		out.println("</body>");
		out.println("</html>");
		
	}
}
