package bs.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import bs.com.model.ParkingTicketVO;
import bs.com.util.jdbcUtil;

public class ParkingTicketDAO {

	private static ParkingTicketDAO parkingTicketDAO = new ParkingTicketDAO();

	public static ParkingTicketDAO getInstance() {
		return parkingTicketDAO;
	}

	private ParkingTicketDAO() {

	}
	

	//테이블 정기권 등록차량 조회 
	public List<ParkingTicketVO> selectParkingMember(Connection conn, String carno) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from ticket_tbl_01 where carno=?");
			pstmt.setString(1, carno);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				List<ParkingTicketVO> list = new ArrayList<ParkingTicketVO>();
				do {
					ParkingTicketVO parkingTicketVO = new ParkingTicketVO();
					
					parkingTicketVO.setCarno(rs.getString("carno"));
					parkingTicketVO.setPhone(rs.getString("phone"));
					parkingTicketVO.setGrade(rs.getString("grade"));
					parkingTicketVO.setTstat(rs.getString("tstat"));
					parkingTicketVO.setStartDate(rs.getString("startDate"));
					parkingTicketVO.setEndDate(rs.getString("endDate"));
					
					list.add(parkingTicketVO);
				} while (rs.next());
				
				return list;
				
			} else {
				return Collections.emptyList();
			}
		} finally {
			jdbcUtil.close(rs);
			jdbcUtil.close(pstmt);
		}
	}
	

	//테이블 조회 
	public List<ParkingTicketVO> selectParkingList(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from ticket_tbl_01");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				List<ParkingTicketVO> list = new ArrayList<ParkingTicketVO>();
				do {
					ParkingTicketVO parkingTicketVO = new ParkingTicketVO();
					
					parkingTicketVO.setTno(rs.getInt("tno"));
					parkingTicketVO.setCarno(rs.getString("carno"));
					parkingTicketVO.setPhone(rs.getString("phone"));
					parkingTicketVO.setGrade(rs.getString("grade"));
					parkingTicketVO.setTstat(rs.getString("tstat"));
					parkingTicketVO.setStartDate(rs.getString("startDate"));
					parkingTicketVO.setEndDate(rs.getString("endDate"));
					
					list.add(parkingTicketVO);
				} while (rs.next());
				
				return list;
				
			} else {
				return Collections.emptyList();
			}
		} finally {
			jdbcUtil.close(rs);
			jdbcUtil.close(pstmt);
		}
	}

	// max 값 +1 채번
	public int selectMaxCount(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int tknum;
		try {
			pstmt = conn.prepareStatement("select max(tno) from ticket_tbl_01");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				tknum = rs.getInt(1);
				System.out.println("22222222222222222 :" + tknum);
				return tknum +1;

			} else {
				return tknum = 0;
			}
		} finally {
			jdbcUtil.close(rs);
			jdbcUtil.close(pstmt);
		}
	}

	// 주차 정기권 등록 메서드
	public int insert(Connection conn, ParkingTicketVO parkingTicketVO) throws SQLException {
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement("insert into ticket_tbl_01 "
					+ "(tno, carno, phone, grade, tstat, startDate, endDate, regidate" + ")"
					+ " values (null, ? , ? , ?, ? , DATE_FORMAT(?, '%Y-%m-%d') , DATE_FORMAT(?, '%Y-%m-%d') , DATE_FORMAT(now(), '%Y-%m-%d'))");
			pstmt.setString(1, parkingTicketVO.getCarno());
			pstmt.setString(2, parkingTicketVO.getPhone());
			pstmt.setString(3, parkingTicketVO.getGrade());
			pstmt.setString(4, parkingTicketVO.getTstat());
			pstmt.setString(5, parkingTicketVO.getStartDate());
			pstmt.setString(6, parkingTicketVO.getEndDate());
			return pstmt.executeUpdate();

		} finally {
			jdbcUtil.close(pstmt);
		}
	}

}
