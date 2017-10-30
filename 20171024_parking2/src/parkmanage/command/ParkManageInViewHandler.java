package parkmanage.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import parkmanage.model.ParkManageVO;
import parkmanage.service.ParkManageInViewService;

public class ParkManageInViewHandler implements CommandHandler {

	private ParkManageInViewService parkManageInService = new ParkManageInViewService();
	private String FORM_VIEW = "/view/parkManageIN.jsp";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO Auto-generated method stub
		String carno = req.getParameter("carno");

		try {
			//차량번호 입력
			ParkManageVO parkManageVO = new ParkManageVO(carno);
			
			// 주차 입고 조회 하기
			req.setAttribute("parkManageVO", parkManageInService.selectParkIn(parkManageVO));

			// 주차번호 Max 값 채번하기
			req.setAttribute("maxParkNum",  parkManageInService.selectMaxParkNum() );

			// 성공했을때
			return FORM_VIEW;

		} catch (Exception e) {
			System.out.println("오류");
			return FORM_VIEW;
		}

	}

}
