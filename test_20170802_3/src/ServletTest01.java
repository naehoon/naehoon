

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletTest01 extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = resp.getWriter(); //응답객체가 제공하는 getWriter  를 얻어라
		
		out.println("<html>");
		out.println("<head><title>sertver Test</title></head>");
		out.println("<body>");
		out.println("<table border=1 align = \"left\">");
		out.println("		<tr>");
		out.println("			<td>오내훈</td>");
		out.println("		</tr>");
		out.println("		<tr>");
		out.println("			<td>010-4219-0903</td>");
		out.println("		</tr>");		
		out.println("</body>");
		out.println("</html>");
		
	}
}
