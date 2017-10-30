package parkregi.command;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import parkregi.model.ParkingRegiVO;
import parkregi.service.DuplicateIdException;
import parkregi.service.ParkRegiService;
import parkregi.service.ParkRegiWriteService;

public class ParkRegiWriteHandler implements CommandHandler {

	private ParkRegiWriteService parkRegiWriteService = new ParkRegiWriteService();
	private ParkRegiService parkRegiService = new ParkRegiService();

	private String FORM_VIEW = "/view/parkRegiForm.jsp";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		int tno = Integer.parseInt(req.getParameter("tno").trim());
		String carno = req.getParameter("carno");
		String phone = req.getParameter("phone");
		String grade = req.getParameter("grade");
		String tstat = req.getParameter("tstat");
		String startdate = req.getParameter("startdate");
		String enddate = req.getParameter("enddate");

		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		ParkingRegiVO parkingRegiVO = new ParkingRegiVO(tno, carno, phone, grade, tstat, startdate, enddate);

		// 맥스값 채번 하기 (다시 채번)
		ParkingRegiVO maxNum = parkRegiService.getMaxParkRegNum();
		req.setAttribute("parkingRegiVO", maxNum);
		
		String datePattern = "^[0-9]*$";
//
//		if (!Pattern.matches(datePattern, startdate) || startdate.length() != 8) {
//			errors.put("startdateFail", Boolean.TRUE);
//		}
//		
//		if (!Pattern.matches(datePattern, enddate) || enddate.length() != 8) {
//			errors.put("startdateFail", Boolean.TRUE);
//		}

		if (carno == null || carno.isEmpty() || carno.length() > 10) {
			errors.put("carno", Boolean.TRUE);
		}

		if (startdate == null || startdate.isEmpty() || !Pattern.matches(datePattern, startdate) || startdate.length() != 8) {
			errors.put("startdate", Boolean.TRUE);
		}

		if (enddate == null || enddate.isEmpty() || !Pattern.matches(datePattern, enddate) || enddate.length() != 8 ) {
			errors.put("enddate", Boolean.TRUE);
		}
		
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}

		try {
			//주차권 등록 하기 
			parkRegiWriteService.write(parkingRegiVO);
			//성공했을때
			return "/view/parkTicketSuccess.jsp";

		} catch (DuplicateIdException e) {
			errors.put("duplicateId", Boolean.TRUE);
			//중복된 차량번호가 있으면
			return FORM_VIEW;
			
		}

	}

}
