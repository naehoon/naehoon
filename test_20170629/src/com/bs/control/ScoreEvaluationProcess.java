package com.bs.control;
import java.io.IOException;

import com.bs.dao.ScoreEvaluationDAO;
import com.bs.vo.Student;

public class ScoreEvaluationProcess {
	public static void main(String[] args) throws IOException {
		ScoreEvaluationDAO sed = new ScoreEvaluationDAO();

		while (true) {
			switch (sed.displayMenu()) {
			case 1: //데이터 입력
				sed.inputRecord();
				break;
			case 2: //데이터 삭제
				sed.deleteRecord();
				break;
			case 3: //데이터 정렬
				sed.sortRecord();
				break;
			case 4: //출력
				sed.displayRecord();
				break;
			case 5: //파일로 내보내기
				sed.outputRecord();
				break;				
			case 6: //프로그램 종료
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
			}
		}// while
	}
}// class
