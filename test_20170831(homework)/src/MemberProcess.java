import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberProcess extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//MemberVO 객체 생성
		MemberVO mvo = new MemberVO();
		req.setCharacterEncoding("utf-8");
		
		//전송받은 자료를 member 객체에 저장한다.
		mvo.setId(req.getParameter("id"));
		mvo.setName(req.getParameter("name"));
		mvo.setPass(req.getParameter("pass"));
		mvo.setHobby(req.getParameter("hobby"));
	
		//저장된 Member 객체를 request 영역에 저장한다.
		req.setAttribute("userInfo", mvo);
		
		//MemberView.java 로 전달
		ServletContext context = this.getServletContext();
		
		RequestDispatcher dispatcher = null;
		dispatcher = context.getRequestDispatcher("/MemberView");
		dispatcher.forward(req, resp);
	}
}
