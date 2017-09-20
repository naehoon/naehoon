import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletTest05 extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		req.setCharacterEncoding("utf-8");
		
		String area = req.getParameter("area");
		
		ServletContext context = this.getServletContext();
		RequestDispatcher dispatcher = null;
		
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		
		out.println("<html><body>");
		out.println("seoul page insert!!!</br>");
		dispatcher = context.getRequestDispatcher("/Servlet01_Seoul");
		dispatcher.include(req, resp);
		out.println("</br></br>");
		out.println("busan page insert!!!</br>");
		dispatcher = context.getRequestDispatcher("/Servlet01_Busan");
		dispatcher.include(req, resp);
		out.println("</body></html>");
		out.close();
		
//		if(area == null){
//			resp.sendError(512, "Radio button error!!!");
//			return;
//			
//		}else if(area.equals("seoul")){
//			dispatcher = context.getRequestDispatcher("/Servlet01_Seoul");
//		}else if(area.equals("busan")){
//			dispatcher = context.getRequestDispatcher("/Servlet01_Busan");
//		}
//		
//		dispatcher.forward(req, resp);
		
	}
}
