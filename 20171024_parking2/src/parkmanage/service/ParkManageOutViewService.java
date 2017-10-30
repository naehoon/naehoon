package parkmanage.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import parkmanage.dao.ParkManageDAO;
import parkmanage.model.ParkManageVO;

public class ParkManageOutViewService {

	private ParkManageDAO parkManageDAO = new ParkManageDAO();

	// 출고차량 조회 메서드
	public ParkManageVO selectParkOut(ParkManageVO parkManageVO) {
		Connection conn = null;
		List<ParkManageVO> item = null;

		try {
			conn = ConnectionProvider.getConnection();
			item = parkManageDAO.selectParkOut(conn, parkManageVO);

		} catch (SQLException se) {
			se.printStackTrace();
		}
		return new ParkManageVO(item);
	}

}
