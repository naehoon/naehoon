import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletTest01 extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		
		String name = req.getParameter("name");
		String age = req.getParameter("age");
		String school = req.getParameter("school");
		String filename = req.getParameter("filename");
		
		ServletContext context = this.getServletContext();
		String path = context.getRealPath("/PersonInfo");
		
		File dir = new File(path);
		
		if(!dir.exists()){
			dir.mkdir();
		}
		
		File file = new File(dir, filename);
		
		PrintWriter f_out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
		
		f_out.println("name : " + name);
		f_out.println("age : " + age );
		f_out.println("school : " + school);

		f_out.close();
		
		resp.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = resp.getWriter();
		
		out.println("<html><body>");
		out.println(path + File.separator + filename + " 위치에 저장");
		out.println("</body></html>");
		
		
	}

}
