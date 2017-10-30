package bs.com.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bs.com.dao.TicketDAO;
import bs.com.model.ParkingViewVO;
import bs.com.util.ConnectionProvider;

public class ViewParkService {

	private static ViewParkService instance = new ViewParkService();

	public static ViewParkService getInstance() {
		return instance;
	}

	private ViewParkService() {

	}

	// 정기권 조회 메서드
	public ParkingViewVO selectParkingList() {
		Connection conn = null;
		List<ParkingViewVO> item = null;

		try {
			conn = ConnectionProvider.getConnection();
			TicketDAO ticketDAO = TicketDAO.getInstance();
			item  = ticketDAO.selectParkingList(conn);

		} catch (SQLException se) {
			se.printStackTrace();
		}
		return new ParkingViewVO(item)  ;
	}

}
