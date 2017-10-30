package parkmanage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import jdbc.JdbcUtil;
import parkmanage.model.ParkManageVO;

public class ParkManageDAO {
	
	//정기권 등록한차량 갯수를 구하기 위한 메서드
	public int selectParkCount(Connection conn) throws SQLException{
		Statement stmt = null;
		ResultSet rs = null;
		
		try{
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from park_info_tbl");
			
			if(rs.next()){
				return rs.getInt(1);
			}
			return 0;
			
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

	

	// 차량입고 등록 쿼리
	public int insertParkInput(Connection conn, ParkManageVO parkManageVO) throws SQLException {
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(
					"insert into park_info_tbl (parkno, carno, grade, tstat, indate, outdate) "
					+ " values(null,?,?,'I',?,?)");

			pstmt.setString(1, parkManageVO.getCarno()); // 차량 번호
			pstmt.setString(2, parkManageVO.getGrade()); //주차 등급 Y, M, D
			pstmt.setString(3, parkManageVO.getIndate()); //입고일자
			pstmt.setString(4, parkManageVO.getOutdate()); //출고일자
			return pstmt.executeUpdate();

		} finally {
			JdbcUtil.close(pstmt);
		}
		
	}
	

	// 차량출고 등록 쿼리
	public int updateParkOutput(Connection conn, ParkManageVO parkManageVO) throws SQLException {
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(
					"update park_info_tbl "
					+ "set outdate = DATE_FORMAT(now(),'%Y-%m-%d %H:%i:%s') "
					+ ",tstat = 'O' "
					+ "where carno=?");

			pstmt.setString(1, parkManageVO.getCarno()); // 차량 번호
			return pstmt.executeUpdate();

		} finally {
			JdbcUtil.close(pstmt);
		}
		
	}	
	

	// 입고차량 정보 조회하기 
	public ArrayList<ParkManageVO> selectParkIn(Connection conn, ParkManageVO parkManageVO) throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ParkManageVO> item = new ArrayList<ParkManageVO>();
		
		try {
			pstmt = conn.prepareStatement("select "
					+ " a.tno,"
					+ " b.parkno,"
					+ " b.tstat,"
					+ " a.carno,"
					+ " a.phone,"
					+ " a.grade,"
					+ " b.indate"
					+ " from ticket_tbl_01 a LEFT OUTER JOIN park_info_tbl b "
					+ " on a.carno = b.carno "
					+ " where a.carno = ?"
					+ " order by parkno desc "
					+ " limit 1");
			
			pstmt.setString(1, parkManageVO.getCarno());
			rs = pstmt.executeQuery();
						
			if(rs.next()){
				do{
					System.out.println("입고 조회쿼리 실행");
					
					parkManageVO.setTno(rs.getString("tno"));
					parkManageVO.setParkno(rs.getInt("parkno"));
					parkManageVO.setCarno(rs.getString("carno"));
					parkManageVO.setTstat(rs.getString("tstat"));
					parkManageVO.setPhone(rs.getString("phone"));
					parkManageVO.setGrade(rs.getString("grade"));
					parkManageVO.setIndate(rs.getString("indate"));
					//parkManageVO.setOutdate(rs.getString("outdate")); //입고 할때 출고일은 안적어 준다.
					
					item.add(parkManageVO);
					
				}while(rs.next());
			}
			return item;
			
		} finally {
			JdbcUtil.close(pstmt);
		}
		
	}		

	// 출고차량 정보 조회하기  (가장 마지막 건만 조회한다)
	public ArrayList<ParkManageVO> selectParkOut(Connection conn, ParkManageVO parkManageVO) throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ParkManageVO> item = new ArrayList<ParkManageVO>();
		
		try {
			pstmt = conn.prepareStatement("select * from park_info_tbl where carno=? "
					+ " order by parkno desc limit 1");

			pstmt.setString(1, parkManageVO.getCarno());
			rs = pstmt.executeQuery();
						
			if(rs.next()){
				do{
					parkManageVO.setParkno(rs.getInt("parkno"));
					parkManageVO.setCarno(rs.getString("carno"));
					parkManageVO.setGrade(rs.getString("grade"));
					parkManageVO.setTstat(rs.getString("tstat"));
					parkManageVO.setIndate(rs.getString("indate"));
					parkManageVO.setOutdate(rs.getString("outdate"));
					
					item.add(parkManageVO);
					
				}while(rs.next());
			}
			return item;
			
		} finally {
			JdbcUtil.close(pstmt);
		}
		
	}			

	// 주차번호 맥스값+1 채번하기
	public int selectMaxParkNum(Connection conn) throws SQLException {

		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select max(parkno) from park_info_tbl");
			rs.next();
			return rs.getInt(1) + 1;

		} finally {
			JdbcUtil.close(stmt);
		}
	}

	// 주차 현황 조회하기
	public ArrayList<ParkManageVO> selectInOutList(Connection conn, int startRow, int size) throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ParkManageVO> item = new ArrayList<ParkManageVO>();

		try {
			//페이징 처리를 하기위한 리미트 쿼리 
			pstmt = conn.prepareStatement("select * from park_info_tbl " + " order by parkno desc limit ?, ?");
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ParkManageVO parkManageVO = new ParkManageVO();
				parkManageVO.setParkno(rs.getInt("parkno")); //주차번호
				parkManageVO.setCarno(rs.getString("carno")); //차량번호 
				parkManageVO.setGrade(rs.getString("grade")); //주차등급
				parkManageVO.setTstat(rs.getString("tstat")); //상태
				parkManageVO.setIndate(rs.getString("indate"));
				parkManageVO.setOutdate(rs.getString("outdate"));
				item.add(parkManageVO);
			}

		} finally {
			JdbcUtil.close(pstmt);
		}
		return item;

	}

}
