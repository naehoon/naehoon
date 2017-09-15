import java.util.*;

public class MyClass08{

	public static void main(String[] arg){

		int month = 0;

		System.out.println("현재의 월을 입력하세요");

		Scanner sc = new Scanner(System.in);

		String tmp = sc.nextLine();

		month = Integer.parseInt(tmp);

		switch(month){

			case 3: case 4: case 5:

				System.out.println("현재의 계절은 봄입니다");
				break;

			case 6: case 7: case 8:
		
				System.out.println("현재의 계절은 여름입니다.");
				break;

			case 9: case 10: case 11:

				System.out.println("현재의 계절은 가을입니다.");
				break;

			default:			

				System.out.println("현재의 계절은 겨울 입니다.");
		}
	}
}