package bs.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bs.com.model.ParkManageVO;
import bs.com.model.ParkingViewVO;

public class ParkDAO {
	
	private static ParkDAO parkDAO = new ParkDAO();

	public static ParkDAO getInstance() {
		return parkDAO;
	}

	private ParkDAO() {
	}

	// �����԰� ��� ����
	public int insertParkInput(Connection conn, ParkManageVO parkManageVO) throws SQLException {
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(
					"insert into park_info_tbl (parkno, carno, grade, tstat, indate, outdate) "
					+ " values(null,?,?,'I',?,?)");

			pstmt.setString(1, parkManageVO.getCarno()); // ���� ��ȣ
			pstmt.setString(2, parkManageVO.getGrade()); //���� ��� Y, M, D
			pstmt.setString(3, parkManageVO.getIndate()); //�԰�����
			pstmt.setString(4, parkManageVO.getOutdate()); //�������
			return pstmt.executeUpdate();

		} finally {
			pstmt.close();
		}
		
	}
	

	// ������� ��� ����
	public int updateParkOutput(Connection conn, ParkManageVO parkManageVO) throws SQLException {
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(
					"update park_info_tbl "
					+ "set outdate = DATE_FORMAT(now(),'%Y-%m-%d %H:%i:%s') "
					+ ",tstat = 'O' "
					+ "where carno=?");

			pstmt.setString(1, parkManageVO.getCarno()); // ���� ��ȣ
			return pstmt.executeUpdate();

		} finally {
			pstmt.close();
		}
		
	}	
	

	// �԰����� ���� ��ȸ�ϱ� 
	public ArrayList<ParkManageVO> selectParkIn(Connection conn, ParkManageVO parkManageVO) throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ParkManageVO> item = new ArrayList<ParkManageVO>();
		
		try {
			pstmt = conn.prepareStatement("select "
					+ " a.tno,"
					+ " b.parkno,"
					+ " b.tstat,"
					+ " a.carno,"
					+ " a.phone,"
					+ " a.grade,"
					+ " b.indate"
					//+ " b.outdate "
					+ " from ticket_tbl_01 a LEFT OUTER JOIN park_info_tbl b "
					+ " on a.carno = b.carno "
					+ " where a.carno = ?");
			
			pstmt.setString(1, parkManageVO.getCarno());
			rs = pstmt.executeQuery();
						
			if(rs.next()){
				do{
					parkManageVO.setTno(rs.getString("tno"));
					parkManageVO.setParkno(rs.getInt("parkno"));
					parkManageVO.setCarno(rs.getString("carno"));
					parkManageVO.setTstat(rs.getString("tstat"));
					parkManageVO.setPhone(rs.getString("phone"));
					parkManageVO.setGrade(rs.getString("grade"));
					parkManageVO.setIndate(rs.getString("indate"));
					//parkManageVO.setOutdate(rs.getString("outdate"));
					
					item.add(parkManageVO);
					
				}while(rs.next());
			}
			return item;
			
		} finally {
			pstmt.close();
		}
		
	}		
	

	// ������� ���� ��ȸ�ϱ� 
	public ArrayList<ParkManageVO> selectParkOut(Connection conn, ParkManageVO parkManageVO) throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ParkManageVO> item = new ArrayList<ParkManageVO>();
		
		try {
			pstmt = conn.prepareStatement("select *from park_info_tbl where carno=?");
			pstmt.setString(1, parkManageVO.getCarno());
			rs = pstmt.executeQuery();
						
			if(rs.next()){
				do{
					parkManageVO.setParkno(rs.getInt("parkno"));
					parkManageVO.setCarno(rs.getString("carno"));
					parkManageVO.setGrade(rs.getString("grade"));
					parkManageVO.setTstat(rs.getString("tstat"));
					parkManageVO.setIndate(rs.getString("indate"));
					parkManageVO.setOutdate(rs.getString("outdate"));
					
					item.add(parkManageVO);
					
				}while(rs.next());
			}
			return item;
			
		} finally {
			pstmt.close();
		}
		
	}			

	// ������ȣ �ƽ���+1 ä���ϱ�
	public int selectMaxParkNum(Connection conn) throws SQLException {

		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select max(parkno) from park_info_tbl");
			rs.next();
			return rs.getInt(1) + 1;

		} finally {
			stmt.close();
		}
	}

	// ���� ��Ȳ ��ȸ�ϱ�
	public ArrayList<ParkManageVO> selectInOutList(Connection conn) throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ParkManageVO> item = new ArrayList<ParkManageVO>();

		try {
			pstmt = conn.prepareStatement("select * from park_info_tbl");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ParkManageVO parkManageVO = new ParkManageVO();
				parkManageVO.setParkno(rs.getInt("parkno")); //������ȣ
				parkManageVO.setCarno(rs.getString("carno")); //������ȣ 
				parkManageVO.setGrade(rs.getString("grade")); //�������
				parkManageVO.setTstat(rs.getString("tstat")); //����
				parkManageVO.setIndate(rs.getString("indate"));
				parkManageVO.setOutdate(rs.getString("outdate"));
				item.add(parkManageVO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return item;

	}


}
