package store;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import deliveryCompany.DBUtil;
import deliveryCompany.OrderVO;

public class StoreDAO {

	public StoreDAO(){}
	// 테이블에 매장정보 등록 //　テーブルに店情報を登録
	public void getStoreRegiste(StoreVO svo) throws Exception { 

		String dml = "insert into dstore" + " (snum, sname, saddr, stel, spwd)"
				+ "values " + " (?, ?, ?, ?,?)";
		Connection con = null; 
		PreparedStatement pstmt = null;
		StoreVO retval = null;

		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(dml);
			retval = getStoreName(svo.getName());
			if (retval == null) {

				pstmt.setInt(1, svo.getNo());
				pstmt.setString(2, svo.getName());
				pstmt.setString(3, svo.getAddress());
				pstmt.setString(4, svo.getTel());
				pstmt.setString(5, svo.getPass());
				pstmt.executeUpdate();
				retval = getStoreName(svo.getName());				
				Object[] options = {"確認"};
				JOptionPane.showOptionDialog(null, "登録が完了されました、店番号は "+retval.getNo()+" です。", "メッセ-ジ", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,null, options,"確認");

			} else {
				
				Object[] options = {"確認"};
				JOptionPane.showOptionDialog(null, "もう登録された店です ", "メッセ-ジ", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,null, options,"確認");
				
			}

		} catch (SQLException e) {
			System.out.println("getStoreRegiste1");
		} catch (Exception e) {
			System.out.println("getStoreRegiste2");
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println("getStoreRegiste3");
			}
		}

	}


	//매장테이블의 모든자료 불러오기 // 注文テーブル呼出
	public ArrayList<StoreVO> getStoretotal() { 
		ArrayList<StoreVO> list = new ArrayList<StoreVO>();
		String tml = "select * from dstore";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StoreVO stVo = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(tml);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				stVo = new StoreVO(rs.getInt("snum"), rs.getString("sname"),
						rs.getString("saddr"),rs.getString("stel"), rs.getString("spwd"));
				list.add(stVo);
			}
		} catch (SQLException se) {
			System.out.println("getStoretotal1");
		} catch (Exception e) {
			System.out.println("getStoretotal2");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
				System.out.println("getStoretotal3");
			}
		}
		return list;
	}

	public ArrayList<String> getStoreListColumn() { // 배달요청테이블에 보여질 필드명 //　要請テーブルのフィールド名
		ArrayList<String> columnName = new ArrayList<String>();
		String sql = "select onum, oaddr, menuCount, codename, smessage from dorder, ddeliver where dorder.code=ddeliver.code";
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		ResultSetMetaData rsmd = null;
		try {
			con = DBUtil.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();
			for (int i = 1; i <= cols; i++) {
				columnName.add(rsmd.getColumnName(i));
			}
		} catch (SQLException se) {
			System.out.println("getStoreListColumn1");
		} catch (Exception e) {
			System.out.println("getStoreListColumn2");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (SQLException es) {
				System.out.println("getStoreListColumn3");
			}

		}
		return columnName;
	}
	// 배달완료창에 나올 필드명 //　完了テーブルのフィールド名
	public ArrayList<String> getStoreDeliveryColumn() { 
		ArrayList<String> columnName = new ArrayList<String>();
		String sql = "select onum, delinum, oaddr, menuCount from dok ";
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		ResultSetMetaData rsmd = null;
		try {
			con = DBUtil.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();
			for (int i = 1; i <= cols; i++) {
				columnName.add(rsmd.getColumnName(i));
			}
		} catch (SQLException se) {
			System.out.println("getStoreListColumn1");
		} catch (Exception e) {
			System.out.println("getStoreListColumn2");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (SQLException es) {
				System.out.println("getStoreListColumn3");
			}

		}
		return columnName;
	}
	// 업체의 매장관리창에 나올 테이블 //　会社の売場管理テーブル
	public ArrayList<String> getStoreListColumn2() { 
		ArrayList<String> columnName = new ArrayList<String>();
		String sql = "select * from dstore";
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		ResultSetMetaData rsmd = null;
		try {
			con = DBUtil.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();
			for (int i = 1; i <= cols; i++) {
				columnName.add(rsmd.getColumnName(i));
			}
		} catch (SQLException se) {
			System.out.println("getStoreListColumn1");
		} catch (Exception e) {
			System.out.println("getStoreListColumn2");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (SQLException es) {
				System.out.println("getStoreListColumn3");
			}

		}
		return columnName;
	}
	// 입력받은 매장번호로 배달완료된 테이블 검색 //　入力した店番号で完了テーブルを検索
	public ArrayList<OrderVO> getStoreDeliveryComplete(int snum) { 
		ArrayList<OrderVO> list = new ArrayList<OrderVO>();
		String sql = "select onum, delinum, oaddr, menuCount from dok where dok.snum and snum = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderVO oVo = null;
		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, snum);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				oVo = new OrderVO(rs.getInt(1), rs.getInt(2),rs.getString(3),rs.getInt(4));
				list.add(oVo);
			}
		} catch (SQLException se) {

		} catch (Exception e) {

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
	// 로그인시 매장번호, 비밀번호 체크 //　ログインの時店番号、パスワードをチェック
	public StoreVO getStoreCheck(int num, String spwd) throws Exception { 
		String dml = "select * from dstore where snum=? and spwd=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StoreVO retval = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(dml);
			pstmt.setInt(1, num);
			pstmt.setString(2, spwd);
			rs = pstmt.executeQuery();
			if (rs.next()) {

				retval = new StoreVO(rs.getInt(1), rs.getString(2),
						rs.getString(5), rs.getString(3), rs.getString(4));

			}
		} catch (SQLException se) {
			System.out.println("getStoreCheck1");
		} catch (Exception e) {
			System.out.println("getStoreCheck2");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
				System.out.println("getStoreCheck3");
			}
		}
		return retval;
	}
	// 입력받은 매장번호로 매장검색 //　入力した店番号で検索
	public StoreVO getStoreNum(int num) throws Exception { 
		String dml = "select * from dstore where snum = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StoreVO retval = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(dml);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				retval = new StoreVO(rs.getInt(1), rs.getString(2),
						rs.getString(5), rs.getString(3), rs.getString(4));
			}
		} catch (SQLException se) {
			System.out.println("getStoreNum1");
		} catch (Exception e) {
			System.out.println("getStoreNum2");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
				System.out.println("getStoreNum3");
			}
		}
		return retval;
	}
	// 매장이름으로 매장검색 //　店の名で検索
	public StoreVO getStoreName(String sname) throws Exception { 
		String dml = "select * from dstore where sname = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StoreVO retval = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(dml);
			pstmt.setString(1, sname);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				retval = new StoreVO(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getString(5));
			}
		} catch (SQLException se) {
			System.out.println("getStoreName1");
		} catch (Exception e) {
			System.out.println("getStoreName2");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
				System.out.println("getStoreName3");
			}
		}
		return retval;
	}

	//매장번호를 받아서 삭제　//店番号でdata削除
	public void storeDelete(int no) throws Exception { 
		String dml = "delete from dstore where snum=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(dml);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		} catch (SQLException se) {
			System.out.println("storeDelete1");
		} catch (Exception e) {
			System.out.println("storeDelete2");
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
				System.out.println("storeDelete3");
			}
		}
	}

	//번호를 받아 그 번호의 매장의 정보를 가져오기
	public StoreVO getStoreNo(int snum) throws Exception {

		String dml = "select * from dstore where snum = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StoreVO retval = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(dml);
			pstmt.setInt(1, snum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				retval = new StoreVO(rs.getInt(1), rs.getString(2),
						rs.getString(3));
			}
		} catch (SQLException se) {
			System.out.println("Storenum");
			System.out.println(se);
		} catch (Exception e) {
			System.out.println("Storenum");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
				System.out.println("Storenum");
			}
		}
		return retval;

	}
	// 주문 테이블에 저장 //　注文テーブルに貯藏
	public ArrayList<OrderVO> getStoreOrder(int snum) { 
		ArrayList<OrderVO> list = new ArrayList<OrderVO>();
		String tml ="select onum, oaddr, menuCount, codename , smessage "
				+ "from dorder, ddeliver "
				+ "where dorder.code=ddeliver.code and snum = ? order by codename, onum";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderVO oVo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(tml);
			pstmt.setInt(1, snum);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				oVo = new OrderVO(rs.getString("smessage"),rs.getInt("onum"),rs.getString("oaddr"),
						rs.getInt("menuCount"),rs.getString("codename"));
				list.add(oVo);
			}
		} catch (SQLException se) {

		} catch (Exception e) {

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

	// 업체에서 보낸 메시지가 온것을 실시간으로 확인하기 위한 메소드
	public int getSmessageCheck(int snum){
		String sql = "select count(smessage) from dorder where snum ="+snum;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs   = null;
		int msgCheck = 0;
		try{
			con = DBUtil.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			msgCheck = rs.getInt(1);
		}catch(SQLException sqle){
			System.out.println("SmessageCheck 메소드 SQL catch");
			sqle.printStackTrace();
		}catch(Exception e){
			System.out.println("SmessageCheck 메소드 catch");
			e.printStackTrace();
		}
		finally{
			try{
				if(rs != null)	rs.close();
				if(stmt != null) stmt.close();
				if(con != null)	con.close();
			}catch(SQLException e){

			}
		}
		return msgCheck;
	}
	// 비밀번호와 전화번호로 검색 //　パスワードと電話番号で検索
	public StoreVO getStoreCheck1(String pass, String tel) throws Exception { 
		String dml = "select * from dstore where spwd=? and stel=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StoreVO retval = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(dml);
			pstmt.setString(1, pass);
			pstmt.setString(2, tel);
			rs = pstmt.executeQuery();
			if (rs.next()) {

				retval = new StoreVO(rs.getInt(1), rs.getString(2),	rs.getString(3), rs.getString(4),rs.getString(5));

			}
		} catch (SQLException se) {
			System.out.println("getStoreCheck1");
		} catch (Exception e) {
			System.out.println("getStoreCheck2");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
				System.out.println("getStoreCheck3");
			}
		}
		return retval;

	}
	// 매장번호와 전화번호로 검색 //　店番号と電話番号で検索
	public StoreVO getStoreCheck2(String no, String tel) throws Exception { 
		String dml = "select * from dstore where snum=? and stel=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StoreVO retval = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(dml);
			pstmt.setString(1, no);
			pstmt.setString(2, tel);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				retval = new StoreVO(rs.getInt(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getString(4), 
						rs.getString(5));
			}
		} catch (SQLException se) {
			System.out.println("getStoreCheck1");
		} catch (Exception e) {
			System.out.println("getStoreCheck2");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
				System.out.println("getStoreCheck3");
			}
		}
		return retval;

	}
	public void getMessageDelete(int num) throws Exception {// 확인버튼 누를시 메세지 삭제 // 確認ボタンを押す時メッセージを削除
		String dml = "update dorder set smessage = null where onum = ?";
		Connection con = null; 
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(dml);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("getStoreRegiste1");
		} catch (Exception e) {
			System.out.println("getStoreRegiste2");
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println("getStoreRegiste3");
			}
		}
	}

}
