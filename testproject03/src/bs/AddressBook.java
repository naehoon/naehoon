package bs;

import java.util.ArrayList;
import java.util.Scanner;

public class AddressBook {

	public static void main(String[] args) {

		AddressBook ab = new AddressBook();
		ArrayList item = new ArrayList();
		Scanner sc = new Scanner(System.in);
		AddressVO av = new AddressVO();
		AddressController ac = new AddressController();

		outer: while (true) {
			System.out.println("*주소록* 1.입력, 2.출력, 3.수정, 4.종료");
			System.out.print(">>Menu: ");

			String tmp = sc.nextLine();
			av.setMenu(Integer.parseInt(tmp));

			if (av.getMenu() == 4) {
				System.out.println("주소록 프로그램을 종료합니다.");
				break;
			} else if (av.getMenu() == 3) {
				System.out.println("수정을 할수 없습니다.");
			} else if (!(1 <= av.getMenu() && av.getMenu() <= 4)) {
				System.out.println("올바르지 않은메뉴입니다.");
				continue;
			}

			switch (av.getMenu()) {

			case 1:
				System.out.println("이름: ");
				item.add(sc.nextLine());
				System.out.println("전화번호: ");
				item.add(sc.nextLine());
				ac.inputMethod(item); 
				break;
				
			case 2:
				System.out.println(ac.outputMethod());
				break;
			}
		}
	}
}