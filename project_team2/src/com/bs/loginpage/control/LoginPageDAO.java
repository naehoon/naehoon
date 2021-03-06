/* 
 * =============================
 * 프로그램 설명 : 로그인 페이지 DAO  
 * プログラムの説明: ログインページDAO
 * 작성자 : 오내훈
 * 作成者 :  オ・ネフン
 * 최초 작성일자 :   2017-07-16
 * 最初の作成日付 : 2017-07-16
 * 최종 수정일 : 
 * 最終の修正日付　:
 * 수정 내용 : 	
 * 修正の内容 :
 * =============================
 * */

package com.bs.loginpage.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bs.util.DBUtil;
import com.bs.vo.CartVO;
import com.bs.vo.ClothBasicVO;
import com.bs.vo.LoginPageVO;
import com.bs.vo.MemberVO;

public class LoginPageDAO {
	
	//등록된 상품리스트를 모두 가져온다.//登録された商品リストをすべて持って来てくる
	public ArrayList<ClothBasicVO> getProductInfo() throws Exception {
		ArrayList<ClothBasicVO> list = new ArrayList<ClothBasicVO>();
		String dml = "SELECT "
				+ "  product_code"
				+ ", product_name"
				+ ", size"
				+ ", price"
				+ ", brand"
				+ ", material"
				+ ", image_path "
				+ "FROM "
				+ "  cloth_basic"
				+ ", image_table "
				+ "WHERE"
				+ "  product_code = image_name "
				;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ClothBasicVO retval = null;
		
		try {
			con = DBUtil.getConnection();
			
			pstmt = con.prepareStatement(dml);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				retval = new ClothBasicVO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7));
				list.add(retval);
			}
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
			}
		}
		return list;
	}
	
	//사용자 정보를 조회한다.//使用者情報を照会する
	public ArrayList<MemberVO> getMemberList(String id) throws Exception {
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		String dml = "SELECT "
				+ "M.ID"
				+ ",M.PASSWORD"
				+ ",M.NAME"
				+ ",M.PHONE"
				+ ",M.ADMIN_FLAG"
				+ ",M.EMAIL"
				+ ",M.GENDER"
				+ ",W.BALANCE"
				+ " FROM "
				+ " MEMBER M"
				+ " ,WALLET W"
				+ " WHERE M.ID = W.W_ID"
				+ " AND M.ID = ?";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO retval = null;
		
		try {
			con = DBUtil.getConnection();
			
			pstmt = con.prepareStatement(dml);
			//조회 조건 
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				retval = new MemberVO(rs.getString(1)
												, rs.getString(2)
												, rs.getString(3)
												, rs.getString(4)
												, rs.getInt(5)
												, rs.getString(6)
												, rs.getInt(7)
												, rs.getInt(8));
				list.add(retval);
//				System.out.println(retval);
			}
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
			}
		}
		return list;
	}
	
	//선택한 물건을 카트에 담는다 //選択した物をカートに入れる
	public CartVO setCartInfo(CartVO cvo) throws Exception {
		
		String dml = "INSERT INTO CART values (?, ?, ?, ? ,? ,? ,?)";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		CartVO retval = null;
		
		try {
			con = DBUtil.getConnection();
			
			pstmt = con.prepareStatement(dml);
			pstmt.setString(1, cvo.getId());
			pstmt.setString(2, cvo.getProductCode());
			pstmt.setString(3, cvo.getProductName());
			pstmt.setString(4, cvo.getSize());
			pstmt.setString(5, cvo.getPrice());
			pstmt.setString(6, cvo.getBrand());
			pstmt.setString(7, cvo.getMaterial());
			
			int i = pstmt.executeUpdate();
			retval = new CartVO();
			retval.setStatus(i); 
			
		} catch (SQLException e) { //예외처리//例外処理
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return retval;
	}
	
	//옷정보를 조회한다.//服の情報を照会する
	public ArrayList<ClothBasicVO> getClothInfo(String productCode) throws Exception {
		
		ArrayList<ClothBasicVO> list = new ArrayList<ClothBasicVO>();
		
		String dml = "SELECT "
				+ "PRODUCT_CODE"
				+ ",PRODUCT_NAME"
				+ ",SIZE"
				+ ",BRAND"
				+ ",MATERIAL"
				+ ",BUYDATE"
				+ ",PRICE"
				+ " FROM "
				+ "CLOTH_BASIC "
				+ "WHERE PRODUCT_CODE =?";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ClothBasicVO retval = null;
		
		try {
			con = DBUtil.getConnection();
			
			pstmt = con.prepareStatement(dml);
			//조회 조건 //照会の条件
			pstmt.setString(1, productCode);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				retval = new ClothBasicVO(rs.getString(1)
												, rs.getString(2)
												, rs.getString(3)
												, rs.getString(4)
												, rs.getString(5)
												, rs.getString(6)
												, rs.getInt(7));
				list.add(retval);
//				System.out.println(retval);
			}
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
			}
		}
		return list;
	}
	
	

	//선택한 물건을 카트에 담는다 //選択した物をカートに入れる
	public int setCartInfo(String productCode, String id) throws Exception {
		int result = 0;
		
		String dml ="INSERT INTO CART" 
		+"(C_ID"
		+",C_PRODUCT_NAME"
		+",C_PRODUCT_CODE"
		 +",C_SIZE"
		 +",C_PRICE"
		 +",C_BRAND"
		 +",C_MATERIAL)"
		 +"SELECT"
		 +" ? as ID"
		 +",PRODUCT_NAME"
		 +",PRODUCT_CODE"
		 +",SIZE"
		 +",PRICE"
		 +",BRAND"
		 +",MATERIAL"
		 +" FROM CLOTH_BASIC"
		 +" WHERE PRODUCT_CODE = ?"
		 ;
		
//		INSERT real_table(id,title) SELECT id,title FROM temp_table WHERE flag = 'N'
		
		Connection con = null;
		PreparedStatement pstmt = null;
		CartVO retval = null;
		
		try {
			con = DBUtil.getConnection();
			System.out.println(dml);
			pstmt = con.prepareStatement(dml);
			
				pstmt.setString(1, id);
				pstmt.setString(2, productCode);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) { //예외처리//例外処理
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	

	
	//상품 구매//商品購買
	public int setClosetInfo(String id, String productCode ) throws Exception {
		int result = 0;
		
		String dml ="INSERT INTO CLOSET" 
		+"(ID"
		+",PRODUCT_CODE"
		+",PRODUCT_NAME"
		+",BUYDATE)"
		 +"SELECT"
		 +" ? as ID"
		 +",PRODUCT_CODE"
		 +",PRODUCT_NAME"
		 +",DATE_FORMAT(now(), '%Y-%m-%d')"
		 +" FROM CLOTH_BASIC"
		 +" WHERE PRODUCT_CODE =?"
		 ;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(dml);
			
			pstmt.setString(1, id);
			pstmt.setString(2, productCode);
			result = pstmt.executeUpdate();  //결과값 //結果値
			
		} catch (SQLException e) { //예외처리//例外処理
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return result;
	}
	
	
	//잔고 감소 //残高減少
	public int setBalanceMinus(String id, String productCode) throws Exception {
		int result = 0;
		
		String dml ="UPDATE WALLET "
				+ " SET BALANCE = BALANCE - (SELECT PRICE FROM CLOTH_BASIC WHERE PRODUCT_CODE=?) "
				+ " WHERE W_ID = ?"; 
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(dml);
			
			pstmt.setString(1, productCode);
			pstmt.setString(2, id);
			result = pstmt.executeUpdate();  //결과값 //結果値
			
		} catch (SQLException e) { //예외처리//例外処理
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	
	//잔고를 조회한다.//残高を照会する
		public ArrayList<LoginPageVO> getBalance(String id) throws Exception {
			
			ArrayList<LoginPageVO> list = new ArrayList<LoginPageVO>();
			
			String dml = "SELECT "
					+ " BALANCE"
					+ " FROM "
					+ " WALLET"
					+ " WHERE W_ID =?";
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			LoginPageVO retval = null;
			
			try {
				con = DBUtil.getConnection();
				
				pstmt = con.prepareStatement(dml);
				//조회 조건 //照会の条件
				pstmt.setString(1, id);
				
				rs = pstmt.executeQuery();
				while (rs.next()) {
					retval = new LoginPageVO(rs.getString(1));
					list.add(retval);
//					System.out.println(retval);
				}
			} catch (SQLException se) {
				System.out.println(se);
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				try {
					if (rs != null)
						rs.close();
					if (pstmt != null)
						pstmt.close();
					if (con != null)
						con.close();
				} catch (SQLException se) {
				}
			}
			return list;
		}
		

		//옷가격을 조회한다.//服の値段を照会する
			public ArrayList<LoginPageVO> getClothPrice(String productCode) throws Exception {
				
				ArrayList<LoginPageVO> list = new ArrayList<LoginPageVO>();
				
				String dml = "SELECT "
						+ " PRICE"
						+ " FROM "
						+ " CLOTH_BASIC"
						+ " WHERE PRODUCT_CODE =?";
				
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				LoginPageVO retval = null;
				
				try {
					con = DBUtil.getConnection();
					
					pstmt = con.prepareStatement(dml);
					//조회 조건 //照会の条件
					pstmt.setString(1, productCode);
					
					rs = pstmt.executeQuery();
					while (rs.next()) {
						retval = new LoginPageVO(rs.getInt(1));
						list.add(retval);
					}
				} catch (SQLException se) {
					System.out.println(se);
				} catch (Exception e) {
					System.out.println(e);
				} finally {
					try {
						if (rs != null)
							rs.close();
						if (pstmt != null)
							pstmt.close();
						if (con != null)
							con.close();
					} catch (SQLException se) {
					}
				}
				return list;
			}
	
	

}
