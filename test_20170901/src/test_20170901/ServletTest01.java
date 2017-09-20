package test_20170901;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletTest01 extends HttpServlet {

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//	   1. page (페이지에서만 유효하다)
//	   2. request (페이지에서만 유효하다 파라메터로 전잘해주나 request 로 전달해주나 별차이 없지만 )
//	      -> 입력란이 많아질경우 
//	   3. session (browser 를 종료하지 않으면 저장되어있다. -> 크롬에서 탭만 닫았다 열었을때 로그인상태인경우)
//	   4. servletContext -> server 에 접근하는 모든 client 들이 공유하는 영역이다. (사용하는 예는 거의 없다.) 유저가 수백명일경우 부하가 발생한다
//	      -> 주로 시스템 설정정보등을 넣는다.
//	      (application 영역이라고 한다.)
	   //data 1은 세션에 담아주지 않았다.
//	  req.setAttribute("data1", "홍길동");
	  Calendar myDate=Calendar.getInstance();
	  
	  //getsession true 일경우 세션이 없다면 새로 생성하라는 뜻이다.
      HttpSession session = req.getSession(true);
      
      session.setAttribute("data1", "test");
      session.setAttribute("data2", "Java");
      session.setAttribute("data3", myDate);
      
      ServletContext context = this.getServletContext();
      RequestDispatcher dispatcher = context.getRequestDispatcher("/Servlet02");
      dispatcher.forward(req, resp);
   }
}