package cookies;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletTest01 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = "";
		
		//클라이언트의 모든 쿠키를 다 가져온다.
		Cookie[] cookies = req.getCookies();
		
		for (int i=0; cookies!=null && i<cookies.length; i++) {
			String key = cookies[i].getName();
			//ID라는 쿠키가 있다면
			//키를 묶어놓은것은 entryset 이다.
			if(key.equals("id")){
				id = cookies[i].getValue();
				id = java.net.URLDecoder.decode(id, "UTF-8");
			}
		}
		
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		
		out.println("<html><body>");
		out.println("		<form method='post' action='Setvlet02'>");
		out.println("		<table border='1'>");
		out.println("			<tr>");
		out.println("				<td align='right' width='30%'>ID : </td>");
		out.println("				<td align='center'><input type='text' name='id' value='"+id+"' size='10' /></td>");
		out.println("				<td align='center'><input type='submit' value='로그인' /></td>");
		out.println("			</tr>");
		out.println("			<tr>");
		out.println("				<td align='right' width='30%'>PASS : </td>");
		out.println("				<td align='center'><input type='password' name='pw' size='10' /></td>");
		out.println("			</tr>");
		out.println("			<tr>");
		out.println("				<td colspan='3'><input type='checkbox' name='id_rem' value='chk' />아이디 기억하기");
		out.println("			</tr></table>");
		out.println("		</form>"	);
		out.println("</body></html>");
		
	}
}
