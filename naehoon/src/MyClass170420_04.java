import java.util.*;

public class MyClass170420_04{

	public static void main(String[] arg){
		
		int score = 0;
		int i = 0;
		int sum = 0;

		//char grade = ' ';

		System.out.println("����� ���ڸ� �Է��ϼ���!!!!!!");

		Scanner sc = new Scanner(System.in);

		String tmp = sc.nextLine(); 
		score = Integer.parseInt(tmp);

		for(i=1; i<=score; i++){

			sum = sum + i;
			
		}

		System.out.println("1���� �Է��Ͻ� " + score + " ������ ���� " + sum + "�Դϴ�.");

	}
}