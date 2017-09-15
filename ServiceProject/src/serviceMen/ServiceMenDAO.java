package serviceMen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import serviceMen.ServiceMenVO;
import store.StoreVO;
import deliveryCompany.CompleteDeliveryVO;
import deliveryCompany.DBUtil;
import deliveryCompany.OrderVO;

public class ServiceMenDAO {
	public ServiceMenDAO(){}
	//======****배달테이블****======
	//주문현황리스트에 목록을 보여주는 메소드。注文状況のリストにリストを表示メソッド。
	public ArrayList<OrderVO> getOrdertotal() { 
		ArrayList<OrderVO> list=new ArrayList<OrderVO>();
		String tml="select onum, sname, saddr, oaddr, menuCount,  codename"
				+ " from dstore s, dorder o, ddeliver d"
				+ " where s.snum = o.snum and o.code = d.code and o.code=0";

		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		OrderVO oVo=null;
		try {
			con=DBUtil.getConnection();
			pstmt=con.prepareStatement(tml);
			rs=pstmt.executeQuery();

			while(rs.next()) {
				oVo=new OrderVO(rs.getInt("onum"),
						rs.getString("sname"),
						rs.getString("saddr"),
						rs.getString("oaddr"),
						rs.getInt("menuCount"),
						rs.getString("codename"));
				list.add(oVo);
			}
		} catch(SQLException se) {
			System.out.println("CompanyDAO-getOrdertotal1");
			list.removeAll(list);
			list.clear();
			se.printStackTrace();
		} catch(Exception e) {
			System.out.println("CompanyDAO-getOrdertotal2");
			list.removeAll(list);
			list.clear();
		} finally{
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null)con.close();
			} catch(SQLException se) {
				System.out.println("CompanyDAO-getOrdertotal3");
			}
		}
		return list;
	}
	
	//승인 버튼 눌렀을 때。承認ボタンをクリックしたとき。
	//'배달중'만 표시。出前中のみ　表示。
	public ArrayList<OrderVO> getDelivering(String delitel) throws Exception{
		ArrayList<OrderVO> oovo=new ArrayList<OrderVO>();
		String dml="select o.onum, s.sname, s.saddr, o.oaddr, o.menuCount, d.codename "
				+ "from ddeliver d, dstore s, dorder o, dman m "
				+ "where m.onum=o.onum and o.code=d.code and o.snum=s.snum "
				+ "and o.code=1 and m.delitel=?";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		OrderVO retval=null;
		try{
			con=DBUtil.getConnection();
			pstmt=con.prepareStatement(dml);
			pstmt.setString(1, delitel);
			rs=pstmt.executeQuery();
			if(rs.next()){
				retval=new OrderVO(rs.getInt("o.onum"),
						rs.getString("s.sname"),
						rs.getString("s.saddr"),
						rs.getString("o.oaddr"),
						rs.getInt("o.menuCount"),
						rs.getString("d.codename"));
				oovo.add(retval);
			}	
		} catch(SQLException se) {
			System.out.println(se);
		} catch(Exception e) {
			System.out.println(e);
		} finally {
			try {
				if(rs!=null)rs.close();
				if (pstmt!=null) pstmt.close();
				if (con!=null) pstmt.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return oovo;

	}
	@SuppressWarnings("finally")

	//배달상태 변화 배달중으로。出前状態変化。出前中
	public ArrayList<OrderVO> getServiceOk(int no) throws Exception { 
		ArrayList<OrderVO> oovo=new ArrayList<OrderVO>();
		String sml="update dorder set code=1 where onum=? ";
		Connection con=null;
		PreparedStatement pstmt=null;
		OrderVO retval=null;

		try{
			con=DBUtil.getConnection();
			pstmt=con.prepareStatement(sml);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();		
			oovo.add(retval);
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt!=null) pstmt.close();
				if (con!=null) pstmt.close();
			} catch(final SQLException e) {
				System.out.println("serviceOK");
			}
			return oovo;
		}
	}
	
	//배달취소 버튼 눌렀을 때　//出前キャンセルボタンを押す時
	//배달상태 변화 배달전으로　//出前の前で配達狀態変更　
		@SuppressWarnings("finally")
		public ArrayList<OrderVO> getOrderCancel(int no) throws Exception { 
			ArrayList<OrderVO> oovo=new ArrayList<OrderVO>();
			String sml="update dorder set code=0 where onum=? ";
			Connection con=null;
			PreparedStatement pstmt=null;
			OrderVO retval=null;

			try{
				con=DBUtil.getConnection();
				pstmt=con.prepareStatement(sml); 
				pstmt.setInt(1, no);
				pstmt.executeUpdate();		
				oovo.add(retval);
			} catch(SQLException e) {
				e.printStackTrace();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (pstmt!=null) pstmt.close();
					if (con!=null) pstmt.close();
				} catch(final SQLException e) {
					System.out.println("serviceOK");
				}
				return oovo;
			}
		}
		//내가 배달완료한 것만 보기//自分の出前完了リスト
		public ArrayList<CompleteDeliveryVO> getCmptShow(int delinum) throws Exception{
			ArrayList<CompleteDeliveryVO> cdvo=new ArrayList<CompleteDeliveryVO>();
			String dml="select * from dok where delinum=?";
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			CompleteDeliveryVO retval=null;
			try{
				con=DBUtil.getConnection();
				pstmt=con.prepareStatement(dml);
				pstmt.setInt(1, delinum);
				rs=pstmt.executeQuery();
				while(rs.next()){
					retval=new CompleteDeliveryVO(rs.getInt("onum"),
							rs.getInt("delinum"),
							rs.getString("sname"),
							rs.getInt("snum"),
							rs.getString("oaddr"),
							rs.getInt("menuCount"));
					cdvo.add(retval);
				
				}	
			} catch(SQLException se) {
				System.out.println(se);
			} catch(Exception e) {
				System.out.println(e);
			} finally {
				try {
					if(rs!=null)rs.close();
					if (pstmt!=null) pstmt.close();
					if (con!=null) pstmt.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
			return cdvo;

		}
		
		//배달원테이블에서 onum 초기화//出前の人テーブルでonum初期化
		public ArrayList<OrderVO> getDmanCancel(int delinum) throws Exception { 
			ArrayList<OrderVO> oovo=new ArrayList<OrderVO>();
			String sml="update dman set onum=null where delinum=? ";
			Connection con=null;
			PreparedStatement pstmt=null;
			OrderVO retval=null;

			try{
				con=DBUtil.getConnection();
				pstmt=con.prepareStatement(sml); 
				pstmt.setInt(1, delinum);
				pstmt.executeUpdate();		
				oovo.add(retval);
			} catch(SQLException e) {
				e.printStackTrace();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (pstmt!=null) pstmt.close();
					if (con!=null) pstmt.close();
				} catch(final SQLException e) {
					System.out.println("serviceOK");
				}
				return oovo;
			}
		}
		
	
	//dman테이블 onum update　//dmanテーブル　 onum update
	@SuppressWarnings("finally")
	public void getServiceOkdman(int no ,int delinum) throws Exception { 
		ArrayList<ServiceMenVO> oovo=new ArrayList<ServiceMenVO>();
		String sml="update dman set onum=? where delinum=?";
		Connection con=null;
		PreparedStatement pstmt=null;
		ServiceMenVO retval=null;

		try{
			con=DBUtil.getConnection();
			pstmt=con.prepareStatement(sml); 
			pstmt.setInt(1, no);
			pstmt.setInt(2, delinum);
			pstmt.executeUpdate();		
			oovo.add(retval);
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt!=null) pstmt.close();
				if (con!=null) pstmt.close();
			} catch(final SQLException e) {
				System.out.println("serviceOK");
			}
		}
	}

	//배달완료 버튼 눌렀을 때//出前完了のボタンを押す時
	//주문현황에서 삭제//注文現況で削除
	@SuppressWarnings("finally")
	public ArrayList<OrderVO> getServiceComplete(int no) throws Exception {
		ArrayList<OrderVO> oovo=new ArrayList<OrderVO>();
		String sml="delete from dorder where onum=? ";
		Connection con=null;
		PreparedStatement pstmt=null;

		OrderVO retval=null;

		try{
			con=DBUtil.getConnection();
			pstmt=con.prepareStatement(sml); 
			pstmt.setInt(1, no);
			pstmt.executeUpdate();	
			oovo.add(retval);
		} catch(SQLException e) {
			System.out.println("serviceOK");
		} catch(Exception e) {
			System.out.println("serviceOK");
		} finally {
			try {
				if (pstmt!=null) pstmt.close();
				if (con!=null) pstmt.close();
			} catch(final SQLException e) {
				System.out.println("serviceOK");
			}
			return oovo;
		}
	}

	//배달완료테이블에 추가할 데이터 검색//出前完了テーブルで追加するdataを検索
	public CompleteDeliveryVO getNum(int num) throws Exception{
		String dml="select d.onum, d.delinum, s.sname, s.snum, o.oaddr, o.menuCount "
				+ "from dman d, dstore s, dorder o "
				+ "where d.onum=o.onum and o.snum=s.snum and o.onum=?";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		CompleteDeliveryVO retval=null;
		try{
			con=DBUtil.getConnection();
			pstmt=con.prepareStatement(dml);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			if(rs.next()){
				retval=new CompleteDeliveryVO(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getInt(6));
			}	
		} catch(SQLException se) {
			System.out.println(se);
		} catch(Exception e) {
			System.out.println(e);
		} finally {
			try {
				if(rs!=null)rs.close();
				if (pstmt!=null) pstmt.close();
				if (con!=null) pstmt.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return retval;

	}

	//배달완료테이블에 추가//出前完了テーブルに追加
	@SuppressWarnings("finally")
	public CompleteDeliveryVO getCmptOK(CompleteDeliveryVO cdvo) throws Exception {
		String dml= "insert into dok values (?, ?, ?, ?, ?, ?)";
		Connection con=null;
		PreparedStatement pstmt=null;
		CompleteDeliveryVO retval=null;	
		try{
			con=DBUtil.getConnection();
			pstmt=con.prepareStatement(dml);
			retval=getNum(cdvo.getOnum());

			if(retval!=null){
				pstmt.setInt(1, cdvo.getOnum());
				pstmt.setInt(2, cdvo.getDelinum());
				pstmt.setString(3,cdvo.getSname());
				pstmt.setInt(4,cdvo.getSnum());
				pstmt.setString(5,cdvo.getOaddr());
				pstmt.setInt(6,cdvo.getMenuCount());
				pstmt.executeUpdate();

				
				Object[] options = {"確認"};
				JOptionPane.showOptionDialog(null, "配達完了！！", "メッセ-ジ", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,null, options,"確認");
			}
		}catch(NullPointerException ne){
			ne.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt!=null) pstmt.close();
				if (con!=null) pstmt.close();
			} catch(final SQLException e) {
				System.out.println("serviceOK");
			}
			return retval;
		}
	}

	//배달원등록//出前の人登録
	public void getDeliverRegiste(ServiceMenVO svo) throws Exception {
		String dml = "insert into dman" + " (deliname, delitel)"
				+ "values " + " (?, ?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		ServiceMenVO retval = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(dml);

			retval = getServiceManTel(svo.getTel());

			if(retval==null){
				pstmt.setString(1, svo.getName());
				pstmt.setString(2, svo.getTel());
				pstmt.executeUpdate();

				JOptionPane.showMessageDialog(null, "登録されました。");
			}
		} catch (SQLException e) {
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
			}
		}

	}

	public ArrayList<ServiceMenVO> getServiceMentotal() {
		ArrayList<ServiceMenVO> list = new ArrayList<ServiceMenVO>();
		String tml = "select * from dman";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ServiceMenVO smVo = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(tml);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				smVo = new ServiceMenVO(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getInt(4));
				list.add(smVo);
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

	//배달원로그인//出前の人ログイン
	public ServiceMenVO getDeliverCheck(String name, String telnum) throws Exception {
		String dml = "select * from dman where deliname=? and delitel=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ServiceMenVO retval = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(dml);
			pstmt.setString(1, name);
			pstmt.setString(2, telnum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				retval = new ServiceMenVO(rs.getInt("delinum"),rs.getString("deliname"), rs.getString("delitel"),rs.getInt("onum"));
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
		return retval;
	}

	//상세배달내용보여주기//詳細出前内容を見せる
	public OrderVO getServiceShow(int no) throws Exception {
		String dml="select o.onum, s.sname, s.saddr, o.oaddr, o.menuCount"
				+ " from dorder o, dstore s, ddeliver d "
				+ "where  o.onum=? "
				+ "and o.snum = s.snum and o.code=d.code";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		OrderVO retval=null;

		try{
			con=DBUtil.getConnection();
			pstmt=con.prepareStatement(dml);
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			if(rs.next()){
				retval=new OrderVO(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5));
			}	
		} catch(SQLException se) {
			System.out.println("serviceShow");
			System.out.println(se);
		} catch(Exception e) {
			System.out.println(e);
			System.out.println("serviceShow");
		} finally {
			try {
				if(rs!=null)rs.close();
				if (pstmt!=null) pstmt.close();
				if (con!=null) pstmt.close();
			} catch(SQLException e) {
				System.out.println("serviceShow");
			}
		}
		return retval;
	}
	//배달원 번호로 배달원 검색//出前の人の番号で出前の人を検索
	public ServiceMenVO getServiceManNum(int num) throws Exception {
		String dml = "select * from dman where delinum = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ServiceMenVO retval = null;
		try{
			con = DBUtil.getConnection( );
			pstmt = con.prepareStatement(dml);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery( );
			if(rs.next( )) {
				retval = new ServiceMenVO(rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getInt(4));
			}
		}catch(SQLException se) {
			System.out.println("serviceNum");
		}catch (Exception e){
			System.out.println("serviceNum");
		}finally{
			try{
				if (rs != null) rs.close( );
				if (pstmt != null) pstmt.close( );
				if (con != null) con.close( );
			}catch(SQLException se){
				System.out.println("serviceNum");
			}
		}
		return retval;
	}
	//배달원전화번호로 배달원 검색//出前の人の電話番号で出前の人を検索
	public ServiceMenVO getServiceManTel(String tel) throws Exception {
		String dml = "select * from dman where delitel = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ServiceMenVO retval =null;
		try{
			con = DBUtil.getConnection( );
			pstmt = con.prepareStatement(dml);
			pstmt.setString(1, tel);
			rs = pstmt.executeQuery( );
			if(rs.next( )) {
				retval = new ServiceMenVO(rs.getInt(1),rs.getString(2), rs.getString(3));
			}
		}catch(SQLException se) {
			System.out.println("serviceName");
			System.out.println(se);
		}catch (Exception e){
			System.out.println("serviceName");
		}finally{
			try{
				if (rs != null) rs.close( );
				if (pstmt != null) pstmt.close( );
				if (con != null) con.close( );
			}catch(SQLException se){
				System.out.println("serviceName");
			}
		}
		return retval;
	}

	public void serviceMenDelete(int no) throws Exception {
		String dml = "delete from dman where snum=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DBUtil.getConnection( );
			pstmt = con.prepareStatement(dml);
			pstmt.setInt(1, no);
			pstmt.executeUpdate( );
		}catch(SQLException se) {
			System.out.println("serviceDelete");
		}catch (Exception e){
			System.out.println("serviceDelete");
		}finally{
			try{
				if (pstmt != null) pstmt.close( );
				if (con != null) con.close( );
			}catch(SQLException se){
				System.out.println("serviceDelete");
			}
		}
	}
	
	//배달원수정//出前の人の情報を修正
	public void serviceMenUpdate(String tel, int num) {
		String tml = "update dman set delitel=? where delinum=?";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(tml);
			pstmt.setString(1, tel);
			pstmt.setInt(2, num);
			pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
			System.out.println(se);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
			}
		}
	}

	public ServiceMenVO getServiceManName(String dname) throws Exception {
		String dml = "select * from dman where deliname = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ServiceMenVO retval =null;
		try{
			con = DBUtil.getConnection( );
			pstmt = con.prepareStatement(dml);
			pstmt.setString(1, dname);
			rs = pstmt.executeQuery( );
			if(rs.next( )) {
				retval = new ServiceMenVO(rs.getInt(1),rs.getString(2), rs.getString(3));
			}
		}catch(SQLException se) {
			System.out.println("serviceName");
			System.out.println(se);
		}catch (Exception e){
			System.out.println("serviceName");
		}finally{
			try{
				if (rs != null) rs.close( );
				if (pstmt != null) pstmt.close( );
				if (con != null) con.close( );
			}catch(SQLException se){
				System.out.println("serviceName");
			}
		}
		return retval;
	}

}