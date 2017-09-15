/* 
 * =============================
 * 프로그램 설명 : 내옷장DAO   
 * プログラムの説明: 私のたんすDAO
 * 작성자 : 오내훈
 * 作成者 : オ・ネフン
 * 최초 작성일자 :　　2017-07-11
 * 最初の作成日付　:　 2017-07-11
 * 최종 수정일 : 
 * 最終の修正日付　:
 * 수정 내용 : 	
 * 修正の内容 :
 * =============================
 * */


package com.bs.mydressroom.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bs.util.DBUtil;
import com.bs.view.MainFrame;
import com.bs.vo.MyDressRoomVO;

public class MyDressRoomDAO {
	
	//내 옷장의 정보를 조회하는 메서드 //私のたんすの情報を照会するメソッド
		public ArrayList<MyDressRoomVO> getMemberList(String id) throws Exception {
			//id 조건 설정 //idの条件を設定
			ArrayList<MyDressRoomVO> list = new ArrayList<MyDressRoomVO>();
			String dml = "SELECT M.GENDER "
					+ " , W.BALANCE"
					+ "	      FROM MEMBER M, WALLET W"
					+ "		  WHERE M.ID = W.W_ID "
					+ "		  AND M.ID =? ";
					
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			MyDressRoomVO retval = null;
			
			try {
				con = DBUtil.getConnection();
				
				pstmt = con.prepareStatement(dml);
				//조회 조건 //照会の条件
				pstmt.setString(1, MainFrame.id);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					retval = new MyDressRoomVO(rs.getInt(1), rs.getInt(2));
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
	
	
	//내 옷장의 정보를 조회하는 메서드 //私のたんすの情報を照会するメソッド
	public ArrayList<MyDressRoomVO> getClosetList(String id) throws Exception {
		//id 조건 설정 //idの条件を設定
		ArrayList<MyDressRoomVO> list = new ArrayList<MyDressRoomVO>();
		String dml = "SELECT C.ID " 
			+"	, C.PRODUCT_CODE      "
			+"	, C.PRODUCT_NAME      "
			+"	, C.BUYDATE           "
			+"	, W.BALANCE           "
			+"	FROM CLOSET C         "
			+"	,WALLET W             "
			+"	WHERE W.W_ID = C.ID   "
			+"	AND W.W_ID = ?";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MyDressRoomVO retval = null;
		
		try {
			con = DBUtil.getConnection();
			
			pstmt = con.prepareStatement(dml);
			//조회 조건 //照会の条件
			pstmt.setString(1, MainFrame.id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				retval = new MyDressRoomVO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
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
	
	//상품 이미지를 불러오는 메서드 //商品イメージをもたらすメソッド
	public ArrayList<MyDressRoomVO> getImage(String imageName) throws Exception {
		//no, name 조건 설정 //no, name　条件を設定
		ArrayList<MyDressRoomVO> list = new ArrayList<MyDressRoomVO>();
		String dml = " SELECT " 
				+ " IMAGE_NAME "
				+ " ,IMAGE_PATH "
				+ " ,XAXIS"
				+ " ,YAXIS"
				+ " ,'' as buydate"
				+" FROM IMAGE_TABLE"
				+ " WHERE IMAGE_NAME = 'none'"
				+ " UNION ALL"	//쿼리 아래로 붙이기//クエリーの下に貼る
				+ " SELECT "
				+ "  A.IMAGE_NAME"
				+ " ,A.IMAGE_PATH"
				+ " ,A.XAXIS"
				+ " ,A.YAXIS"
				+ " ,B.BUYDATE"
				+ " FROM "
				+ " IMAGE_TABLE A"
				+ ",CLOSET B"
				+ " WHERE A.IMAGE_NAME = B.PRODUCT_CODE"
				+ " AND A.IMAGE_NAME = ?"
				;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MyDressRoomVO retval = null;
		
		try {
			con = DBUtil.getConnection();
			
			pstmt = con.prepareStatement(dml);
			//조회 조건 //照会の条件
			pstmt.setString(1, imageName);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				retval = new MyDressRoomVO(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5));
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
	
	//충전하기 기능//充電する機能
	public int setCashCharge(String id, int charge) throws Exception {
		int i =  0;
		
		//기존 요청 값에 더해준다.//従来の要請の価格に加えてくれる。
		String dml = "UPDATE WALLET SET REQUESTCASH = REQUESTCASH + ?, CONFIRM = 'N' "
						+ " WHERE W_ID = ? ";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(dml);
			pstmt.setInt(1, charge);
			pstmt.setString(2, id);
			
			 i = pstmt.executeUpdate();
			
		} catch (SQLException e) { //예외처리//例外処理
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
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
		return i;
	}
}
