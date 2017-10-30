package bs.com.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import bs.com.dao.TicketDAO;
import bs.com.model.ParkingViewVO;
import bs.com.util.ConnectionProvider;

public class RegistParkService {

	private static RegistParkService instance = new RegistParkService();

	public static RegistParkService getInstance() {
		return instance;
	}

	private RegistParkService() {

	}

	// 티켓 등록 메서드
	public void insertParkInfo(ParkingViewVO parkingViewVO) {
		Connection conn = null;

		try {
			conn = ConnectionProvider.getConnection();
			// conn.setAutoCommit(false);
			TicketDAO ticketDAO = TicketDAO.getInstance();
			ticketDAO.insertTicket(conn, parkingViewVO);

		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	// 맥스값 채번 메서드
	public int selectMaxNum() {
		Connection conn = null;
		int maxNo = 0;
		try {
			conn = ConnectionProvider.getConnection();
			TicketDAO ticketDAO = TicketDAO.getInstance();
			maxNo = ticketDAO.selectMaxNum(conn);

		} catch (SQLException se) {
			se.printStackTrace();
		}
		return maxNo;
	}

}
