import java.util.*;

public class MyClass170420_04{

	public static void main(String[] arg){
		
		int score = 0;
		int i = 0;
		int sum = 0;

		//char grade = ' ';

		System.out.println("당신의 숫자를 입력하세요!!!!!!");

		Scanner sc = new Scanner(System.in);

		String tmp = sc.nextLine(); 
		score = Integer.parseInt(tmp);

		for(i=1; i<=score; i++){

			sum = sum + i;
			
		}

		System.out.println("1부터 입력하신 " + score + " 까지의 합은 " + sum + "입니다.");

	}
}