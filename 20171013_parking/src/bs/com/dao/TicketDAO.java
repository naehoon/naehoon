package bs.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

import bs.com.model.ParkingViewVO;

public class TicketDAO {

	private static TicketDAO ticketDAO = new TicketDAO();

	public static TicketDAO getInstance() {
		return ticketDAO;
	}

	private TicketDAO() {
	}

	// 티켓 등록 쿼리
	public int insertTicket(Connection conn, ParkingViewVO parkingViewVO) throws SQLException {
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
			pstmt.close();
		}

	}

	// 사용자 정보 조회하기
	public ArrayList<ParkingViewVO> selectParkingList(Connection conn) throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
//		ParkingViewVO parkingViewVO = new ParkingViewVO();
		ArrayList<ParkingViewVO> item = new ArrayList<ParkingViewVO>();

		try {
			pstmt = conn.prepareStatement("select * from ticket_tbl_01");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ParkingViewVO parkingViewVO = new ParkingViewVO();
				parkingViewVO.setTno(rs.getInt("tno"));
				parkingViewVO.setCarno(rs.getString("carno"));
				parkingViewVO.setPhone(rs.getString("phone"));
				parkingViewVO.setGrade(rs.getString("grade"));
				parkingViewVO.setTstat(rs.getString("tstat"));
				parkingViewVO.setStartdate(rs.getString("startdate"));
				parkingViewVO.setEnddate(rs.getString("enddate"));
				item.add(parkingViewVO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return item;

	}

	// 맥스값 채번하기
	public int selectMaxNum(Connection conn) throws SQLException {

		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select max(tno) from ticket_tbl_01");
			rs.next();
			return rs.getInt(1) + 1;

		} finally {
			stmt.close();
		}
	}

}
