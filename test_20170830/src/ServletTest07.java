import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletTest07 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String data1 = new String("java");
		ArrayList<String> data2 = new ArrayList<String>();
		data2.add("123455");
		data2.add("abcdef");
		data2.add("test");
		
		req.setAttribute("v1", data1);
		req.setAttribute("v2", data2);
		
		ServletContext context = this.getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher("/Servlet01_request?data3=http01&data4=http02");
		dispatcher.forward(req, resp);
		
		
	}
}
