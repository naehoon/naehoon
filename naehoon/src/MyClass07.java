import java.util.*;

public class MyClass07{

	public static void main(String[] arg){

		System.out.println("사용자의 일본어점수, IT 점수를 입력하세요");

		Scanner scanner = new Scanner(System.in);
		String tmpjapan = scanner.nextLine();
		String tmpit = scanner.nextLine();

		int japan = Integer.parseInt(tmpjapan);
		int it = Integer.parseInt(tmpit);

		if(japan >= 60 || it >= 60){

			System.out.println("합격");

		}else{

			System.out.println("불합격");

		}

	}

}