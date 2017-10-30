package bs.com.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import bs.com.dao.ParkDAO;
import bs.com.model.ParkManageVO;
import bs.com.util.ConnectionProvider;

//주차 입고 관리
public class ParkmanageService {

	private static ParkmanageService instance = new ParkmanageService();

	public static ParkmanageService getInstance() {
		return instance;
	}

	private ParkmanageService() {
	}

	// 차량 입고 메서드
	public void insertParkInput(ParkManageVO parkManageVO) {
		Connection conn = null;

		try {
			conn = ConnectionProvider.getConnection();
			// conn.setAutoCommit(false);
			ParkDAO parkDAO = ParkDAO.getInstance();
			parkDAO.insertParkInput(conn, parkManageVO);

		} catch (SQLException se) {
			se.printStackTrace();
			throw new InvalidInputException("쿼리 실패" + se.getMessage());
		}
	}
	
	// 차량 출고 메서드
		public void updateParkOutput(ParkManageVO parkManageVO) {
			Connection conn = null;

			try {
				conn = ConnectionProvider.getConnection();
				// conn.setAutoCommit(false);
				ParkDAO parkDAO = ParkDAO.getInstance();
				parkDAO.updateParkOutput(conn, parkManageVO);

			} catch (SQLException se) {
				se.printStackTrace();
				throw new InvalidInputException("쿼리 실패" + se.getMessage());
			}
		}	

	// 입고차량 조회 메서드
	public ParkManageVO selectParkIn(ParkManageVO parkManageVO) {
		Connection conn = null;
		List<ParkManageVO> item = null;

		try {
			conn = ConnectionProvider.getConnection();
			ParkDAO parkDAO = ParkDAO.getInstance();
			item = parkDAO.selectParkIn(conn, parkManageVO);

		} catch (SQLException se) {
			se.printStackTrace();
		}
		return new ParkManageVO(item);
	}

	// 출고차량 조회 메서드
	public ParkManageVO selectParkOut(ParkManageVO parkManageVO) {
		Connection conn = null;
		List<ParkManageVO> item = null;

		try {
			conn = ConnectionProvider.getConnection();
			ParkDAO parkDAO = ParkDAO.getInstance();
			item = parkDAO.selectParkOut(conn, parkManageVO);

			if (item.size() == 0) {
				throw new ListNotFoundException("해당하는 차량번호 없음");
			}

		} catch (SQLException se) {
			se.printStackTrace();
		}
		return new ParkManageVO(item);
	}

	// 맥스값 채번 메서드+1
	public int selectMaxParkNum() {
		Connection conn = null;
		int maxNo = 0;
		try {
			conn = ConnectionProvider.getConnection();
			ParkDAO parkDAO = ParkDAO.getInstance();
			maxNo = parkDAO.selectMaxParkNum(conn);

		} catch (SQLException se) {
			se.printStackTrace();
		}
		return maxNo;
	}

}
