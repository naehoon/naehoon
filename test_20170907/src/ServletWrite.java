import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletWrite extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		String author = (String)session.getAttribute("author");
		PrintWriter out = resp.getWriter();
		
		out.println("<body>");
		out.println("	<center>");
		out.println("		<h2>글 쓰 기</h2>");
		out.println("		<form method=post action=ServletSuccess enctype=multipart/form-data>");
		out.println("			<table border=1 width=420>");
		out.println("				<tr>");
		out.println("					<th width=100>제목</th>");
		out.println("					<td align=center><input type=text name =subject size =40/></td>");
		out.println("				</tr>");
		out.println("				<tr> ");
		out.println("					<td colspan=2  align=left>");
	    out.println("						<b style= \"border: 1px solid #000000 ; padding : 3px;\" >작성자</b> <input type=text name =author value="+author+" readonly  size =18 style=padding: 2px\">");
		out.println("						<b style= \"border: 1px solid #000000 ; padding : 3px;\">비번</b><input type=password name =pw size =18 style=padding: 2px\">");
		out.println("					</td>");
		out.println("				</tr>");
		out.println("				<tr height=120>");
		out.println("					<td colspan=2 align=right>");
		out.println("						<textarea rows=10 cols=50 name =contents></textarea>");
		out.println("					</td>");
		out.println("				</tr>");
		out.println("				<tr>");
		out.println("					<th width=120>파일첨부</th>");
		out.println("					<td align=left><input type=file name=upfiles size=30 /></td>");
		out.println("				</tr>");
		out.println("				<tr>");
		out.println("					<td colspan=2 align=right><input type=submit value=글쓰기>");
		out.println("															<input type=reset value =취소>");
		out.println("					</td>");
		out.println("				</tr>");
		out.println("			</table>");
		out.println("		</form>");
		out.println("	</center>");
		out.println("</body>");
		
	}
}
