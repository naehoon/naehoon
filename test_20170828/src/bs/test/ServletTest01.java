package bs.test;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletTest01 extends HttpServlet{

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String local_addr = req.getLocalAddr();
		String local_name = req.getLocalAddr();
		int local_post = req.getLocalPort();
		
		
		System.out.println("local_addr : " + local_addr);
		System.out.println("local_name : " + local_name);
		System.out.println("local_post : " + local_post);
		
		
		String remote_addr = req.getRemoteAddr();
		String remote_host = req.getRemoteHost();
		int remote_post = req.getRemotePort();
		
		System.out.println("remote_addr : " + remote_addr);
		System.out.println("remote_host : " + remote_host);
		System.out.println("remote_post : " + remote_post);
		System.out.println();
		
		
		int content_length = req.getContentLength();
		String content_type=  req.getContentType();
		
		System.out.println("content_length : " + content_length);
		System.out.println("content_type : " + content_type);
		
		
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

}
