import java.util.*;

public class MyClass170425_01{

	public static void main(String[] arg){
		
		String munja = "";
		//int i = 0;
		//int sum = 0;

		//char grade = ' ';

		System.out.println("����� ���ڸ� �Է��ϼ���!!!!!!");

		Scanner sc = new Scanner(System.in);

		String tmp = sc.nextLine();
		
		munja = tmp;

		//System.out.println("����" + munja.length());

		for(int i=0; i<munja.length(); i++){

			char ch = munja.charAt(i);

			System.out.println(munja.substring(i,i));
			
			//System.out.println(ch);			
		}
		//System.out.println("1���� �Է��Ͻ� " + score + " ������ ���� " + sum + "�Դϴ�.");
	}
}