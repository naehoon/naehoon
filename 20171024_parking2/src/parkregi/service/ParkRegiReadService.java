package parkregi.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import parkregi.dao.ParkRegiDAO;
import parkregi.model.ParkingRegiVO;

public class ParkRegiReadService {
	
	private ParkRegiDAO parkRegiDAO = new ParkRegiDAO();
	private int size = 10;

	// 정기권 조회 메서드
	public ParkingRegiVO selectParkingList(int pageNo) {
		Connection conn = null;
		List<ParkingRegiVO> item = null;

		try {
			conn = ConnectionProvider.getConnection();
			
			int total = parkRegiDAO.selectCount(conn); //자료 갯수 구하기
			item  = parkRegiDAO.selectParkingList(conn, (pageNo-1)*size, size);
			return new ParkingRegiVO(total, pageNo, size, item)  ;

		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		}
		
	}

}
