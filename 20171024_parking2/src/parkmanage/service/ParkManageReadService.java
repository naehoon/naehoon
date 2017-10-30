package parkmanage.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import parkmanage.dao.ParkManageDAO;
import parkmanage.model.ParkManageVO;

public class ParkManageReadService {
	
	private ParkManageDAO parkManageDAO = new ParkManageDAO();
	private int size = 10;

	// 주차 현황 조회 메서드
	public ParkManageVO selectParkingList(int pageNo) {
		Connection conn = null;
		List<ParkManageVO> item = null;

		try {
			conn = ConnectionProvider.getConnection();
			
			int total = parkManageDAO.selectParkCount(conn); //자료 갯수 구하기
			item  = parkManageDAO.selectInOutList(conn, (pageNo-1)*size, size);
			
			return new ParkManageVO(total, pageNo, size, item)  ;
//			item  = parkManageDAO.selectInOutList(conn);

		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		}
		
	}

}
