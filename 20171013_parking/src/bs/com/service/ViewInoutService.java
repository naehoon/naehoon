package bs.com.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import bs.com.dao.ParkDAO;
import bs.com.model.ParkManageVO;
import bs.com.model.ParkingViewVO;
import bs.com.util.ConnectionProvider;

public class ViewInoutService {

	private static ViewInoutService instance = new ViewInoutService();

	public static ViewInoutService getInstance() {
		return instance;
	}

	private ViewInoutService() {

	}

	//입출고차량 조회 메서드
	public ParkManageVO selectInOutList() {
		Connection conn = null;
		List<ParkManageVO> item = null;

		try {
			conn = ConnectionProvider.getConnection();
			ParkDAO parkDAO = ParkDAO.getInstance();
			item  = parkDAO.selectInOutList(conn);

		} catch (SQLException se) {
			se.printStackTrace();
		}
		return new ParkManageVO(item)  ;
	}

}
