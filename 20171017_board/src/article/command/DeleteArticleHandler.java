package article.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.service.ArticleData;
import article.service.ArticleNotFoundException;
import article.service.DeleteArticleService;
import article.service.DeleteRequest;
import article.service.PermissionDeniedException;
import article.service.ReadArticleService;
import auth.service.User;
import mvc.command.CommandHandler;

public class DeleteArticleHandler implements CommandHandler{
	
	private static final String FORM_VIEW="/WEB-INF/view/listArticle.jsp";
	
	private ReadArticleService readService = new ReadArticleService();
	private DeleteArticleService deleteService = new DeleteArticleService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		if(req.getMethod().equalsIgnoreCase("GET")){
			return processSubmit(req, res);
		}else{
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res)throws Exception{
		
		try{
			
			String noVal = req.getParameter("no").trim();
			int no = Integer.parseInt(noVal);
			ArticleData articleData = readService.getArticle(no, false);
			User authUser = (User)req.getSession().getAttribute("authUser");
			
			if(!canModify(authUser, articleData)){
				res.sendError(HttpServletResponse.SC_FORBIDDEN);
				return null;
			}
			
			DeleteRequest delReq = new DeleteRequest(authUser.getId(), no, req.getParameter("title"), req.getParameter("content"));
			req.setAttribute("delReq", delReq);
			
			deleteService.delete(delReq);
			return "/WEB-INF/view/deleteSuccess.jsp";
			
		}catch (ArticleNotFoundException e) {
			// TODO: handle exception
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}catch (PermissionDeniedException e) {
			// TODO: handle exception
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}
	
	private boolean canModify(User authUser, ArticleData articleData){
		String writerId = articleData.getArticle().getWriter().getId();
		return authUser.getId().equals(writerId);
	}
	
}
