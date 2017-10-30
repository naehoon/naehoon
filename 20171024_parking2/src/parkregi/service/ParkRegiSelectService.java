package parkregi.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import parkmanage.model.ParkManageVO;
import parkregi.dao.ParkRegiDAO;
import parkregi.model.ParkingRegiVO;

public class ParkRegiSelectService {
	
	private ParkRegiDAO parkRegiDAO = new ParkRegiDAO();

	// 정기권 차량 단건 조회 메서드
	public ParkingRegiVO selectParkingOne(ParkingRegiVO parkingRegiVO) {
		Connection conn = null;
		List<ParkingRegiVO> item = null;

		try {
			conn = ConnectionProvider.getConnection();
			item  = parkRegiDAO.selectParkingOne(conn, parkingRegiVO);

			
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return new ParkingRegiVO(item)  ;
	}

}
