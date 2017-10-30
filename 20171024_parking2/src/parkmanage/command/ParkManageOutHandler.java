package parkmanage.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import parkmanage.model.ParkManageVO;
import parkmanage.service.ParkManageOutService;

public class ParkManageOutHandler implements CommandHandler{
	
	private ParkManageOutService parkManageOutService = new ParkManageOutService();
	private String FORM_VIEW = "/view/parkManageSuccessOUT.jsp";
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		String carno = req.getParameter("carno"); //차량번호
		String grade = req.getParameter("grade"); //주차등급
		String indate = req.getParameter("indate"); //입고일자
		String outdate = req.getParameter("outdate"); //출고일자

		try {
			//차량번호 입력
			ParkManageVO parkManageVO = new ParkManageVO(carno, grade, indate, outdate);
			//입고 하기
			parkManageOutService.updateParkOutput(parkManageVO);

			// 성공했을때
			return FORM_VIEW;

		} catch (Exception e) {
			System.out.println("오류");
			return "/view/parkManageOut.jsp";
		}
		
	}


}
