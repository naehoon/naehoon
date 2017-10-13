package guestbook.service;

import java.sql.Connection;
import java.sql.SQLException;

import guestbook.dao.MessageDao;
import guestbook.model.Message;
import jdbc.jdbcUtil;
import jdbc.connection.ConnectionProvider;

public class DeleteMessageService {
	
	//싱글턴 패턴
	//유일한 객체를 static 필드에 저장한다.
	private static DeleteMessageService instance = new DeleteMessageService();

	//유일한 객체에 접근할 수 있는 정적 메서드 정의.
	public static DeleteMessageService getInstance(){
		return instance;
	}
	
	//외부에서 객체 생성 금지 
	private DeleteMessageService(){}
	
	public void deleteMessage(int messageId, String password){
		Connection conn = null;
		try{
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			MessageDao messageDao = MessageDao.getInstance();
			Message message = messageDao.select(conn, messageId);
			
			if(message == null){
				//메시지가 없을경우 예외발생
				throw new MessageNotFoundException("메시지 없음");
			}
			
			System.out.println("delete DAO password : " + password);
			if(!message.matchPassword(password)){
				//패스워드 틀렸을때 예외 발생
				throw new InvalidPasswordException("bad password");
			}
			
			messageDao.delete(conn, messageId);
			conn.commit();
			
		}catch (SQLException ex) {
			jdbcUtil.rollback(conn);
			
			//예외 발생
			throw new ServiceException("삭제 실패 : " + ex.getMessage(), ex);
			
		}catch (InvalidPasswordException | MessageNotFoundException ex) {
			jdbcUtil.rollback(conn);
			throw ex;
		}finally {
			jdbcUtil.close(conn);
		}
	}
}
