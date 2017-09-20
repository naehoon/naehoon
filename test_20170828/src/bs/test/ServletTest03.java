package bs.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletTest03 extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = resp.getWriter();
		
		//iterator 는 enumeration 의 옛날 버전이다. 
		//http 프로토콜에 실어서 서버에 전송했다.
		Enumeration names = req.getParameterNames();
		
		while(names.hasMoreElements()){
			
			String name = (String)names.nextElement();
			String value = req.getParameter(name);
			
			out.println(name  + " : " + value );
		}
		
	}

}
