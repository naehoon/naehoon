package bs.com.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServlet;

import bs.com.dao.ParkingTicketDAO;
import bs.com.model.ParkingTicketVO;
import bs.com.model.ParkingViewVO;
import bs.com.util.jdbcUtil;

public class ViewParkService extends HttpServlet{

	private static ViewParkService instance = new ViewParkService();
	
	public static ViewParkService getInstance(){
		return instance;
	}
	
	private ViewParkService(){
	}
	
	//getMaxNum 채번 하기 
	public int getMaxNum() throws ClassNotFoundException{
		Connection conn  =null;
		int maxCount;
		
		try{
			Class.forName("org.apache.commons.dbcp.PoolingDriver");
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:/wdbpool");
			ParkingTicketDAO parkingTicketDAO = ParkingTicketDAO.getInstance();
			
			maxCount = parkingTicketDAO.selectMaxCount(conn);
			
			return maxCount;
			
		}catch (SQLException e) {
			// TODO: handle exception
			throw new ServiceException("목록 구하기 실패했습니다 : " + e.getMessage(), e);
		}finally {
			jdbcUtil.close(conn);
		}
				
	}

	//테이블 조회 
	public ParkingViewVO selectParkingList() throws ClassNotFoundException{
		Connection conn  =null;
		List<ParkingTicketVO> list = null;
		
		try{
			Class.forName("org.apache.commons.dbcp.PoolingDriver");
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:/wdbpool");
			ParkingTicketDAO parkingTicketDAO = ParkingTicketDAO.getInstance();
			
			list = parkingTicketDAO.selectParkingList(conn);
			int count = list.size();
			
			return  new ParkingViewVO(list, count);
			
		}catch (SQLException e) {
			// TODO: handle exception
			throw new ServiceException("목록 구하기 실패했습니다 : " + e.getMessage(), e);
		}finally {
			jdbcUtil.close(conn);
		}
				
	}
	
}
