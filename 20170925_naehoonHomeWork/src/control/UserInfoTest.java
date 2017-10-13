package control;

import java.util.ArrayList;

import dao.UserDAO;
import vo.UserVO;

public class UserInfoTest {

	public static void main(String[] args) {
		
		UserDAO ud = new UserDAO();
		UserVO uv = null; 

		while (true) {
			switch (ud.displayMenu()) {
			case 1: //데이터 입력
				uv = ud.insertUser();
				break;
			case 2: //데이터 수정
				ud.updateUser();
				break;
			case 3: //데이터 검색
				ArrayList<UserVO> li = ud.searchName();
				for (int i = 0; i < li.size(); i++) {
					uv = li.get(i);
					System.out.println(uv);
				}
				break;
			case 4: //데이터 삭제
				ud.deleteUser();
				break;
			case 5: //프로그램 종료
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
			}
		}// while

	}

}
