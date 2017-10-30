package parkmanage.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import parkmanage.model.ParkManageVO;
import parkmanage.service.ParkManageInService;

//차량 입고 핸들러
public class ParkManageInHandler implements CommandHandler {

	private ParkManageInService parkManageInService = new ParkManageInService();
	private String FORM_VIEW = "/view/parkManageSuccessIN.jsp";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO Auto-generated method stub
		String carno = req.getParameter("carno"); //차량번호
		String grade = req.getParameter("grade"); //주차등급
		String indate = req.getParameter("indate"); //입고일자
		String outdate = req.getParameter("outdate"); //출고일자
		
		System.out.println("1111111111111 " + outdate );
		
//		park_info_tbl
		try {
			
			//차량번호 입력
			ParkManageVO parkManageVO = new ParkManageVO(carno, grade, indate, outdate);
			parkManageInService.insertParkInput(parkManageVO);
				// 성공했을때
			return FORM_VIEW;

		} catch (Exception e) {
			System.out.println("오류");
			e.printStackTrace();
			
			return "/view/parkManageIN.jsp";
		}

	}

}
