package parkmanage.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

//주차차량 입고 출고관리 선택화면 
public class ParkManageHandler implements CommandHandler{
	
	private String FORM_VIEW = "/view/parkManage.jsp";
//	private ParkManageService parkManageService = new ParkManageService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		return FORM_VIEW;
	}

}
