package test_20170706_02;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCEx02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/naehoon";
		Connection con = null;
//		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "insert into employee values(null,?,?,?,?)"; // sql 쿼리
		String sql2 = "select * from employee"; // sql 쿼리
		
		String name = "Parks";
		String jobgrade 		= "Salary";
		String email 	= "psu@naver.com";
		int department = 30;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, "root","");
//			stmt = con.createStatement();
			
			pstmt = con.prepareStatement(sql);                          // prepareStatement에서 해당 sql을 미리 컴파일한다.
			pstmt.setString(1, name);
			pstmt.setString(2, jobgrade);
			pstmt.setInt(3, department);
			pstmt.setString(4, email);
			
			int rowCount = pstmt.executeUpdate();
			System.out.println(rowCount  + " 행이 입력되었습니다.");
			
			pstmt = con.prepareStatement(sql2);                          // prepareStatement에서 해당 sql을 미리 컴파일한다.
			
			pstmt.executeQuery(sql2);           
			rs = pstmt.executeQuery();                                        // 쿼리를 실행하고 결과를 ResultSet 객체에 담는다.
			System.out.println("번호\t이름\t직책\t\t부서\t이메일");
			
			 // 결과를 한 행씩 돌아가면서 가져온다.
			while(rs.next()){                                                       
				System.out.println(rs.getString(1) +"\t"+ rs.getString(2)  +"\t"+ rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5));
			}
			
			 // 결과를 한 행씩 돌아가면서 가져온다.
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
