package parkregi.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import parkregi.model.ParkingRegiVO;
import parkregi.service.ParkRegiService;

//정기권 등록 폼 핸들러 
public class ParkRegiFormHandler implements CommandHandler{
	
	private String FORM_VIEW = "/view/parkRegiForm.jsp";
	private ParkRegiService parkRegiService = new ParkRegiService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO Auto-generated method stub
//		System.out.println("1111111111111111111111111111");
		
		//맥스값 채번 하기 
		ParkingRegiVO parkingRegiVO = parkRegiService.getMaxParkRegNum();
		req.setAttribute("parkingRegiVO", parkingRegiVO);
		
		return FORM_VIEW;
	}
}
