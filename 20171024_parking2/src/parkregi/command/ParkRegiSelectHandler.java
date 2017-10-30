package parkregi.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import parkregi.model.ParkingRegiVO;
import parkregi.service.ParkRegiSelectService;

public class ParkRegiSelectHandler  implements CommandHandler{
	
	private ParkRegiSelectService parkRegiSelectService = new ParkRegiSelectService();
	private String FORM_VIEW = "/view/parkRegiFormSelect.jsp";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO Auto-generated method stub
		String carno = req.getParameter("carno"); //차량번호
		
		try {
			
			//차량번호 입력
			ParkingRegiVO parkingRegiVO = new ParkingRegiVO(carno);

			//주차권 조회 하기 
			req.setAttribute("parkingRegiVO", parkRegiSelectService.selectParkingOne(parkingRegiVO));
			
			//성공했을때
			return FORM_VIEW;

		} catch (Exception e) {
			return  "/view/parkRegiForm.jsp";

		}
	}
}
