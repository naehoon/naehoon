import java.util.*;

public class MyClass170420{

	public static void main(String[] arg){

		
		int score = 0;

		char grade = ' ';

		System.out.println("����� ������ �Է��ϼ���. (1~100) > ");

		Scanner sc = new Scanner(System.in);

		String tmp = sc.nextLine(); 
		score = Integer.parseInt(tmp);

		switch(score/10){


			case 10: 
			case 9: 
				grade = 'A';
				break;

			case 8:
				grade = 'B';
				break;

			case 7:
				grade = 'C';
				break;

			default : 
				grade = 'F';
 
			
		}

		System.out.println("����� ������ " + grade +"�Դϴ�");

	}
}