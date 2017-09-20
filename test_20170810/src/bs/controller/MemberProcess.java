package bs.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bs.models.MemberDAO;
import bs.models.MemberDTO;

public class MemberProcess extends HttpServlet {   //httpservlet 을 상속받으면 전부다 서블릿이다.

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//req = 요청이다.
		req.setCharacterEncoding("utf-8");
		
		String name = req.getParameter("name"); //html 의 name 을 적어준다.
		String tel = req.getParameter("tel"); //tel 
		String addr = req.getParameter("addr");
		
		MemberDTO dto = new MemberDTO();
		
		dto.setName(name);
		dto.setTel(tel);
		dto.setAddr(addr);
		
		MemberDAO dao =new MemberDAO();
		boolean bool = dao.registerMember(dto);
		
		if(bool) {
			resp.sendRedirect("./success.html"); //sendRedirect란 네이버에서 링크를 걸어줄때 
			//이동 할때 사용한다.페이지의 이동처리를 한다.
			//forawrd 는 전달이다.
		}

	}

}

