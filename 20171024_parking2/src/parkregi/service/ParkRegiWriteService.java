package parkregi.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import parkregi.dao.ParkRegiDAO;
import parkregi.model.ParkingRegiVO;

public class ParkRegiWriteService {
	
	private ParkRegiDAO parkRegiDAO = new ParkRegiDAO();
	
	//정기 주차권 인서트 메서드
	public int write(ParkingRegiVO parkingRegiVO){
		
		Connection conn = null;
		try{
			conn =  ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			//주차권 중복확인 하기 
			ParkingRegiVO dupleCarno = parkRegiDAO.selectByCarno(conn, parkingRegiVO);
			
			//중복된 차량번호 있을때
			if(dupleCarno!=null){
				System.out.println("중복된 아이디가 있을때");
				JdbcUtil.rollback(conn);
				throw new DuplicateIdException();
			}
			
			int result = parkRegiDAO.insertTicket(conn, parkingRegiVO);
			conn.commit();
			return result;
			
		}catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
			
		}catch (RuntimeException e) {
			JdbcUtil.rollback(conn);
			throw e;
			
		}finally {
			JdbcUtil.close(conn);
		}
	}

}
