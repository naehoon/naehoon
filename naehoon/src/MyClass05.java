import java.util.*;

public class MyClass05{

	public static void main(String[] arg){

		int input;
		char gender;

		System.out.println("성별을 입력하세요 M/Y 남자/여자");

		Scanner scanner = new Scanner(System.in);
		String tmp = scanner.nextLine();

		//input = Integer.parseInt(tmp);

		gender = tmp.charAt(0);

		System.out.println(gender);

		if(gender == 'M'){

			System.out.println("남자");

		}else if(gender == 'Y'){

			System.out.println("여자");

		}


	}

}