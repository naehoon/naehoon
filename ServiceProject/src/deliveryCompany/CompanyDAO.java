package deliveryCompany;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import serviceMen.ServiceMenVO;
import store.StoreVO;


public class CompanyDAO {
	//주문테이블에 자료 등록　//　注文テーブルに資料登録
	public void getOrderRegiste(int snum, String oaddr , int menuCount, int code) throws Exception {//데이터베이스에 목록을 넣는 메소드//DBにリストを入れるmethod
		String dml="insert into dorder "
				+ " (oaddr, menuCount, snum, code)"
				+ "values "
				+ " (?, ?, ?, ?)";
		Connection con=null;
		PreparedStatement pstmt=null;


		try{//디비에서 목록을 가져옴　//　DBからリストを呼び出す
			con=DBUtil.getConnection();//디비 연동　//DB連動
			pstmt=con.prepareStatement(dml);
			pstmt.setString(1, oaddr);	// 매개변수로 주문주소　//パラメーターで注文住所
			pstmt.setInt(2, menuCount);	// 매개변수로 메시지　//パラメーターでメッセージ
			pstmt.setInt(3, snum);	// 매개변수로 매장번호 넣으면되요　//パラメーターで店番号
			pstmt.setInt(4, code);

			pstmt.executeUpdate();


		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("CompanyDAO-getOrderRegiste1");
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("CompanyDAO-getOrderRegiste2");
		} finally {
			try {
				if (pstmt!=null) pstmt.close();
				if (con!=null) pstmt.close();
			} catch(SQLException e) {
				e.printStackTrace();
				System.out.println("CompanyDAO-getOrderRegiste3");
			}
		}
	}
	//주문현황리스트에 목록을 보여주는 메소드　//注文現況リストにリストを見せるmethod
	public ArrayList<OrderVO> getOrdertotal() { 
		ArrayList<OrderVO> list=new ArrayList<OrderVO>();
		String tml="select onum, sname, saddr, oaddr, menuCount,  codename"
				+ " from dstore s, dorder o, ddeliver d"
				+ " where s.snum = o.snum and o.code = d.code";

		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		OrderVO oVo=null;
		try {
			con=DBUtil.getConnection();
			pstmt=con.prepareStatement(tml);
			rs=pstmt.executeQuery();

			while(rs.next()) {
				oVo=new OrderVO(rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getInt(5),
						rs.getString(6));
				list.add(oVo);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("CompanyDAO-getOrdertotal1");
			list.removeAll(list);
			list.clear();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("CompanyDAO-getOrdertotal2");
			list.removeAll(list);
			list.clear();
		} finally{
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null)con.close();
			} catch(SQLException e) {
				e.printStackTrace();
				System.out.println("CompanyDAO-getOrdertotal3");
			}
		}
		return list;
	}
	//배달완료테이블 모든 자료 불러오기//出前完了テーブルのすべての資料を呼び出す
	public ArrayList<CompleteDeliveryVO> getCompleteDeliverytotal() {
		ArrayList<CompleteDeliveryVO> list=new ArrayList<CompleteDeliveryVO>();
		String tml="select * from dok";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		CompleteDeliveryVO oVo=null;
		try {
			con=DBUtil.getConnection();
			pstmt=con.prepareStatement(tml);
			rs=pstmt.executeQuery();

			while(rs.next()) {
				oVo=new CompleteDeliveryVO(rs.getInt(1),
						rs.getInt(2),
						rs.getString(3),
						rs.getInt(4),
						rs.getString(5),
						rs.getInt(6)
						);
				list.add(oVo);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("CompanyDAO-getCompleteDeliverytotal1");
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("CompanyDAO-getCompleteDeliverytotal2");
		} finally{
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null)con.close();
			} catch(SQLException e) {
				e.printStackTrace();
				System.out.println("CompanyDAO-getCompleteDeliverytotal3");
			}
		}
		return list;
	}

	//이름으로 배달완료테이블 자료 불러오기//名前で出前完了テーブルの資料を呼び出す
	public ArrayList<CompleteDeliveryVO> getCompleteDeliveryName(String sname) {
		ArrayList<CompleteDeliveryVO> list=new ArrayList<CompleteDeliveryVO>();
		String tml="select * from dok where sname=?";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		CompleteDeliveryVO oVo=null;
		try {
			con=DBUtil.getConnection();
			pstmt=con.prepareStatement(tml);
			pstmt.setString(1, sname);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				oVo=new CompleteDeliveryVO(rs.getInt(1),
						rs.getInt(2),
						rs.getString(3),
						rs.getInt(4),
						rs.getString(5),
						rs.getInt(6));
				list.add(oVo);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("CompanyDAO-getCompleteDeliveryName1");
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("CompanyDAO-getCompleteDeliveryName2");
		} finally{
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null)con.close();
			} catch(SQLException e) {
				e.printStackTrace();
				System.out.println("CompanyDAO-getCompleteDeliveryName3");
			}
		}
		return list;
	}

	//배달원 테이블의 모든 자료를 가져오기//出前の人テーブルのすべての資料を持ってくる
	public ArrayList<ServiceMenVO> getServiceMentotal() {
		ArrayList<ServiceMenVO> list=new ArrayList<ServiceMenVO>();
		String tml="select * from dman";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ServiceMenVO smVo=null;
		try {
			con=DBUtil.getConnection();
			pstmt=con.prepareStatement(tml);
			rs=pstmt.executeQuery();

			while(rs.next()) {
				smVo=new ServiceMenVO(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getInt(4));
				list.add(smVo);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("CompanyDAO-getServiceMentotal1");
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("CompanyDAO-getServiceMentotal2");
		} finally{
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null)con.close();
			} catch(SQLException e) {	
				e.printStackTrace();
				System.out.println("CompanyDAO-getServiceMentotal3");
			}
		}
		return list;
	}
	//주문테이블의 자료를 주문번호로 오름차순 정렬하기//注文テーブルの資料を注文番号で整列
	public ArrayList<CompleteDeliveryVO> OrderListOnum() {
		ArrayList<CompleteDeliveryVO> list=new ArrayList<CompleteDeliveryVO>();
		String tml="select * from dok order by onum";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		CompleteDeliveryVO oVo=null;
		try {
			con=DBUtil.getConnection();
			pstmt=con.prepareStatement(tml);
			//pstmt.setInt(1, onum);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				oVo=new CompleteDeliveryVO(rs.getInt(1),
						rs.getInt(2),
						rs.getString(3),
						rs.getInt(4),
						rs.getString(5),
						rs.getInt(6));
				list.add(oVo);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("CompanyDAO-OrderListOnum1");
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("CompanyDAO-OrderListOnum2");
		} finally{
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null)con.close();
			} catch(SQLException se) {			
				System.out.println("CompanyDAO-OrderListOnum3");
			}
		}
		return list;
	}

	//주문테이블의 자료들을 배달원번호의 오름차순 정렬//注文テーブルの資料を出前の人番号で整列
	public ArrayList<CompleteDeliveryVO> OrderListSortDelinum() {
		ArrayList<CompleteDeliveryVO> list=new ArrayList<CompleteDeliveryVO>();
		String tml="select * from dok order by delinum";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		CompleteDeliveryVO oVo=null;
		try {
			con=DBUtil.getConnection();
			pstmt=con.prepareStatement(tml);
			//pstmt.setInt(1, delinum);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				oVo=new CompleteDeliveryVO(rs.getInt(1),
						rs.getInt(2),
						rs.getString(3),
						rs.getInt(4),
						rs.getString(5),
						rs.getInt(6));
				list.add(oVo);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("CompanyDAO-OrderListSortDelinum1");
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("CompanyDAO-OrderListSortDelinum2");
		} finally{
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null)con.close();
			} catch(SQLException e) {
				e.printStackTrace();
				System.out.println("CompanyDAO-OrderListSortDelinum3");
			}
		}
		return list;
	}

	//주문테이블의 자료를 이름으로 오름차순 정렬//注文テーブルの資料を名前で整列
	public ArrayList<CompleteDeliveryVO> OrderListSortSname() {
		ArrayList<CompleteDeliveryVO> list=new ArrayList<CompleteDeliveryVO>();
		String tml="select * from dok order by sname";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		CompleteDeliveryVO oVo=null;
		try {
			con=DBUtil.getConnection();
			pstmt=con.prepareStatement(tml);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				oVo=new CompleteDeliveryVO(rs.getInt(1),
						rs.getInt(2),
						rs.getString(3),
						rs.getInt(4),
						rs.getString(5),
						rs.getInt(6));
				list.add(oVo);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("CompanyDAO-OrderListSortSname1");
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("CompanyDAO-OrderListSortSname2");
		} finally{
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null)con.close();
			} catch(SQLException e) {
				e.printStackTrace();
				System.out.println("CompanyDAO-OrderListSortSname3");
			}
		}
		return list;
	}

	//매장테이블의 자료를 수정//店テーブルの整列
	public void storeUpdate(StoreVO num) {
		String tml = "update dstore set sname=?,saddr=?,stel=?,spwd=? where snum=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(tml);
			pstmt.setString(1, num.getName());
			pstmt.setString(2, num.getAddress());
			pstmt.setString(3, num.getTel());
			pstmt.setString(4, num.getPass());
			pstmt.setInt(5, num.getNo());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("CompanyDAO-storeUpdate1");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("CompanyDAO-storeUpdate2");
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("CompanyDAO-storeUpdate3");
			}
		}
	}

	//배달원테이블의 자료를 배달번호를 받고 삭제//配達員テーブルの資料を配達番号をもらって削除
	public void serviceMenDelete(int no) throws Exception {
		String dml = "delete from dman where delinum=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(dml);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("CompanyDAO-serviceMenDelete1");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("CompanyDAO-serviceMenDelete2");
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("CompanyDAO-serviceMenDelete3");
			}
		}
	}

	//배달테이블의 자료를 수정//出前テーブルの資料を修正
	public void orderListUpdate(String message, int onum) {
		String tml = "update dorder set smessage=? where onum=?";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(tml);
			pstmt.setString(1, message);
			pstmt.setInt(2, onum);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("CompanyDAO-orderListUpdate1");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("CompanyDAO-orderListUpdate2");
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("CompanyDAO-orderListUpdate3");
			}
		}
	}

	//데일리 테이블에 자료를 넣기//dailyテーブルに資料を入れる
	public void getDailyRegiste() throws Exception {
		String dml="insert into ddaily "
				+ " select curdate(), delinum, snum, menuCount"
				+ " from dok";
		Connection con=null;
		PreparedStatement pstmt=null;


		try{
			con=DBUtil.getConnection();
			pstmt=con.prepareStatement(dml);
			pstmt.executeUpdate();


		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("CompanyDAO-getOrderRegiste1");
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("CompanyDAO-getOrderRegiste2");
		} finally {
			try {
				if (pstmt!=null) pstmt.close();
				if (con!=null) pstmt.close();
			} catch(SQLException e) {
				e.printStackTrace();
				System.out.println("CompanyDAO-getOrderRegiste3");
			}
		}
	}

	public String getSumCount(String date, String sname, int delinum){
		String sql;
		Connection con = null;
		Statement stmt = null;
		ResultSet 	rs = null;
		if(date == null)
			return null;
		else if(sname != null && delinum == 0){
			sql = "select sum(menuCount) from ddaily d, dstore s"
					+ " where d.snum = s.snum and day ='"+ date +"' and sname ='"+ sname +"'";
		}
		else if(sname == null && delinum >= 1){
			sql = "select sum(menuCount) from ddaily "
					+ "where day ='"+ date +"' and delinum ="+delinum;
		}
		else{
			sql = "select sum(menuCount) from ddaily "
					+ "where day ='"+ date +"'";
		}

		try{
			con=DBUtil.getConnection();
			stmt=con.createStatement();
			rs = stmt.executeQuery(sql);

			if(rs.next()){
				return rs.getString(1);
			}
		}catch(SQLException sqle){
			System.out.println("CompanyDAO-getSumCount1");
			sqle.printStackTrace();
		}catch(Exception e){
			System.out.println("CompanyDAO-getSumCount2");
			e.printStackTrace();
		}
		return null;
	}
	//주문현황리스트에 목록을 보여주는 메소드//注文現況リストにリストを見せるmethod
	public ArrayList<DailyVO> getDailytotal() { 
		ArrayList<DailyVO> list=new ArrayList<DailyVO>();
		String tml="select day, delinum, sname, menuCount"
				+ " from ddaily"
				+ " left join dstore on ddaily.snum = dstore.snum";

		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		DailyVO daVo=null;
		try {
			con=DBUtil.getConnection();
			pstmt=con.prepareStatement(tml);
			rs=pstmt.executeQuery();

			while(rs.next()) {
				daVo=new DailyVO(rs.getString(1),
						rs.getInt(2),
						rs.getString(3),
						rs.getInt(4));
				list.add(daVo);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("CompanyDAO-getOrdertotal1");
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("CompanyDAO-getOrdertotal2");
		} finally{
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null)con.close();
			} catch(SQLException e) {
				e.printStackTrace();
				System.out.println("CompanyDAO-getOrdertotal3");
			}
		}
		return list;
	}

	//데일리테이블에서 모든 자료를 리셋해서 가져옴//Dailyテーブルからすべての資料を初期化して持ってくる
	public ArrayList<DailyVO> dailySearchAll(String date, String sname, int delinum) {
		ArrayList<DailyVO> list=new ArrayList<DailyVO>();
		String tml="select day, delinum, sname, menuCount"
				+ " from ddaily"
				+ " left join dstore on ddaily.snum = dstore.snum"
				+ " and day=?"
				+ " and delinum=?"
				+ " and sname=?";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		DailyVO dVo=null;
		try {
			con=DBUtil.getConnection();
			pstmt=con.prepareStatement(tml);
			pstmt.setString(1, date);
			pstmt.setInt(2, delinum);
			pstmt.setString(3, sname);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				dVo=new DailyVO(rs.getString(1),
						rs.getInt(2),
						rs.getString(3),
						rs.getInt(4));
				list.add(dVo);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("CompanyDAO-kensakuSearchAll1");
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("CompanyDAO-kensakuSearchAll2");
		} finally{
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null)con.close();
			} catch(SQLException e) {
				e.printStackTrace();
				System.out.println("CompanyDAO-kensakuSearchAll3");
			}
		}
		return list;
	}

	//데일리테이블에서 날짜와 배달원번호에 관계된 자료를 출력//Dailyテーブルからひづけと出前の人番号の資料を出力
	public ArrayList<DailyVO> dailySearchDelinum(String date, int delinum) {
		ArrayList<DailyVO> list=new ArrayList<DailyVO>();
		String tml="select day, delinum, menuCount"
				+ " from ddaily"
				+ " where day=?"
				+ " and delinum=?";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		DailyVO dVo=null;
		try {
			con=DBUtil.getConnection();
			pstmt=con.prepareStatement(tml);
			pstmt.setString(1, date);
			pstmt.setInt(2, delinum);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				dVo=new DailyVO(rs.getString(1),
						rs.getInt(2),
						rs.getInt(3));
				list.add(dVo);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("CompanyDAO-kensakuSearchDelinum1");
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("CompanyDAO-kensakuSearchDelinum2");
		} finally{
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null)con.close();
			} catch(SQLException e) {			
				e.printStackTrace();
				System.out.println("CompanyDAO-kensakuSearchDelinum3");
			}
		}
		return list;
	}

	//이름과 날짜로 데일리테이블에서 자료를 불러오기//名前と日付でDailyテーブルから資料を呼び出す
	public ArrayList<DailyVO> dailySearchName(String date, String sname) {
		ArrayList<DailyVO> list=new ArrayList<DailyVO>();
		String tml="select day, delinum, sname, menuCount"
				+ " from ddaily d, dstore s"
				+ " where d.snum = s.snum"
				+ " and day=?"
				+ " and sname=?";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		DailyVO dVo=null;
		try {
			con=DBUtil.getConnection();
			pstmt=con.prepareStatement(tml);
			pstmt.setString(1, date);
			pstmt.setString(2,  sname);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				dVo=new DailyVO(rs.getString(1),
						rs.getInt(2),
						rs.getString(3),
						rs.getInt(4));
				list.add(dVo);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("CompanyDAO-kensakuSearchName1");
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("CompanyDAO-kensakuSearchName2");
		} finally{
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null)con.close();
			} catch(SQLException e) {
				e.printStackTrace();
				System.out.println("CompanyDAO-kensakuSearchName3");
			}
		}
		return list;
	}
	//날짜로 데일리테이블에서 자료를 불러오기//日付でDailyテーブルから資料を呼び出す
	public ArrayList<DailyVO> dailySearchDate(String date) {
		ArrayList<DailyVO> list=new ArrayList<DailyVO>();
		String tml;
		if(date.length()==10){
			tml="select day, delinum, sname, menuCount"
					+ " from ddaily, dstore"
					+ " where day = '"+date+"'";
		} else {
			tml="select day, delinum, sname, menuCount"
					+ " from ddaily, dstore"
					+ " where day like '"+date+"%'";
		}
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		DailyVO dVo=null;
		try {
			con=DBUtil.getConnection();
			stmt=con.createStatement();
			rs=stmt.executeQuery(tml);
			while(rs.next()) {
				dVo=new DailyVO(rs.getString(1),
						rs.getInt(2),
						rs.getString(3),
						rs.getInt(4));
				list.add(dVo);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("CompanyDAO-kensakuSearchName1");
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("CompanyDAO-kensakuSearchName2");
		} finally{
			try{
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				if(con!=null)con.close();
			} catch(SQLException e) {
				e.printStackTrace();
				System.out.println("CompanyDAO-kensakuSearchName3");
			}
		}
		return list;
	}

	//배달원테이블에서 자료수정//出前の人テーブルで資料修正
	public void serviceMenUpdate(ServiceMenVO a) {
		ArrayList<ServiceMenVO> list=new ArrayList<ServiceMenVO>();
		String tml = "update dman set deliname=?,"
				+ " delitel=? where delinum=?";
		list.add(a);

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(tml);
			pstmt.setInt(3, a.getNo());
			pstmt.setString(1, a.getName());
			pstmt.setString(2, a.getTel());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("CompanyDAO-serviceMenUpdate1");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("CompanyDAO-serviceMenUpdate2");
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("CompanyDAO-serviceMenUpdate3");
			}
		}
	}

}


