package parkmanage.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import parkmanage.dao.ParkManageDAO;
import parkmanage.model.ParkManageVO;

public class ParkManageInViewService {
	
	private ParkManageDAO parkManageDAO = new ParkManageDAO();

	// 입고차량 조회 메서드
	public ParkManageVO selectParkIn(ParkManageVO parkManageVO) {
		Connection conn = null;
		List<ParkManageVO> item = null;

		try {
			conn = ConnectionProvider.getConnection();
			item = parkManageDAO.selectParkIn(conn, parkManageVO);

		} catch (SQLException se) {
			se.printStackTrace();
		}
		return new ParkManageVO(item);
	}

	// 주차번호 맥스값 채번 메서드+1
	public int selectMaxParkNum() {
		Connection conn = null;
		int maxNo = 0;
		try {
			conn = ConnectionProvider.getConnection();
			maxNo = parkManageDAO.selectMaxParkNum(conn);

		} catch (SQLException se) {
			se.printStackTrace();
		}
		return maxNo;
	}

}
