import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletTest02 extends HttpServlet {

	private File dir, file;
	private int count = 1;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext context = this.getServletContext();
		String path = context.getRealPath("/");
		String dirname = getInitParameter("dirname");
		String filename = getInitParameter("filename");

		dir = new File(path, dirname);
		if (!dir.exists()) {
			dir.mkdir();
		}

		file = new File(dir, filename);

		if (!file.exists()) {
			try {
				DataInputStream f_in = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
				count = f_in.readInt(); //꼭 리드 인트로 할필요는 없다...
				f_in.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();

		out.println("<html><body><center>");
		out.println("당신은 " + count++ + " 번째 방문자 입니다!!!");
		out.println("</center></body></html>");

		try {
			DataOutputStream f_out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
			f_out.writeInt(count);
			f_out.close();

		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		

	}

}
