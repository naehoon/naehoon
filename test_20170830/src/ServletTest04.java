import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletTest04 extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		super.service(arg0, arg1);
		
		String method = req.getMethod();
		
		if(method.equals("GET")){
			resp.sendRedirect("http://localhost:8282/test_20170830/getTest01.html");
		}else if(method.equals("POST")){
			resp.sendRedirect("./postTest01.html");
		}
		
	}

}
