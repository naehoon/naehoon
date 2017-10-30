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

	// Ƽ�� ��� �޼���
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

	// �ƽ��� ä�� �޼���
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
