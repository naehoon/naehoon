package article.service;

import java.sql.Connection;
import java.sql.SQLException;

import article.dao.ArticleContentDao;
import article.dao.ArticleDao;
import article.model.Article;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class DeleteArticleService {
	
	private ArticleDao articleDao = new ArticleDao();
	private ArticleContentDao articleContentDao = new ArticleContentDao();
	
	//게시글 삭제 메서드
	public void delete(DeleteRequest delReq){
		Connection conn = null;
		try{
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Article article = articleDao.selectById(conn, delReq.getArticleNumber());
			
			if(article == null){
				throw new ArticleNotFoundException();
			}
			
			if(!canModify(delReq.getUserId(), article)){
				throw new PermissionDeniedException();
			}
			
			articleDao.deleteArticle(conn, delReq.getArticleNumber());
			articleDao.deleteArticleContent(conn, delReq.getArticleNumber());
			conn.commit();
			
		}catch (SQLException e) {
			// TODO: handle exception
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}catch (PermissionDeniedException e) {
			// TODO: handle exception
			JdbcUtil.rollback(conn);
			throw e;
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
	private boolean canModify(String modifyingUserId, Article article){
		return article.getWriter().getId().equals(modifyingUserId);
	}
	
}
