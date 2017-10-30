import java.util.*;

public class MyClass03{

	public static void main(String[] arg){

		Scanner scanner = new Scanner(System.in);

		System.out.print("두자리 정수를 입력하세요");

		String input = scanner.nextLine();

		int num = Integer.parseInt(input);

		System.out.print("입력내용 : " + input);				
		System.out.printf("num=%d\n", num);				

	}

}