package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import utl.DBUtil;
import vo.UserVO;
public class UserDAO {
	
	// 메뉴 출력 및 선택한 메뉴 값 리턴 메서드
	public int displayMenu() {

		System.out.println("메뉴를 선택하세요 >> 1.입력, 2.수정 , 3,검색 , 4.삭제, 5.종료");
		int menu = 0;

		do {
			try {
				Scanner s = new Scanner(System.in);
				menu = Integer.parseInt(s.nextLine());

				if (menu >= 1 && menu <= 5) {
					break;
				} else {
					throw new Exception();
				}
			} catch (Exception e) {
				System.out.println("잘못 입력. 다시 입력!! ->> 메뉴 선택(1~5) : ");
				System.out.println();
			}
		} while (true);
		return menu;
	}
	
	//사용자 입력 메서드 
	public UserVO insertUser(){
		
		UserVO uvo = null;
		String dml = "INSERT INTO USERINFO VALUES(?,?)";
		Connection con = null;
		PreparedStatement pstmt = null;

		do {
			try {
				Scanner s = new Scanner(System.in);
				uvo = new UserVO();
				System.out.println("이름을 입력하세요(문자열)");
				uvo.setName(s.nextLine());
				System.out.println("나이를 입력하세요(숫자)");
				uvo.setAge(Integer.parseInt(s.nextLine()));
				break;
			} catch (Exception e) {
				System.out.println("입력 오류. 다시 입력해 주세요.");
				break;
			}
		} while (true);
		
		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(dml);

			pstmt.setString(1, uvo.getName());
			pstmt.setInt(2, uvo.getAge());

			int i = pstmt.executeUpdate();
			
			if(i>=1){
				System.out.println("입력성공!!!");
			}else{
				System.out.println("입력실패!!!");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return uvo;
	}
	
	//사용자 수정 메서드 
	public void updateUser(){
		
		UserVO uvo = null;		
		String dml = "UPDATE USERINFO SET AGE = 35 WHERE NAME = ?";
		Connection con = null;
		PreparedStatement pstmt = null;

		do {
			try {
				Scanner s = new Scanner(System.in);
				uvo = new UserVO();
				System.out.println("정보를 수정하실 분의 이름을 입력하세요(문자열)");
				uvo.setName(s.nextLine());
//				System.out.println("수정하실 나이를 입력하세요(숫자)");
//				uvo.setAge(Integer.parseInt(s.nextLine()));
				break;
			} catch (Exception e) {
				System.out.println("입력 오류. 다시 입력해 주세요.");
				break;
			}
			
		} while (true);
		
		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(dml);

//			pstmt.setInt(1, uvo.getAge());
			pstmt.setString(1, uvo.getName());

			int i = pstmt.executeUpdate();
			
			if(i>=1){
				System.out.println("수정성공!!!");
			}else{
				System.out.println("수정실패!!!(존재하지 않는 이름입니다. 이름을 다시 확인해주세요)");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	//사용자 검색 메서드
	public ArrayList<UserVO> searchName(){
		ArrayList<UserVO> list = new ArrayList<UserVO>();
		String dml = "SELECT * FROM USERINFO";

		Statement stmt = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserVO retval = null;
		try {

			con = DBUtil.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(dml);
			
			while (rs.next()) {
				retval = new UserVO(rs.getString(1), rs.getInt(2));
				list.add(retval);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return list;
	}
	
	//사용자 삭제 메서드
	public void deleteUser(){
		
		UserVO uvo = null;		
		String dml = "DELETE FROM USERINFO WHERE NAME = ?";
		Connection con = null;
		PreparedStatement pstmt = null;

		do {
			try {
				Scanner s = new Scanner(System.in);
				uvo = new UserVO();
				System.out.println("삭제하실 분의 이름을 입력하세요(문자열)");
				uvo.setName(s.nextLine());
				break;
			} catch (Exception e) {
				System.out.println("입력 오류. 다시 입력해 주세요.");
				break;
			}
			
		} while (true);
		
		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(dml);

			pstmt.setString(1, uvo.getName());

			int i = pstmt.executeUpdate();
			
			if(i>=1){
				System.out.println("삭제성공!!!");
			}else{
				System.out.println("삭제실패!!! (존재하지 않는 이름입니다. 이름을 다시 확인해주세요)");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		
	}

}
