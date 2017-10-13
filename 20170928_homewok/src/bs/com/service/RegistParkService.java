package bs.com.service;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.fabric.Response;

import bs.com.dao.ParkingTicketDAO;
import bs.com.model.ParkingTicketVO;
import bs.com.model.ParkingViewVO;
import bs.com.util.jdbcUtil;

//주차 정기권 등록 화면 
public class RegistParkService extends HttpServlet{
//	
//	private static RegistParkService instance = new RegistParkService();
//	
//	public static RegistParkService getInstance(){
//		return instance;
//	}
//	
//	private RegistParkService(){
//		
//	}
//
//	//테이블 주차정기권 등록조회 
//	public ParkingViewVO selectParkingMember(String carno) throws ClassNotFoundException{
//		Connection conn  =null;
//		List<ParkingTicketVO> list = null;
//		
//		try{
//			Class.forName("org.apache.commons.dbcp.PoolingDriver");
//			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:/wdbpool");
//			ParkingTicketDAO parkingTicketDAO = ParkingTicketDAO.getInstance();
//			
//			list = parkingTicketDAO.selectParkingMember(conn, carno);
//			int count = list.size();
//			
//			return  new ParkingViewVO(list, count);
//			
//		}catch (SQLException e) {
//			// TODO: handle exception
//			throw new ServiceException("조회를 실패했습니다 : " + e.getMessage(), e);
//		}finally {
//			jdbcUtil.close(conn);
//		}
//				
//	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ParkingTicketVO parkingTicketVO = new ParkingTicketVO(); 
		req.setCharacterEncoding("utf-8");
		String cmd =req.getParameter("cmd");
		String carno = req.getParameter("carno");
		
		parkingTicketVO.setCarno(req.getParameter("carno"));
		parkingTicketVO.setPhone(req.getParameter("phone"));
		parkingTicketVO.setGrade(req.getParameter("grade"));
		parkingTicketVO.setTstat(req.getParameter("tstat"));
		parkingTicketVO.setStartDate(req.getParameter("startDate"));
		parkingTicketVO.setEndDate(req.getParameter("endDate"));
		
		Connection conn = null;
		
			try{
				
				Class.forName("org.apache.commons.dbcp.PoolingDriver");
				conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:/wdbpool");
				ParkingTicketDAO parkingTicketDAO = ParkingTicketDAO.getInstance();
				parkingTicketDAO.insert(conn, parkingTicketVO);
				
				resp.setContentType("text/html;charset=utf-8");
		        PrintWriter out = resp.getWriter();
				
				out.println("<script>");
				out.println("alert('등록 성공!!!')");
				out.println("location.href='./parkregi.jsp'");
				out.println("</script>");
				
			}catch (SQLException e) {
				// TODO: handle exception
				throw new ServiceException("등록을 실패했습니다 : "  + e.getMessage(), e);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				jdbcUtil.close(conn);
			}
		}
		

}
