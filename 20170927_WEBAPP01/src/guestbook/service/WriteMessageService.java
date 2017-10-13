package guestbook.service;

import java.sql.Connection;
import java.sql.SQLException;

import guestbook.dao.MessageDao;
import guestbook.model.Message;
import jdbc.jdbcUtil;
import jdbc.connection.ConnectionProvider;

public class WriteMessageService {
	
	private static WriteMessageService instance = new WriteMessageService();
	
	public static WriteMessageService getInstance(){
		return instance;
	}
	
	private WriteMessageService() {

	}
	
	public void write(Message message){
		Connection conn = null;
		try{
			conn = ConnectionProvider.getConnection();
			MessageDao messageDao = MessageDao.getInstance();
			messageDao.insert(conn, message);
		}catch (SQLException e) {
			// TODO: handle exception
			throw new ServiceException("메시지 등록을 실패했습니다 : "  + e.getMessage(), e);
		}finally {
			jdbcUtil.close(conn);
		}
		
	}

}
