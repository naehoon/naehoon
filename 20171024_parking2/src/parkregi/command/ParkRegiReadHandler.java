package parkregi.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import parkregi.model.ParkingRegiVO;
import parkregi.service.ParkRegiReadService;

//정기권 등록자 조회 화면 핸들러
public class ParkRegiReadHandler implements CommandHandler {

	private ParkRegiReadService parkRegiReadService = new ParkRegiReadService();
	private String FORM_VIEW = "/view/parkRegiForm.jsp";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		System.out.println("@@@@@@@@@@@@@ pageNoVal : " + pageNoVal);
		
		if(pageNoVal != null){
			pageNo = Integer.parseInt(pageNoVal);
		}

		//주차권 조회 하기 
		ParkingRegiVO parkingRegiVO = parkRegiReadService.selectParkingList(pageNo);
		req.setAttribute("parkingRegiVO", parkingRegiVO);
		
		//성공했을때
		return "/view/parkTicketView.jsp";
		
	}
}
