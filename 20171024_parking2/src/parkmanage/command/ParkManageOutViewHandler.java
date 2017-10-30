package parkmanage.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import parkmanage.model.ParkManageVO;
import parkmanage.service.ParkManageOutViewService;

public class ParkManageOutViewHandler implements CommandHandler{
	
	private ParkManageOutViewService parkManageOutViewService = new ParkManageOutViewService();
	private String FORM_VIEW = "/view/parkManageOUT.jsp";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		String carno = req.getParameter("carno");
		try {
			//차량번호 입력
			ParkManageVO parkManageVO = new ParkManageVO(carno);
			
			// 주차 입고 조회 하기
			System.out.println("입고조회 진행11111111111111111111");
			req.setAttribute("parkManageVO", parkManageOutViewService.selectParkOut(parkManageVO));
			// 성공했을때
			return FORM_VIEW;

		} catch (Exception e) {
			System.out.println("오류");
			return FORM_VIEW;
		}

	}

}
