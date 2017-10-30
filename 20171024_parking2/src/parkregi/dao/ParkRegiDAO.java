package parkregi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import jdbc.JdbcUtil;
import parkregi.model.ParkingRegiVO;

public class ParkRegiDAO {
	
	//정기권 등록한차량 갯수를 구하기 위한 메서드
	public int selectCount(Connection conn) throws SQLException{
		Statement stmt = null;
		ResultSet rs = null;
		
		try{
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from ticket_tbl_01");
			
			if(rs.next()){
				return rs.getInt(1);
			}
			return 0;
			
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

	//주차 정기권 등록 메서드
	public int insertTicket(Connection conn, ParkingRegiVO parkingViewVO) throws SQLException {
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(
					"insert into ticket_tbl_01 (tno, carno, phone, grade, tstat, startdate, enddate, regidate) values(null,?,?,?,?,DATE_FORMAT(?, '%Y-%m-%d'),DATE_FORMAT(?, '%Y-%m-%d'),  DATE_FORMAT(now(), '%Y-%m-%d'))");

			pstmt.setString(1, parkingViewVO.getCarno());
			pstmt.setString(2, parkingViewVO.getPhone());
			pstmt.setString(3, parkingViewVO.getGrade());
			pstmt.setString(4, parkingViewVO.getTstat());
			pstmt.setString(5, parkingViewVO.getStartdate());
			pstmt.setString(6, parkingViewVO.getEnddate());

			return pstmt.executeUpdate();

		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	//주차권 등록차량 조회 메서드
	public ParkingRegiVO selectByCarno(Connection conn, ParkingRegiVO parkingRegiVO) throws SQLException{
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			pstmt = conn.prepareStatement("select * from ticket_tbl_01 where carno = ?");
			pstmt.setString(1, parkingRegiVO.getCarno());
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				parkingRegiVO = new ParkingRegiVO(rs.getInt("tno")
																	, rs.getString("carno")
																	, rs.getString("phone")
																	, rs.getString("grade")
																	, rs.getString("tstat")
																	, rs.getString("startdate")
																	, rs.getString("enddate") );
				return parkingRegiVO;
			
			}else{
				//조회된게 없으면 널값 리턴
				return null;
			}
			
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}

	//주차 정기권 목록 조회 메서드 
	public ArrayList<ParkingRegiVO> selectParkingList(Connection conn, int startRow, int size) throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ParkingRegiVO> item = new ArrayList<ParkingRegiVO>();

		try {
			//페이징 처리를 하기위한 리미트 쿼리 			
			pstmt = conn.prepareStatement("select * from ticket_tbl_01" + " order by tno desc limit ?, ?");
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ParkingRegiVO parkingRegiVO = new ParkingRegiVO();
				parkingRegiVO.setTno(rs.getInt("tno"));
				parkingRegiVO.setCarno(rs.getString("carno"));
				parkingRegiVO.setPhone(rs.getString("phone"));
				parkingRegiVO.setGrade(rs.getString("grade"));
				parkingRegiVO.setTstat(rs.getString("tstat"));
				parkingRegiVO.setStartdate(rs.getString("startdate"));
				parkingRegiVO.setEnddate(rs.getString("enddate"));
				item.add(parkingRegiVO);
			}

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return item;
	}
	
	
	//주차 정기권 단건 조회 메서드 
	public ArrayList<ParkingRegiVO> selectParkingOne(Connection conn, ParkingRegiVO parkingRegiVO) throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ParkingRegiVO> item = new ArrayList<ParkingRegiVO>();

		try {
			pstmt = conn.prepareStatement("select * from ticket_tbl_01 where carno= ? ");
			pstmt.setString(1, parkingRegiVO.getCarno());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				parkingRegiVO.setTno(rs.getInt("tno"));
				parkingRegiVO.setCarno(rs.getString("carno"));
				parkingRegiVO.setPhone(rs.getString("phone"));
				parkingRegiVO.setGrade(rs.getString("grade"));
				parkingRegiVO.setTstat(rs.getString("tstat"));
				parkingRegiVO.setStartdate(rs.getString("startdate"));
				parkingRegiVO.setEnddate(rs.getString("enddate"));
				item.add(parkingRegiVO);
			}

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return item;
	}
	

	//주차 정기권 번호 맥스값 채번 메서드 
	public int selectMaxNum(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select max(tno) from ticket_tbl_01");
			rs.next();
			return rs.getInt(1) + 1;

		} finally {
			JdbcUtil.close(stmt);
		}
	}

}
