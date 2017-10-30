package parkmanage.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import parkmanage.model.ParkManageVO;
import parkmanage.service.ParkManageReadService;

public class ParkManageReadHandler implements CommandHandler {

	private ParkManageReadService parkManageReadService = new ParkManageReadService();
	private String FORM_VIEW = "/view/parkManageView.jsp";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {

			String pageNoVal = req.getParameter("pageNo");
			int pageNo = 1;
			System.out.println("@@@@@@@@@@@@@ pageNoVal : " + pageNoVal);
			
			if(pageNoVal != null){
				pageNo = Integer.parseInt(pageNoVal);
			}
			// 주차 입출고 현황 조회 하기
			ParkManageVO parkManageVO = parkManageReadService.selectParkingList(pageNo);
			req.setAttribute("parkManageVO", parkManageVO);
			// 성공했을때
			return FORM_VIEW;

	}

}
