package com.bs.control;

import com.bs.dao.CarDAO;

public class CarProcess {

	public static void main(String[] args) {
		CarDAO cd = new CarDAO();

		while (true) {
			switch (cd.displayMenu()) {
			case 1: //데이터 입력
				cd.inputRecord();
				break;
			case 2: //데이터 출력
				cd.printData();
				break;
			case 3: //데이터 정렬
				cd.sortRecord();
				break;
			case 4: //데이터 수정
				cd.updateRecord();
				break;
			case 5: //데이터 삭제
				cd.deleteRecord();
				break;
			case 6: //데이터 내보내기
				cd.ouputStream();
				break;
			case 7: //데이터 가져오기
				cd.inputStream();
				break;
			case 8: //프로그램 종료
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
			}
		}// while
	}
}
