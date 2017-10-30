package test_20170706_02;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCEx01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/naehoon";
		Connection con = null;
//		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
//		String sql = "select * from employee";        // sql 쿼리
		String sql = "insert into employee values(null,?,?,?,?)";        // sql 쿼리

		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, "root","");
//			stmt = con.createStatement();
			
			pstmt = con.prepareStatement(sql);                          // prepareStatement에서 해당 sql을 미리 컴파일한다.
			System.out.println("Database Connection ok");
			pstmt.executeQuery(sql);           
			rs = pstmt.executeQuery();                                        // 쿼리를 실행하고 결과를 ResultSet 객체에 담는다.
			System.out.println("번호\t이름\t직책\t\t부서\t이메일");
			
			 // 결과를 한 행씩 돌아가면서 가져온다.
			while(rs.next()){                                                       
				String no 				= rs.getString("no");
				String name 			= rs.getString("name");
				String jobgrade 		= rs.getString("jobgrade");
				String department 	= rs.getString("department");
				String email 			= rs.getString("email");
				
				System.out.print(no+"\t");
				System.out.print(name+"\t");
				System.out.print(jobgrade+"\t");
				System.out.print(department+"\t");
				System.out.print(email+"\n");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try{
				if(con!=null	){
					con.close();
				}
				if(pstmt!=null){
					con.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}