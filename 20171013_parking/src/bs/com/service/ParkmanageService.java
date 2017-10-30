package bs.com.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import bs.com.dao.ParkDAO;
import bs.com.model.ParkManageVO;
import bs.com.util.ConnectionProvider;

//���� �԰� ����
public class ParkmanageService {

	private static ParkmanageService instance = new ParkmanageService();

	public static ParkmanageService getInstance() {
		return instance;
	}

	private ParkmanageService() {
	}

	// ���� �԰� �޼���
	public void insertParkInput(ParkManageVO parkManageVO) {
		Connection conn = null;

		try {
			conn = ConnectionProvider.getConnection();
			// conn.setAutoCommit(false);
			ParkDAO parkDAO = ParkDAO.getInstance();
			parkDAO.insertParkInput(conn, parkManageVO);

		} catch (SQLException se) {
			se.printStackTrace();
			throw new InvalidInputException("���� ����" + se.getMessage());
		}
	}
	
	// ���� ��� �޼���
		public void updateParkOutput(ParkManageVO parkManageVO) {
			Connection conn = null;

			try {
				conn = ConnectionProvider.getConnection();
				// conn.setAutoCommit(false);
				ParkDAO parkDAO = ParkDAO.getInstance();
				parkDAO.updateParkOutput(conn, parkManageVO);

			} catch (SQLException se) {
				se.printStackTrace();
				throw new InvalidInputException("���� ����" + se.getMessage());
			}
		}	

	// �԰����� ��ȸ �޼���
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

	// ������� ��ȸ �޼���
	public ParkManageVO selectParkOut(ParkManageVO parkManageVO) {
		Connection conn = null;
		List<ParkManageVO> item = null;

		try {
			conn = ConnectionProvider.getConnection();
			ParkDAO parkDAO = ParkDAO.getInstance();
			item = parkDAO.selectParkOut(conn, parkManageVO);

			if (item.size() == 0) {
				throw new ListNotFoundException("�ش��ϴ� ������ȣ ����");
			}

		} catch (SQLException se) {
			se.printStackTrace();
		}
		return new ParkManageVO(item);
	}

	// �ƽ��� ä�� �޼���+1
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
