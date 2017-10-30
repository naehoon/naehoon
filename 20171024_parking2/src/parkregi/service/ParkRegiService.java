package parkregi.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;
import parkregi.dao.ParkRegiDAO;
import parkregi.model.ParkingRegiVO;

public class ParkRegiService {

	private ParkRegiDAO parkRegiDAO =new ParkRegiDAO();
	
	//맥스값 채번 메서드
	public ParkingRegiVO getMaxParkRegNum(){
		try(Connection conn = ConnectionProvider.getConnection()){
			
			//맥스값 채번하기
			int maxNum = parkRegiDAO.selectMaxNum(conn);
			return new ParkingRegiVO(maxNum);
			
		}catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
}
