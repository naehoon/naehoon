package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;

public class ChangePasswordService {
	
	private MemberDao memberDao = new MemberDao();
	
	//패스워드 변경 메서드 
	public void changePassword(String userId, String curPwd, String newPwd){
		Connection conn = null;
		try{
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Member member = memberDao.selectById(conn, userId);
			
			if(member == null){ //해당하는 아이디가 존재하지 않을때 
				throw new MemberNotFoundException();
			}
			
			if(!member.matchPassword(curPwd)){ //현재 패스워드와 일치하지 않을때 
				throw new InvalidPasswordException();
			}
			
			member.changePassword(newPwd);
			memberDao.update(conn, member);
			conn.commit();
			
		}catch (SQLException e) { //SQL 에러 발생시 롤백한다.
			// TODO: handle exception
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
			
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
