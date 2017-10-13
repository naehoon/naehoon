package bs.com.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServlet;

import bs.com.dao.ParkingManageDAO;
import bs.com.model.ParkingTicketVO;
import bs.com.model.ParkingViewVO;
import bs.com.util.jdbcUtil;

public class ParkmanageService extends HttpServlet {

	List<ParkingTicketVO> list = null;
	
	private static ParkmanageService instance = new ParkmanageService();
	
	public static ParkmanageService getInstance(){
		return instance;
	}
	
	private ParkmanageService(){
		
	}
	
	//테이블 조회 
	public ParkingViewVO selectManageList() throws ClassNotFoundException{
			
		ParkingViewVO parkingViewVO = null;
		Connection conn  =null;
		List<ParkingTicketVO> list = null;
		
		try{
			
			Class.forName("org.apache.commons.dbcp.PoolingDriver");
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:/wdbpool");
			ParkingManageDAO parkingManageDAO = ParkingManageDAO.getInstance();
//			list = parkingManageDAO.selectManageList(conn, carno);
			
			System.out.println("매니저 서비스 실행" + list);
			
			parkingViewVO.setList(list);
			
		}catch (SQLException e) {
			// TODO: handle exception
			throw new ServiceException("목록 구하기 실패했습니다 : " + e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			jdbcUtil.close(conn);
		}
		return parkingViewVO;
	}
//
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//		ParkingTicketVO parkingTicketVO = new ParkingTicketVO(); 
//		req.setCharacterEncoding("utf-8");
//		
//		String cmd = req.getParameter("cmd");
//		String carno = req.getParameter("carno");
//		
//		if(cmd.equals("parkIn")){
//			
//				Connection conn  =null;
//				
//				try{
//					
//					Class.forName("org.apache.commons.dbcp.PoolingDriver");
//					conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:/wdbpool");
//					ParkingManageDAO parkingManageDAO = ParkingManageDAO.getInstance();
//					
//					list = parkingManageDAO.selectManageList(conn, carno);
//					
//				}catch (SQLException e) {
//					// TODO: handle exception
//					throw new ServiceException("목록 구하기 실패했습니다 : " + e.getMessage(), e);
//				} catch (ClassNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}finally {
//					jdbcUtil.close(conn);
//				}
//			
//		}else{
//			
//			System.out.println("ttttttttt");
//		}
//		
	}

