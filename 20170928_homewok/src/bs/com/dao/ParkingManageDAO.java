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

public class ParkingManageDAO {
	
	private static ParkingManageDAO parkingManageDAO = new ParkingManageDAO();

	public static ParkingManageDAO getInstance() {
		return parkingManageDAO;
	}

	private ParkingManageDAO() {

	}
	


	//테이블 조회 
	public List<ParkingTicketVO> selectManageList(Connection conn, String carno) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from park_info_tbl where carno =?");
			
			System.out.println("car no : " + carno);
			
			pstmt.setString(1, carno);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				List<ParkingTicketVO> list = new ArrayList<ParkingTicketVO>();
				do {
					ParkingTicketVO parkingTicketVO = new ParkingTicketVO();
					
					parkingTicketVO.setTno(rs.getInt("parkno"));
					parkingTicketVO.setCarno(rs.getString("carno"));
					parkingTicketVO.setGrade(rs.getString("grade"));
					parkingTicketVO.setTstat(rs.getString("tstat"));
					parkingTicketVO.setStartDate(rs.getString("indate"));
					parkingTicketVO.setEndDate(rs.getString("outdate"));
					
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
	

}
