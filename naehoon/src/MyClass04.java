import java.util.*;

public class MyClass04{

	public static void main(String[] arg){

		int input;

		System.out.print("숫자를 하나 입력하세요.>");

		Scanner scanner = new Scanner(System.in);
		String tmp = scanner.nextLine();
		input = Integer.parseInt(tmp);

		if(input ==0){		
			System.out.print("입력하신 숫자는 0 입니다. ");
		}

		if(input !=0){		
			System.out.println("입력하신 숫자는 0 이 아닙니다.. ");
			System.out.printf("입력하신 숫자는 %d 입니다.", input);
		}

	}

}