package bs.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		String query = "INSERT INTO MEMBER "
				+ " (NUM "
				+ ", ID"
				+ ", PASSWORD"
				+ ", NAME"
				+ ", GENDER"
				+ ", HOBBY"
				+ ", TEL"
				+ ", ADDR"
				+ ") "
				+ "  VALUES"
				+ " (NULL, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);

			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPass());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getGender());
			pstmt.setString(5, dto.getHobby());
			pstmt.setString(6, dto.getTel());
			pstmt.setString(7, dto.getAddr());
			
			pstmt.executeUpdate();
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException("insert failure"); //servlet Exception 을 걸어줘야 응답번호 에러 내용을 볼수 있다.
			//그렇지 않으면 java App 의 예외처리가 실행된다.
		} finally {
			this.close();
		}
		return true;
	}


	
	//아이디 중복확인
	public int duplchkMember(MemberDTO dto) throws ServletException {
		String query = "SELECT count(ID) FROM MEMBER WHERE ID=?";
		ResultSet rv;
		int result = 0;
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getId());
			
			rv = pstmt.executeQuery();
			
			while(rv.next()){
				result = rv.getInt(1);
			}
			
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException("select failure"); 
			//그렇지 않으면 java App 의 예외처리가 실행된다.
		} finally {
			this.close();
		}
		return result;
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
