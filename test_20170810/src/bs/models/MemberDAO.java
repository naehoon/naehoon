package bs.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;

public class MemberDAO {

	private Connection conn;

	public MemberDAO() throws ServletException {

		try {

			Class.forName("org.gjt.mm.mysql.Driver");

		} catch (ClassNotFoundException e) {
			throw new ServletException("driver error");
		}

		String url = "jdbc:mysql://localhost:3306/naehoon";
		String id = "root";
		String pw = "";

		try {
			conn = DriverManager.getConnection(url, id, pw);

		} catch (SQLException e) {
			throw new ServletException("connection error");
		}
	}

	public boolean registerMember(MemberDTO dto) throws ServletException {
		String query = "insert into member values(null, ?, ?, ?)";

		try {
			PreparedStatement pstmt = conn.prepareStatement(query);

			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getTel());
			pstmt.setString(3, dto.getAddr());
			pstmt.executeUpdate();
			pstmt.close();

		} catch (SQLException e) {
			throw new ServletException("insert failure"); //servlet Exception 을 걸어줘야 응답번호 에러 내용을 볼수 있다.
			//그렇지 않으면 java App 의 예외처리가 실행된다.
		} finally {
			this.close();
		}
		return true;
	}

	private void close() {
		// TODO Auto-generated method stub
		try{
			if(conn!=null && !conn.isClosed()){
				conn.close();
			}
		}catch(SQLException e){
			conn = null;
		}
		
	}

}
