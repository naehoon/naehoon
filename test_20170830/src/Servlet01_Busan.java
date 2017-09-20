import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet01_Busan extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		
		String name = req.getParameter("name");
		String id = req.getParameter("id");
		String pass = req.getParameter("pw");
		String area= req.getParameter("area");
		
		
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter  out = resp.getWriter();
		
		out.println("<html><body>");
		out.println("this page is Busan area");
		out.println("name : " + name + "</br>");
		out.println("id : " + id + "</br>");
		out.println("pass: " + pass + "</br>");
		out.println("area: " + area + "</br>");
		out.println("</body></html>");
		
	}

}
