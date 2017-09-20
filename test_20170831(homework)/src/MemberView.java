import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberView extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		//전달받은 Attr 을 VO 로 캐스팅한다.
		MemberVO mvo = (MemberVO)req.getAttribute("userInfo");
		
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		//전달받은 정보를 표시한다
		
		out.println("<html><body>");
		out.println("ID : "  + mvo.getId() + "<br/>");
		out.println("PASS : "  + mvo.getPass()+ "<br/>");
		out.println("NAME : "  + mvo.getName()+ "<br/>");
		out.println("HOBBY : "  + mvo.getHobby()+ "<br/>");
		out.println("</body></html>");
	}
}
