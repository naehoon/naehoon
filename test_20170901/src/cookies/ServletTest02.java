package cookies;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletTest02 extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		
		String id = req.getParameter("id");
		String pass = req.getParameter("pw");
		String id_rem = req.getParameter("id_rem");
		
		resp.setContentType("text/html;charset=utf-8" );
		PrintWriter out  = resp.getWriter();
		
		out.println("<html><body><center><h2>");
		out.println(id + " ( " + pass + " ) login seccess!!! ");
		out.println("</h2></center></body></html>");
		
		if(id_rem != null && id_rem.equals("chk")){
			Cookie id_cookie = new Cookie("id", java.net.URLEncoder.encode(id, "UTF-8"));
			
			id_cookie.setComment("ID saved" );
			id_cookie.setPath("/"); //모든 경로에서 사용을 한다.
			id_cookie.setMaxAge(60*60*24*365);
			
			System.out.println("@#@@@@@@@@@@@@@");
			System.out.println(id_cookie.getComment());
			System.out.println(id_cookie.getPath());
			System.out.println(id_cookie.getMaxAge());
			System.out.println(id_cookie.getValue());
			System.out.println("@#@@@@@@@@@@@@@");
			
			//쿠키 저장시키기 
			resp.addCookie(id_cookie);
			
		}
		
		
		
	}

}
