package guestbook.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import guestbook.dao.MessageDao;
import guestbook.model.Message;
import jdbc.jdbcUtil;
import jdbc.connection.ConnectionProvider;

public class GetMessageListService {
	
	private static GetMessageListService instance = new GetMessageListService();
	
	public static GetMessageListService getInstance(){
		return instance;
	}
	
	private GetMessageListService(){
		
	}
	
	private static final int MESSAGE_COUNT_PER_PAGE = 3;
	
	//테이블 조회, 게시물 갯수, 페이징 처리 메서드 
	public MessageListView getMessageList(int pageNumber){
		Connection conn  =null;
		int currentPageNumber = pageNumber;
		
		try{
			conn = ConnectionProvider.getConnection();
			MessageDao messageDao = MessageDao.getInstance();
			
			int messageTotalCount = messageDao.selectCount(conn);
			
			List<Message> messageList = null;
			int firstRow = 0;
			int endRow = 0;
			
			if(messageTotalCount > 0){
				firstRow = (pageNumber -1) * MESSAGE_COUNT_PER_PAGE + 1;
				endRow = firstRow + MESSAGE_COUNT_PER_PAGE -1;
				messageList  = messageDao.selectList(conn, firstRow, endRow);
			}else{
				currentPageNumber = 0;
				messageList = Collections.emptyList();
			}
			return new MessageListView(messageList, messageTotalCount, currentPageNumber, MESSAGE_COUNT_PER_PAGE, firstRow, endRow);
			
		}catch (SQLException e) {
			// TODO: handle exception
			throw new ServiceException("목록 구하기 실패했습니다 : " + e.getMessage(), e);
		}finally {
			jdbcUtil.close(conn);
		}
				
	}

}
