package parkmanage.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;
import parkmanage.dao.ParkManageDAO;
import parkmanage.model.ParkManageVO;

public class ParkManageInService {
	
	private ParkManageDAO parkManageDAO = new ParkManageDAO();
	
	// 차량 입고 메서드
	public void insertParkInput(ParkManageVO parkManageVO) {
		Connection conn = null;

		try {
			conn = ConnectionProvider.getConnection();
			
			parkManageDAO.insertParkInput(conn, parkManageVO);

		} catch (SQLException se) {
			se.printStackTrace();
			throw new InvalidInputException("쿼리 실패" + se.getMessage());
		}
	}
	
}
